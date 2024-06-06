package com.fcctech.tournament.service.impl;

import com.fcctech.tournament.domain.Action;
import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.ParticipantStatus;
import com.fcctech.tournament.entity.*;
import com.fcctech.tournament.exception.BadRequestException;
import com.fcctech.tournament.exception.ForbiddenException;
import com.fcctech.tournament.exception.NotFoundException;
import com.fcctech.tournament.payload.request.ModalityRequest;
import com.fcctech.tournament.payload.request.ParticipantRequest;
import com.fcctech.tournament.repository.*;
import com.fcctech.tournament.service.ParticipantService;
import com.fcctech.tournament.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;



@Service
@RequiredArgsConstructor
@AllArgsConstructor
@Slf4j
public class ParticipantServiceImp implements ParticipantService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private ParticipantTeamRepository participantTeamRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private BeltCategoryRepository beltCategoryRepository;

    @Autowired
    private CombatCategoryRepository combatCategoryRepository;

    @Autowired
    private FormCategoryRepository formCategoryRepository;


    public Participant registerParticipant(User registerBy, Tournament tournament, ParticipantTeam participantTeam, ParticipantRequest request) {
        BeltCategory beltCategory = beltCategoryRepository.findBeltCategoryByTournamentAndId(tournament.getId(), request.getBeltCategoryId()).orElseThrow(() -> new NotFoundException("belt not found"));
        //Validate ParticipantTeam is register on the same tournament

        Participant participant = new Participant();
        participant.setName(request.getName());
        participant.setEmail(request.getEmail());
        participant.setLastName(request.getLastName());
        participant.setTeam(participantTeam);
        participant.setTournament(tournament);
        participant.setWeight(request.getWeight());
        participant.setBirthDate(request.getDateBirth());
        participant.setSex(request.getSex());
        participant.setBeltCategory(beltCategory);

        Set<Modality> modalities = new HashSet<>();
        request.getModalities().forEach(modalityRequest -> {
            Modality modality = new Modality();
            modality.setParticipant(participant);
            modality.setType(modalityRequest.getType());
            //modality.setCategoryId(modalityRequest.getCategoryId());
            checkAndApply(modality, modalityRequest.getType(), modalityRequest.getCategoryId(), participant.getWeight(), participant.getBirthDate());

            modalities.add(modality);
        });

        participant.setModalities(modalities);

        participant.setStatus(ParticipantStatus.PENDING_VALIDATION);

        return participantRepository.save(participant);
    }
    @Override
    public Participant registerParticipant(User registerBy, Long tournamentID, Long teamID, ParticipantRequest request) {
        Tournament tournament = tournamentRepository.findById(tournamentID).orElseThrow(() -> new NotFoundException("tournament not found"));
        ParticipantTeam participantTeam = participantTeamRepository.findById(teamID).orElseThrow(() -> new NotFoundException("team not found"));
        return registerParticipant(registerBy, tournament, participantTeam, request);

    }

    @Override
    public Optional<Participant> findById(Long participantId) {
        return participantRepository.findById(participantId);
    }



    @Override
    public void updateParticipant(Long userId, Long participantId, Long tournamentID, Long teamID, ParticipantRequest request) {
        Participant participant = participantRepository.findById(participantId).orElseThrow(() -> new NotFoundException("user not found"));
        if (!participant.getTournament().getId().equals(tournamentID)) {
            throw new BadRequestException("participant doesn't belong to the tournament");
        }
        if (!participant.getTeam().getId().equals(teamID)) {
            throw new BadRequestException("participant doesn't belong to the team");
        }

        participant.setName(request.getName());
        participant.setEmail(request.getEmail());
        participant.setLastName(request.getLastName());
        participant.setWeight(request.getWeight());
        participant.setBirthDate(request.getDateBirth());
        participant.setSex(request.getSex());

        if (participant.getBeltCategory().getId() != request.getBeltCategoryId()) {
            beltCategoryRepository.findBeltCategoryByTournamentAndId(participant.getTournament().getId(), request.getBeltCategoryId()).orElseThrow(() -> new NotFoundException("belt not found"));
        }

        request.getModalities().forEach(modalityRequest -> {
            switch (modalityRequest.getAction().orElse(Action.NEW)) {
                case REMOVE -> removeModality(participant, modalityRequest);
                case UPDATE -> updateCategory(participant.getModalities(), modalityRequest.getId(), modalityRequest.getType(), modalityRequest.getCategoryId(), participant.getWeight(), participant.getBirthDate());
                default -> appendNewModality(participant, modalityRequest);
            }
        });


        participantRepository.save(participant);
    }

    @Override
    public Set<Participant> findAllByTournamentIdAndTeamId(Long tournamentID, Long teamID) {
        return participantRepository.findAllParticipantByTeamAndTournamentId(tournamentID, teamID);
    }

    @Override
    public Set<Participant> findAllByTournamentId(Long tournamentID) {
        return participantRepository.findAllParticipantByTournamentId(tournamentID);
    }

    @Override
    public void updateParticipantCheckIn(Long userId, Long participantId, Long tournamentID) {
        Participant participant = participantRepository.findById(participantId).orElseThrow(() -> new NotFoundException("participant not found"));
        participant.getTournament().assertOwner(userId);

        if (!participant.getTournament().getId().equals(tournamentID)) {
            throw new BadRequestException("participant doesn't belong to tournament");
        }
        participant.setStatus(ParticipantStatus.VALIDATED);
        participantRepository.save(participant);
    }

    /**
     *  Only to person can delete the participant:
     *      1) The tournament owner
     *      2) Who subscribe the participant
     * */
    @Override
    public void deleteParticipant(Long userId, Long participantId, Long tournamentID) {
        Participant participant = participantRepository.findById(participantId).orElseThrow(() -> new NotFoundException("participant not found"));
        Tournament tournament = participant.getTournament();

        //Owner can delete participant
        if (tournament.getOwner().getId().equals(userId)) {
            //check belongs to the tournament?
            if (!tournament.getId().equals(tournamentID)) {
                throw new BadRequestException("participant doesn't belong to tournament");
            }

            participantRepository.delete(participant);

        } else if (participant.getTeam().getSubscriber().getId().equals(userId)) {

            participantRepository.delete(participant);

        } else {
            throw new ForbiddenException("access denied");
        }
    }

    @Override
    public Page<Participant> searchParticipants(Long tournamentID, int page, int size, String sortDir, String sortField) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sortField);
        return participantRepository.findAllParticipantByTournamentId(tournamentID, pageRequest);
    }

    private void removeModality(Participant participant, ModalityRequest modalityRequest) {
        participant.getModalities().removeIf(modality -> modality.getId().equals(modalityRequest.getId()));
    }

    private void appendNewModality(Participant participant, ModalityRequest modalityRequest) {
        Modality modality = new Modality();
        modality.setType(modalityRequest.getType());
        modality.setParticipant(participant);

        checkAndApply(modality, modalityRequest.getType(), modalityRequest.getCategoryId(), participant.getWeight(), participant.getBirthDate());
        participant.getModalities().add(modality);
    }

    private void updateCategory(Set<Modality> modalities, Long id, ModalityType type, Long categoryId, Double weight, LocalDate birthDate) {
        Modality modality = modalities.stream().filter(modality1 -> modality1.getId().equals(id) && modality1.getType().equals(type))
                .findAny().orElseThrow(() -> new NotFoundException(String.format("modality id [%d] not found", id)));
        //If they are distinct change category
        if (!modality.getCategory().getId().equals(categoryId)) {
            checkAndApply(modality, type, categoryId, weight , birthDate);
        }
    }

    private void checkAndApply(Modality modality, ModalityType type, Long categoryId, Double weight, LocalDate birthDate) {
        if (type.isWeightNeedIt()) {
            Objects.requireNonNull(weight, "weight is mandatory for fight category");

            CombatCategory fightCategory = combatCategoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("fight category not found"));
            if (fightCategory.isNotInWeightRange(weight)) {
                throw new BadRequestException(String.format("the fight[%f] is not in range[%d, %d] for category selected", weight, fightCategory.getMinWeight(), fightCategory.getMaxWeight()));
            }
            checkCategoryAgeInRange(fightCategory.getAgeCategory().getMinAge(), fightCategory.getAgeCategory().getMaxAge(), birthDate);

            modality.setCategory(fightCategory);
        } else {
            FormCategory styleCategory = formCategoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("style category not found"));
            checkCategoryAgeInRange(styleCategory.getMinAge(), styleCategory.getMaxAge(), birthDate);
            modality.setCategory(styleCategory);
        }
    }

    private void checkCategoryAgeInRange(int minAge, int maxAge, LocalDate birthDate) {
        if (DateUtil.isNotInRange(minAge, maxAge, birthDate)) {
            throw new BadRequestException(String.format("the birthdate [%s] is not in range[%d, %d] " +
                            "for category selected", birthDate.format(DateUtil.FORMATTER),
                    minAge, maxAge));
        }
    }


}
