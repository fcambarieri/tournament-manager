package com.fcctech.tournament.service;

import com.fcctech.tournament.entity.AgeCategory;
import com.fcctech.tournament.payload.request.AgeCategoryRequest;

import java.util.List;

public interface CategoryService {

    AgeCategory createCategory(Long tournamentId, Long userId, AgeCategoryRequest request);

    void updateCategory(Long tournamentId, Long userId, Long categoryId, AgeCategoryRequest request);

    AgeCategory getCategory(Long tournamentId, Long categoryId);

    List<AgeCategory> getAllCategories(Long tournamentId);
}
