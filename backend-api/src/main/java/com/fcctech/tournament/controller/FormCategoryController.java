package com.fcctech.tournament.controller;


import com.fcctech.tournament.entity.CombatCategory;
import com.fcctech.tournament.entity.FormCategory;
import com.fcctech.tournament.payload.request.CombatCategoryRequest;
import com.fcctech.tournament.payload.request.FormCategoryRequest;
import com.fcctech.tournament.payload.response.EntityIdResponse;
import com.fcctech.tournament.payload.response.FormCategoryResponse;
import com.fcctech.tournament.payload.response.IntPair;
import com.fcctech.tournament.repository.FormCategoryRepository;
import com.fcctech.tournament.security.UserPrincipal;
import com.fcctech.tournament.service.FormCategoryService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequiredArgsConstructor
@RequestMapping("/tournaments/{tournamentId}")
public class FormCategoryController {

    @Autowired
    private FormCategoryService formCategoryService;

    @PostMapping("/form_categories")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntityIdResponse createCombatCategory(@AuthenticationPrincipal UserPrincipal principal,
                                                 @PathVariable("tournamentId") Long tournamentId,
                                                 @RequestBody FormCategoryRequest request) {

        FormCategory category = formCategoryService.createFormCategory(principal.getUserId(), tournamentId, request);

        return EntityIdResponse.builder().id(category.getId()).build();
    }

    @PutMapping("/form_categories/{categoryId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateCombatCategory(@AuthenticationPrincipal UserPrincipal principal,
                                                 @PathVariable("tournamentId") Long tournamentId,
                                                 @PathVariable("categoryId") Long categoryId,
                                                 @RequestBody FormCategoryRequest request) {

        formCategoryService.updateFormCategory(principal.getUserId(), tournamentId, categoryId, request);

    }

    @GetMapping("/form_categories/{categoryId}")
    @ResponseStatus(code = HttpStatus.OK)
    public FormCategoryResponse getFormCategory(@PathVariable("tournamentId") Long tournamentId,
                                                @PathVariable("categoryId") Long categoryId) {

        FormCategory category = formCategoryService.getFormCategory(tournamentId, categoryId);

        return to(category);
    }

    @GetMapping("/form_categories")
    @ResponseStatus(code = HttpStatus.OK)
    public List<FormCategoryResponse> getFormCategories(@PathVariable("tournamentId") Long tournamentId) {

        List<FormCategory> categories = formCategoryService.getAllFormCategory(tournamentId);

        return categories.stream()
                .map(this::to)
                .collect(Collectors.toList());
    }

    private FormCategoryResponse to(@NotNull FormCategory category) {
        return FormCategoryResponse.builder()
                .type(category.getType())
                .name(category.getName())
                .maxParticipant(category.getMaxParticipant())
                .agesRange(IntPair.builder()
                        .from(category.getMinAge())
                        .to(category.getMaxAge())
                        .build())
                .id(category.getId())
                .sex(category.getSex())
                .build();
    }
}
