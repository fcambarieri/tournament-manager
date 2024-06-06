package com.fcctech.tournament.controller;

import com.fcctech.tournament.entity.AgeCategory;
import com.fcctech.tournament.payload.request.AgeCategoryRequest;
import com.fcctech.tournament.payload.response.AgeCategoryResponse;
import com.fcctech.tournament.security.UserPrincipal;
import com.fcctech.tournament.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tournaments/{tournamentId}")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/categories")
    @ResponseStatus(code = HttpStatus.CREATED)
    public AgeCategoryResponse createCategory(@AuthenticationPrincipal UserPrincipal principal,
                                              @PathVariable("tournamentId") Long tournamentId,
                                              @RequestBody AgeCategoryRequest request) {
        AgeCategory category = categoryService.createCategory(tournamentId, principal.getUserId(), request);
        return AgeCategoryResponse.builder()
                .id(category.getId())
                .minAge(category.getMinAge())
                .maxAge(category.getMaxAge())
                .name(category.getName())
                .build();
    }

    @PutMapping("/categories/{categoryId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateCategory(@AuthenticationPrincipal UserPrincipal principal,
                               @PathVariable("tournamentId") Long tournamentId,
                               @PathVariable("categoryId") Long categoryId,
                               @RequestBody AgeCategoryRequest request) {

        categoryService.updateCategory(tournamentId, principal.getUserId(), categoryId, request);

    }

    @GetMapping("/categories/{categoryId}")
    @ResponseStatus(code = HttpStatus.OK)
    public AgeCategoryResponse getCategory(
            @PathVariable("tournamentId") Long tournamentId,
            @PathVariable("categoryId") Long categoryId) {
        AgeCategory category = categoryService.getCategory(tournamentId, categoryId);
        return AgeCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .minAge(category.getMinAge())
                .maxAge(category.getMaxAge())
                .build();
    }

    @GetMapping("/categories")
    @ResponseStatus(code = HttpStatus.OK)
    public List<AgeCategoryResponse> getAllCategories(
            @PathVariable("tournamentId") Long tournamentId) {
        List<AgeCategory> categories = categoryService.getAllCategories(tournamentId);
        return categories.stream().map(category -> AgeCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .minAge(category.getMinAge())
                .maxAge(category.getMaxAge())
                .build()).collect(Collectors.toList());


    }
}
