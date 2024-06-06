package com.fcctech.tournament.service.impl;

import com.fcctech.tournament.domain.CompetitionStatus;
import com.fcctech.tournament.domain.competition.CompetitionFactory;
import com.fcctech.tournament.domain.competition.MatchStatus;
import com.fcctech.tournament.entity.*;
import com.fcctech.tournament.exception.BadRequestException;
import com.fcctech.tournament.exception.BusinessException;
import com.fcctech.tournament.exception.NotFoundException;
import com.fcctech.tournament.payload.request.MatchRequest;
import com.fcctech.tournament.payload.response.BeltCategory;
import com.fcctech.tournament.payload.response.competition.CompositionKey;
import com.fcctech.tournament.payload.response.competition.ParticipantComposition;
import com.fcctech.tournament.payload.response.competition.TournamentComposition;
import com.fcctech.tournament.repository.CompetitionRepository;
import com.fcctech.tournament.repository.MatchRepository;
import com.fcctech.tournament.repository.ParticipantRepository;
import com.fcctech.tournament.service.CompetitionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class CompetitionServiceImp implements CompetitionService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private MatchRepository matchRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<TournamentComposition> getTournamentComposition(Long tournamentId) {

        Map<CompositionKey, List<Participant>> map = getTournamentCompositionMap(tournamentId);

        return map.entrySet().stream().map(compositionKeySetEntry -> TournamentComposition.builder()
                .name(compositionKeySetEntry.getKey().getBaseCategory().getName())
                .count((long) compositionKeySetEntry.getValue().size())
                .participants(compositionKeySetEntry.getValue().stream().map(participant -> ParticipantComposition.builder()
                        .name(participant.getName())
                        .lastName(participant.getLastName())
                        .id(participant.getId())
                        .team(participant.getTeam().getName())
                        .participant(participant)
                        .build()).collect(Collectors.toSet()))
                .sex(compositionKeySetEntry.getKey().getSex())
                .belt(fromBeltCategory(compositionKeySetEntry.getKey().getBelt()))
                .modality(compositionKeySetEntry.getKey().getModality())
                .categoryId(compositionKeySetEntry.getKey().getCategoryId())
                .build()).collect(Collectors.toSet());
    }

    private BeltCategory fromBeltCategory(com.fcctech.tournament.entity.BeltCategory beltCategory) {
        return BeltCategory.builder()
                .name(beltCategory.getName())
                .id(beltCategory.getId())
                .build();
    }

    @Override
    public Map<CompositionKey, List<Participant>> getTournamentCompositionMap(Long tournamentId) {
        Set<Participant> participants = participantRepository.findAllParticipantByTournamentId(tournamentId);
        Map<CompositionKey, List<Participant>> map = new HashMap<>();
        participants.forEach(participant -> participant.getModalities().forEach(modality -> {
            CompositionKey key = CompositionKey
                    .builder()
                    .sex(participant.getSex())
                    .belt(participant.getBeltCategory())
                    .modality(modality.getType())
                    .categoryId(modality.getCategory().getId())
                    .baseCategory(modality.getCategory())
                    .build();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(participant);
        }));
        return map;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void createCompetitions(Long tournamentId) {
        Map<CompositionKey, List<Participant>> competitions = getTournamentCompositionMap(tournamentId);

        log.info("[action:create_competition] [tournament:{}] [categories:{}] [state:started]", tournamentId, competitions.size());

        List<Competition> competionsList = new ArrayList<>();

        competitions.forEach((compositionKey, participants) -> {
            var builder = CompetitionFactory.INSTANCE.getBuilder(compositionKey.getBaseCategory().getFormat());
            log.info("[action:building_competition] [key: {}] [state:started] [participants: {}]", compositionKey, participants.size());
            try {
                competionsList.add(builder.createCompetition(compositionKey.getBelt().getTournament(), compositionKey, participants));
                log.info("[action:building_competition] [key: {}] [state:build_success]", compositionKey);
            } catch (Throwable t) {
                log.error("[action:building_competition][key: {}]", compositionKey, t);
            }
        });

        sortAndEnumerateCompetitionMatch(competionsList,1);
        log.info("[action:enumerated_matches] [tournament:{}] [categories:{}] [state:started]", tournamentId, competitions.size());

        competionsList.forEach(competition -> {
            try {
                entityManager.persist(competition);
                log.info("[action:persist_competition] {}", competition.getCategory().display());
            } catch (Throwable t) {
                log.error("[action:persist_competition][key: {}]", competition.getCategory().display(), t);
            }
        });


        boolean update = entityManager.createQuery("update Tournament t set t.status = :status " +
                        "where t.id = :tournamentId")
                .setParameter("status", TournamentStatus.IN_PROCESS)
                .setParameter("tournamentId", tournamentId)
                .executeUpdate() == 1;

        log.info("[action:create_competition] [tournament:{}] [categories:{}] [state:started]", tournamentId, competitions.size());

    }

    @Override
    public Page<Competition> listTournamentCompetitions(Long tournamentId, int page, int size, String sortDir, String field) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), field);
        return participantRepository.findCompetitiontByTournamentId(tournamentId, pageRequest);
    }

    @Override
    public Optional<Competition> findCompetitionById(Long tournamentId, Long competitionId) {
        return competitionRepository.findByTournamentIdAndId(tournamentId, competitionId);
    }

    @Override
    @Transient
    public Optional<Match> updateMatch(Long userId, Long tournamentId, Long competitionId, Long matchId, MatchRequest request) {
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new NotFoundException("match not found"));
        Tournament tournament = match.getBracket().getTournament();
        tournament.assertOwner(userId);

        if (!match.getBracket().getId().equals(competitionId)) {
            throw new BadRequestException("match not belongs to competition");
        }
        if (!match.getBracket().getTournament().getId().equals(tournamentId)) {
            throw new BadRequestException("competition not belongs to tournament");
        }

        if (!match.getStatus().equals(MatchStatus.READY)) {
            throw new BadRequestException("match is not ready to be updated ");
        }


        Participant winner = null;
        if (match.getLeft().getId().equals(request.getWinner())) {
            winner = match.getLeft();
        } else if (match.getRight().getId().equals(request.getWinner())) {
            winner = match.getRight();
        } else {
            throw new BadRequestException("the winner is not from match");
        }

        match.setWinner(winner);
        match.setStart(request.getStart());
        match.setEnd(request.getEnd());
        match.setStatus(MatchStatus.FINALIZED);


        Match nextMatch = match.getNext();
        if (nextMatch != null) {
            prepareNextMatch(nextMatch, winner);
            matchRepository.save(match);
            matchRepository.save(nextMatch);
            log.info("[action:updateMatch] [match_id:{}] [next_match:{}]", match.getId(), nextMatch.getId());
        } else {
            //There is not match less then finalize
            BracketCompetition competition = match.getBracket();
            competition.setStatus(CompetitionStatus.FINALIZED);
            matchRepository.save(match);
            competitionRepository.save(competition);
            log.info("[action:updateMatch] Competition: {} finalized", competition.getId());
        }

        return Optional.ofNullable(nextMatch);
    }

    @Override
    public Match findMatch(Long tournamentId, Long competitionId, Long matchId) {
        return matchRepository.findById(matchId).orElseThrow(() -> new NotFoundException("match not found"));
    }

    private void prepareNextMatch(@NotNull Match nextMatch, @NotNull Participant winner) {
        if (nextMatch.getLeft() == null) {
            nextMatch.setLeft(winner);
        } else if (nextMatch.getRight() == null) {
            nextMatch.setRight(winner);
        } else {
            throw new BadRequestException("the match already completed");
        }
        MatchStatus status = nextMatch.getLeft() != null && nextMatch.getRight() != null
                ? MatchStatus.READY
                : MatchStatus.WAITING_MATCH;

        nextMatch.setStatus(status);
    }

    public void sortAndEnumerateCompetitionMatch(List<Competition> competitions, long startIn) {
        List<Match> matches = competitions.stream()
                .filter(competition -> competition instanceof BracketCompetition)
                .map(competition -> (BracketCompetition)competition)
                .map(BracketCompetition::getMatches)
                .flatMap(List::stream).collect(Collectors.toList());

        matches.sort((o1, o2) -> Comparator
                .comparing((Match match) -> match.getBracket().getCategory().getMinYear())
                .thenComparing(Match::getRound, Comparator.reverseOrder())
                .compare(o1, o2));

        for (int i = 0; i < matches.size(); i++) {
            matches.get(i).setNumber(startIn + i);
        }
    }
}
