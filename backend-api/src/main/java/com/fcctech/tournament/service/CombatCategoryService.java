package com.fcctech.tournament.service;

import com.fcctech.tournament.entity.CombatCategory;
import com.fcctech.tournament.payload.request.CombatCategoryRequest;

import java.util.List;

public interface CombatCategoryService {
    CombatCategory createCombatCategory(Long userId, Long tournamentId, CombatCategoryRequest request);

    void updateCombatCategory(Long userId, Long tournamentId, Long categoryId, CombatCategoryRequest request);

    CombatCategory findByTournamentIdAndId(Long tournamentId, Long categoryId);

    List<CombatCategory> findByTournamentId(Long tournamentId);
}
