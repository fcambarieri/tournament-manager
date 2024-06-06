package com.fcctech.tournament.service.impl;

import com.fcctech.tournament.entity.AgeCategory;
import com.fcctech.tournament.entity.Tournament;
import com.fcctech.tournament.exception.BadRequestException;
import com.fcctech.tournament.exception.NotFoundException;
import com.fcctech.tournament.exception.TournamentNotFoundException;
import com.fcctech.tournament.payload.request.AgeCategoryRequest;
import com.fcctech.tournament.repository.CategoryRepository;
import com.fcctech.tournament.repository.ParticipantRepository;
import com.fcctech.tournament.repository.TournamentRepository;
import com.fcctech.tournament.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
@Slf4j
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public AgeCategory createCategory(Long tournamentId, Long userId, AgeCategoryRequest request) {

        validateRangeValues(request);

        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(TournamentNotFoundException::new);
        tournament.assertOwner(userId);

        List<AgeCategory> categories = categoryRepository.findAllCategoriesByTournamentId(tournamentId);

        validateCategory(request, categories);

        AgeCategory category = new AgeCategory();
        category.setName(request.getName());
        category.setMinAge(request.getMinAge());
        category.setMaxAge(request.getMaxAge());
        category.setTournament(tournament);

        return categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Long tournamentId, Long userId, Long categoryId, AgeCategoryRequest request) {
        AgeCategory category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("category not found"));

        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(TournamentNotFoundException::new);
        tournament.assertOwner(userId);

        List<AgeCategory> categories = categoryRepository.findAllCategoriesByTournamentId(tournamentId);
        categories.remove(category);

        validateCategory(request, categories);

        if (participantRepository.existsParticipantInCategory(tournamentId, categoryId)) {
            throw new BadRequestException("You can't modify a category with participant in it");
        }

        category.setName(request.getName());
        category.setMinAge(request.getMinAge());
        category.setMaxAge(request.getMaxAge());
        category.setTournament(tournament);

        categoryRepository.save(category);
    }

    @Override
    public AgeCategory getCategory(Long tournamentId, Long categoryId) {
        AgeCategory category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("category not found"));
        if (!category.getTournament().getId().equals(tournamentId)) {
            throw new BadRequestException("the category doesn't belong to tournament");
        }
        return category;
    }

    @Override
    public List<AgeCategory> getAllCategories(Long tournamentId) {
        return categoryRepository.findAllCategoriesByTournamentId(tournamentId);
    }

    public void validateRangeValues(AgeCategoryRequest request) {
        int min = request.getMinAge();
        int max = request.getMaxAge();

        if (min >= max) {
            throw new BadRequestException("min value is bigger or equals to max");
        }
    }

    public void validateCategory(AgeCategoryRequest request, List<AgeCategory> categories) {
        categories.forEach(ageCategory -> {
            if (ageCategory.getName().equals(request.getName())) {
                throw new BadRequestException("name is already taken");
            }

            int min = ageCategory.getMinAge();
            int max = ageCategory.getMaxAge();
            int reqMin = request.getMinAge();
            int reqMax = request.getMaxAge();

            if (reqMin >= min && reqMin <= max) {
                throw new BadRequestException(String.format("min [%d] value has conflict with in category %s range [%d, %d]",
                       reqMin, ageCategory.getName(), ageCategory.getMinAge(), ageCategory.getMaxAge()));
            }

            if (reqMax >= min && reqMax <= max) {
                throw new BadRequestException(String.format("max [%d] value has conflict with in category %s range [%d, %d]",
                        reqMax, ageCategory.getName(), ageCategory.getMinAge(), ageCategory.getMaxAge()));
            }

            if (reqMin < min && reqMax > max) {
                throw new BadRequestException(String.format("max [%d] value has conflict with in category %s range [%d, %d]",
                        reqMax, ageCategory.getName(), ageCategory.getMinAge(), ageCategory.getMaxAge()));
            }
        });
    }
}
