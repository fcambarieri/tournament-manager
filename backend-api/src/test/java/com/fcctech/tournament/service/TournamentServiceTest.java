package com.fcctech.tournament.service;


import com.fcctech.tournament.entity.Account;
import com.fcctech.tournament.entity.ParticipantTeam;
import com.fcctech.tournament.entity.Tournament;
import com.fcctech.tournament.entity.User;
import com.fcctech.tournament.exception.BadRequestException;
import com.fcctech.tournament.exception.BusinessException;
import com.fcctech.tournament.exception.NotFoundException;
import com.fcctech.tournament.payload.request.ParticipantTeamRequest;
import com.fcctech.tournament.repository.AccountRepository;
import com.fcctech.tournament.repository.ParticipantTeamRepository;
import com.fcctech.tournament.repository.TournamentRepository;
import com.fcctech.tournament.repository.UserRepository;
import com.fcctech.tournament.service.impl.TournamentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TournamentServiceTest {

    @Mock private TournamentRepository tournamentRepository;

    @Mock private UserRepository userRepository;

    @Mock private ParticipantTeamRepository participantTeamRepository;

    @Mock private AccountRepository accountRepository;
    @InjectMocks
    private TournamentServiceImpl tournamentService;

    private User user ;
    private Tournament tournament;

    private ParticipantTeam team;

    private Account account;

    private ParticipantTeamRequest request;

    @BeforeEach
    public void setup() {
        user = new User(); user.setId(2L);
        tournament = new Tournament(); tournament.setId(1L);
        team = new ParticipantTeam(); team.setId(1L);
        account = new Account(); account.setId(1L);
        account.setOwner(user);
        request = ParticipantTeamRequest.builder()
                .associationName("Escuela Andina")
                .email("test@hotmail.com")
                .build();
        given(userRepository.findById(2L)).willReturn(Optional.of(user));
        given(tournamentRepository.findById(1L)).willReturn(Optional.of(tournament));
        given(participantTeamRepository.findBySubscriberAndTournamentId(1L, 1L)).willReturn(Optional.of(team));
        given(accountRepository.findByOwnerId(2L)).willReturn(Optional.of(account));
    }

    @Test
    public void subscribeMe_user_subscribe_to_tournament() {

        tournamentService.subscribeMe(2L, 1L);

        verify(participantTeamRepository, times(1)).save(any(ParticipantTeam.class));
    }


    @Test
    public void subscribeMe_user_not_found() {

        Assertions.assertThrows(NotFoundException.class, () -> {
            tournamentService.subscribeMe(1L, 1L);
        });

        verify(participantTeamRepository, never()).save(any(ParticipantTeam.class));
    }

    @Test
    public void subscribeMe_tournament_not_found() {

        Assertions.assertThrows(NotFoundException.class, () -> {
            tournamentService.subscribeMe(2L, 4L);
        });

        verify(participantTeamRepository, never()).save(any(ParticipantTeam.class));
    }

    @Test
    public void subscribeMe_user_already_subscribe() {

        given(participantTeamRepository.findBySubscriberAndTournamentId(2L, 1L)).willReturn(Optional.of(team));

        Assertions.assertThrows(BusinessException.class, () -> {
            tournamentService.subscribeMe(2L, 1L);
        });

        verify(participantTeamRepository, never()).save(any(ParticipantTeam.class));
    }

    @Test
    public void subscribeMe_user_without_account() {
        given(accountRepository.findByOwnerId(2L)).willReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class, () -> {
            tournamentService.subscribeMe(2L, 1L);
        });

        verify(participantTeamRepository, never()).save(any(ParticipantTeam.class));
    }

    @Test
    public void subscribeTeam_subscribe_to_tournament() {

        tournamentService.subscribeTeam(2L, 1L, request);

        verify(participantTeamRepository, times(1)).save(any(ParticipantTeam.class));
    }

    @Test
    public void subscribeTeam_user_already_register() {
        given(participantTeamRepository.findByTournamentIdAndEmail(any(), any())).willReturn(Optional.of(team));

        Assertions.assertThrows(BusinessException.class, () -> {
            tournamentService.subscribeTeam(2L, 1L, request);
        });

        verify(participantTeamRepository, never()).save(any(ParticipantTeam.class));
    }


}
