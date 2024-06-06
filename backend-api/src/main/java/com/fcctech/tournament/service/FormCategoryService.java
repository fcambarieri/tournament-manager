package com.fcctech.tournament.service;

import com.fcctech.tournament.entity.CombatCategory;
import com.fcctech.tournament.entity.FormCategory;
import com.fcctech.tournament.payload.request.FormCategoryRequest;

import java.util.List;

public interface FormCategoryService {
    FormCategory createFormCategory(Long userId, Long tournamentId, FormCategoryRequest request);

    void updateFormCategory(Long userId, Long tournamentId, Long categoryId, FormCategoryRequest request);

    FormCategory getFormCategory(Long tournamentId, Long categoryId);

    List<FormCategory> getAllFormCategory(Long tournamentId);
}
