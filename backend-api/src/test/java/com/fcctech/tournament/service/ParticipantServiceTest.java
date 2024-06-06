package com.fcctech.tournament.service;


import com.fcctech.tournament.TestDomainHelper;
import com.fcctech.tournament.domain.Action;
import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.entity.CombatCategory;
import com.fcctech.tournament.entity.Modality;
import com.fcctech.tournament.entity.Participant;
import com.fcctech.tournament.entity.Tournament;
import com.fcctech.tournament.exception.BadRequestException;
import com.fcctech.tournament.payload.request.ModalityRequest;
import com.fcctech.tournament.payload.request.ParticipantRequest;
import com.fcctech.tournament.repository.CombatCategoryRepository;
import com.fcctech.tournament.repository.ParticipantRepository;
import com.fcctech.tournament.repository.FormCategoryRepository;
import com.fcctech.tournament.service.impl.ParticipantServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ParticipantServiceTest {

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private CombatCategoryRepository combatCategoryRepository;

    @Mock
    private FormCategoryRepository formCategoryRepository;


    @InjectMocks
    private ParticipantServiceImp participantServiceImp;

    private Tournament tournament;
    private Participant participant;

    private CombatCategory fightCategory;

    private Long id = 15L;

    @BeforeEach
    public void setUp() {

        tournament = TestDomainHelper.defaultTournament();
        participant = TestDomainHelper.defaultParticipant(id, tournament);
        fightCategory = TestDomainHelper.defaultFightCategory();

        given(participantRepository.findById(id)).willReturn(Optional.of(participant));
        given(combatCategoryRepository.findById(1L)).willReturn(Optional.of(fightCategory));

    }

    @Test
    public void update_participant_append_one_modality() {
        participantServiceImp.updateParticipant(1L, id, 1L, 1L, TestDomainHelper.defaultParticipantRequest());

        verify(participantRepository, times(1)).save(any(Participant.class));

        Assertions.assertFalse(participant.getModalities().isEmpty());

        Modality m = participant.getModalities().iterator().next();
        Assertions.assertEquals(1L, m.getCategory().getId());
    }

    @Test
    public void update_participant_remove_one_modality() {
        participant.getModalities().add(TestDomainHelper.defaultModality());
        ParticipantRequest request = TestDomainHelper.defaultParticipantRequest();
        request.setModalities(new ArrayList<>());

        request.getModalities().add(ModalityRequest.builder()
                .action(Optional.of(Action.REMOVE))
                .id(1L)
                .build());

        participantServiceImp.updateParticipant(1L, id, 1L, 1L, request);

        verify(participantRepository, times(1)).save(any(Participant.class));

        Assertions.assertTrue(participant.getModalities().isEmpty());
    }

    @Test
    public void update_participant_update_one_modality() {

        CombatCategory newFightCategory = TestDomainHelper.defaultFightCategory();
        newFightCategory.setId(3L);

        given(combatCategoryRepository.findById(3L)).willReturn(Optional.of(newFightCategory));

        Modality modality = TestDomainHelper.defaultModality();
        participant.getModalities().add(modality);

        ParticipantRequest request = TestDomainHelper.defaultParticipantRequest();
        request.setModalities(new ArrayList<>());

        request.getModalities().add(ModalityRequest.builder()
                .action(Optional.of(Action.UPDATE))
                .type(ModalityType.COMBAT)
                .id(1L)
                .categoryId(3L)
                .build());

        participantServiceImp.updateParticipant(1L, id, 1L, 1L, request);

        verify(participantRepository, times(1)).save(any(Participant.class));

        Assertions.assertEquals(3L, modality.getCategory().getId());
    }


    @Test
    public void update_participant_append_one_modality_weight_error() {
        ParticipantRequest request = TestDomainHelper.defaultParticipantRequest();
        request.setWeight(50D);
        Assertions.assertThrows(BadRequestException.class, () -> {
            participantServiceImp.updateParticipant(1L, id, 1L, 1L, request);
        });
        verify(participantRepository, never()).save(any(Participant.class));
    }

    @Test
    public void update_participant_append_one_modality_age_error() {
        ParticipantRequest request = TestDomainHelper.defaultParticipantRequest();
        request.setDateBirth(LocalDate.of(2012, 10, 4));
        Assertions.assertThrows(BadRequestException.class, () -> {
            participantServiceImp.updateParticipant(1L, id, 1L, 1L, request);
        });
        verify(participantRepository, never()).save(any(Participant.class));
    }


}
