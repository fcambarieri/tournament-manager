package com.fcctech.tournament.service.impl;

import com.fcctech.tournament.entity.AgeCategory;
import com.fcctech.tournament.entity.BeltCategory;
import com.fcctech.tournament.entity.CombatCategory;
import com.fcctech.tournament.entity.Tournament;
import com.fcctech.tournament.exception.BadRequestException;
import com.fcctech.tournament.exception.ForbiddenException;
import com.fcctech.tournament.exception.TournamentAccessException;
import com.fcctech.tournament.exception.TournamentNotFoundException;
import com.fcctech.tournament.payload.request.CombatCategoryRequest;
import com.fcctech.tournament.repository.BeltCategoryRepository;
import com.fcctech.tournament.repository.CategoryRepository;
import com.fcctech.tournament.repository.CombatCategoryRepository;
import com.fcctech.tournament.repository.TournamentRepository;
import com.fcctech.tournament.service.CombatCategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class CombatCategoryServiceImp implements CombatCategoryService {

    @Autowired
    private CombatCategoryRepository combatCategoryRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CombatCategory createCombatCategory(Long userId, Long tournamentId, CombatCategoryRequest request) {
        validateWeight(request);

        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(TournamentNotFoundException::new);
        tournament.assertOwner(userId);

        List<CombatCategory> categories = combatCategoryRepository.findByTournamentIdAndSexAndAgeCategory(tournamentId, request.getSex(), request.getCategoryId());
        validateRangeWeights(categories, request.getMinWeight(), request.getMaxWeight());

        AgeCategory ageCategory = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new BadRequestException("category_id not found"));

        CombatCategory category = new CombatCategory();
        category.setTournament(tournament);
        category.setName(request.getName());
        category.setSex(request.getSex());
        category.setMaxParticipant(request.getMaxParticipants());
        category.setMinWeight(request.getMinWeight());
        category.setMaxWeight(request.getMaxWeight());
        category.setAgeCategory(ageCategory);
        category.setType(request.getType());
        category.setFormat(request.getFormat());

        return combatCategoryRepository.save(category);
    }

    public void validateWeight(CombatCategoryRequest category) {
        if (category.getMinWeight() >= category.getMaxWeight()) {
            throw new BadRequestException("min_weight is bigger or equal than max_weight");
        }
    }

    public void validateRangeWeights(List<CombatCategory> categories, int reqMinWeight, int reqMaxWeight) {
        categories.forEach(category -> {
            int min = category.getMinWeight();
            int max = category.getMaxWeight();

            if (reqMinWeight >= min && reqMinWeight <= max) {
                throw new BadRequestException(String.format("min [%d] value has conflict with in category %d range [%d, %d]",
                        reqMinWeight, category.getId(), min, max));
            }

            if (reqMaxWeight >= min && reqMaxWeight <= max) {
                throw new BadRequestException(String.format("max [%d] value has conflict with in category %d range [%d, %d]",
                        reqMaxWeight, category.getId(), min, max));
            }

            if (reqMinWeight < min && reqMaxWeight > max) {
                throw new BadRequestException(String.format("max [%d] value has conflict with in category %s range [%d, %d]",
                        reqMaxWeight, category.getId(), min, max));
            }
        });
    }


    @Override
    public void updateCombatCategory(Long userId, Long tournamentId, Long categoryId, CombatCategoryRequest request) {
        validateWeight(request);

        CombatCategory category = combatCategoryRepository.findById(categoryId).orElseThrow(() -> new BadRequestException("category_id not found"));
        category.getTournament().assertOwner(userId);

        if (!category.getTournament().getId().equals(tournamentId)) {
            throw new BadRequestException("category doesn't belongs to tournament");
        }

        List<CombatCategory> categories = combatCategoryRepository.findByTournamentIdAndSexAndAgeCategory(tournamentId, request.getSex(), request.getCategoryId());
        categories.remove(category);

        validateRangeWeights(categories, request.getMinWeight(), request.getMaxWeight());

        category.setName(request.getName());
        category.setSex(request.getSex());
        category.setMaxParticipant(request.getMaxParticipants());
        category.setMinWeight(request.getMinWeight());
        category.setMaxWeight(request.getMaxWeight());
        category.setType(request.getType());
        category.setFormat(request.getFormat());

        if (!category.getAgeCategory().getId().equals(request.getCategoryId())) {
            AgeCategory ageCategory = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new BadRequestException("category_id not found"));
            category.setAgeCategory(ageCategory);
        }

        combatCategoryRepository.save(category);
    }

    @Override
    public CombatCategory findByTournamentIdAndId(Long tournamentId, Long categoryId) {
        return combatCategoryRepository.findByTournamentIdAndId(tournamentId, categoryId).orElseThrow(() -> new BadRequestException("category_id not found"));
    }

    @Override
    public List<CombatCategory> findByTournamentId(Long tournamentId) {
        return combatCategoryRepository.findByTournamentId(tournamentId);
    }
}
