package com.fcctech.tournament.service.impl;

import com.fcctech.tournament.entity.*;
import com.fcctech.tournament.exception.BadRequestException;
import com.fcctech.tournament.exception.BusinessException;
import com.fcctech.tournament.exception.NotFoundException;
import com.fcctech.tournament.payload.request.ParticipantTeamRequest;
import com.fcctech.tournament.payload.request.TournamentRequest;
import com.fcctech.tournament.repository.*;
import com.fcctech.tournament.service.TournamentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
@Slf4j
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParticipantTeamRepository participantTeamRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Tournament createTournament(TournamentRequest request, User user) {
        Tournament tournament = new Tournament();
        tournament.setName(request.getName());
        tournament.setDateCreated(new Date());
        tournament.setDescription(request.getDescription());
        tournament.setStatus(TournamentStatus.OPEN);
        tournament.setDateStarted(request.getDateStarted());
        tournament.setDateEnd(request.getDateEnd());
        tournament.setOwner(user);
        tournament.setTournamentType(tournamentRepository.findType(request.getType()).orElseThrow(() -> new NotFoundException("type not found")));
        return tournamentRepository.save(tournament);
    }

    @Override
    public Optional<Tournament> findById(Long id) {
        return tournamentRepository.findById(id);
    }

    @Override
    public void updateTournament(Tournament tournament, TournamentRequest request) {
        tournament.setName(request.getName());
        tournament.setDescription(request.getDescription());
        tournament.setDateStarted(request.getDateStarted());
        tournament.setDateEnd(request.getDateEnd());
        TournamentStatus status = TournamentStatus.from(request.getStatus());
        if (null != status) {
            tournament.setStatus(status);
        }
        tournament.setDateUpdated(new Date());

        if (!tournament.getTournamentType().getName().equals(request.getType())) {
            tournament.setTournamentType(tournamentRepository.findType(request.getType()).orElseThrow(() -> new NotFoundException("type not found")));
        }

        tournamentRepository.save(tournament);
    }

    public Page<Tournament> listOwnerTournament(Long ownerId, int page, int size, String sortDir, String sortField) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sortField);
        return tournamentRepository.findTournamentByOwner(ownerId, pageRequest);
    }

    @Override
    public void subscribeMe(Long userId, Long tournamentId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new NotFoundException("tournament not found"));
        Optional<ParticipantTeam> team = participantTeamRepository.findBySubscriberAndTournamentId(userId, tournamentId);
        if (team.isPresent()) {
            throw new BusinessException("already subscribe", HttpStatus.BAD_REQUEST);
        }
        Account account = accountRepository.findByOwnerId(userId).orElseThrow(() -> new BadRequestException("user doesn't have an account"));

        ParticipantTeam participantTeam = new ParticipantTeam();
        participantTeam.setTournament(tournament);
        participantTeam.setEmail(user.getEmail());
        participantTeam.setSubscriber(user);
        participantTeam.setName(account.getAssociationName());

        participantTeamRepository.save(participantTeam);
    }

    @Override
    public void subscribeTeam(Long userId, Long tournamentId, ParticipantTeamRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new NotFoundException("tournament not found"));
        Optional<ParticipantTeam> team = participantTeamRepository.findByTournamentIdAndEmail(tournamentId, request.getEmail());
        if (team.isPresent()) {
            throw new BusinessException("already subscribe", HttpStatus.BAD_REQUEST);
        }

        ParticipantTeam participantTeam = new ParticipantTeam();
        participantTeam.setTournament(tournament);
        participantTeam.setEmail(request.getEmail());
        participantTeam.setSubscriber(user);
        participantTeam.setName(request.getAssociationName());

        participantTeamRepository.save(participantTeam);
    }

}
