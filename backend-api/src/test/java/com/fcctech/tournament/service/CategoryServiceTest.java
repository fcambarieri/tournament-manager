package com.fcctech.tournament.service;

import com.fcctech.tournament.TestDomainHelper;
import com.fcctech.tournament.entity.AgeCategory;
import com.fcctech.tournament.entity.Tournament;
import com.fcctech.tournament.entity.User;
import com.fcctech.tournament.exception.BadRequestException;
import com.fcctech.tournament.exception.NotFoundException;
import com.fcctech.tournament.exception.TournamentAccessException;
import com.fcctech.tournament.payload.request.AgeCategoryRequest;
import com.fcctech.tournament.repository.CategoryRepository;
import com.fcctech.tournament.repository.ParticipantRepository;
import com.fcctech.tournament.repository.TournamentRepository;
import com.fcctech.tournament.service.impl.CategoryServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CategoryServiceTest {

    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private CategoryServiceImp categoryServiceImp;

    private AgeCategory category;
    private Tournament tournament;

    @BeforeEach
    public void setUp() {
        category = new AgeCategory();
        category.setId(1L);
        category.setName("adults");
        category.setMinAge(10);
        category.setMinAge(14);

        User user = new User();
        user.setId(1L);
        tournament = TestDomainHelper.defaultTournament();
        tournament.setOwner(user);

        category.setTournament(tournament);
    }

    @Test
    public void category_not_found() {
        given(categoryRepository.findById(any())).willReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> {
            categoryServiceImp.updateCategory(1L, 1L, 1L, AgeCategoryRequest.builder().build());
        });

        verify(tournamentRepository, never()).findById(any());
    }

    @Test
    public void user_is_not_owner() {
        given(categoryRepository.findById(any())).willReturn(Optional.of(category));
        given(tournamentRepository.findById(any())).willReturn(Optional.of(tournament));


        Assertions.assertThrows(TournamentAccessException.class, () -> {
            categoryServiceImp.updateCategory(1L, 3L, 1L, AgeCategoryRequest.builder().build());
        });

        verify(categoryRepository, never()).findAllCategoriesByTournamentId(1L);
    }

    @Test
    public void cant_modify_category_participant_exists() {
        given(categoryRepository.findById(any())).willReturn(Optional.of(category));
        given(tournamentRepository.findById(any())).willReturn(Optional.of(tournament));
        given(participantRepository.existsParticipantInCategory(any(), any())).willReturn(true);

        BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
            categoryServiceImp.updateCategory(1L, 1L, 1L, AgeCategoryRequest.builder().build());
        });

        Assertions.assertEquals("You can't modify a category with participant in it", exception.getMessage());
    }

    @Test
    public void category_doesnt_belogn_to_tournament() {
        given(categoryRepository.findById(any())).willReturn(Optional.of(category));

        BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
            categoryServiceImp.getCategory(3L, 1L);
        });

        Assertions.assertEquals("the category doesn't belong to tournament", exception.getMessage());
    }

    @Test
    public void invalid_range_values() {
        BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
            categoryServiceImp.validateRangeValues(AgeCategoryRequest.builder().minAge(51).maxAge(20).build());
        });

        Assertions.assertEquals("min value is bigger or equals to max", exception.getMessage());
    }

    @Test
    public void validation_ok_new_category() {
        AgeCategoryRequest request = AgeCategoryRequest.builder()
                .name("one")
                .minAge(10)
                .maxAge(13)
                .build();

        List<AgeCategory> categories = new ArrayList<>();
        AgeCategory category = new AgeCategory();
        category.setName("two");
        category.setMinAge(7);
        category.setMaxAge(9);
        categories.add(category);


        Assertions.assertDoesNotThrow(() -> {
            categoryServiceImp.validateCategory(request, categories);
        });
    }

    @Test
    public void range_validation() {
        List<AgeCategoryRequest> requests = Arrays.asList(
                AgeCategoryRequest.builder()
                        .name("two")
                        .minAge(9)
                        .maxAge(13)
                        .build(),
                AgeCategoryRequest.builder()
                        .name("one1")
                        .minAge(9)
                        .maxAge(13)
                        .build(),
                AgeCategoryRequest.builder()
                        .name("one1")
                        .minAge(12)
                        .maxAge(13)
                        .build(),
                AgeCategoryRequest.builder()
                        .name("one1")
                        .minAge(12)
                        .maxAge(16)
                        .build(),
                AgeCategoryRequest.builder()
                        .name("one1")
                        .minAge(9)
                        .maxAge(16)
                        .build()
        );

        List<AgeCategory> categories = new ArrayList<>();
        AgeCategory category = new AgeCategory();
        category.setName("two");
        category.setMinAge(10);
        category.setMaxAge(15);
        categories.add(category);


        requests.forEach(request -> {
            Assertions.assertThrows(BadRequestException.class, () -> {
                categoryServiceImp.validateCategory(request, categories);
            });
        });
    }
}
