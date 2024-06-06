package com.fcctech.tournament.controller;


import com.fcctech.tournament.entity.CombatCategory;
import com.fcctech.tournament.payload.request.CombatCategoryRequest;
import com.fcctech.tournament.payload.response.CombatCategoryResponse;
import com.fcctech.tournament.payload.response.EntityIdResponse;
import com.fcctech.tournament.payload.response.IntPair;
import com.fcctech.tournament.security.UserPrincipal;
import com.fcctech.tournament.service.CombatCategoryService;
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
public class CombatCategoryController {

    @Autowired
    private CombatCategoryService combatCategoryService;

    @PostMapping("/combat_categories")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntityIdResponse createCombatCategory(@AuthenticationPrincipal UserPrincipal principal,
                                                 @PathVariable("tournamentId") Long tournamentId,
                                                 @RequestBody CombatCategoryRequest request) {

        CombatCategory category = combatCategoryService.createCombatCategory(principal.getUserId(), tournamentId, request);

        return EntityIdResponse.builder().id(category.getId()).build();
    }

    @PutMapping("/combat_categories/{categoryId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateCombatCategory(@AuthenticationPrincipal UserPrincipal principal,
                                     @PathVariable("tournamentId") Long tournamentId,
                                     @PathVariable("categoryId") Long categoryId,
                                     @RequestBody CombatCategoryRequest request) {

        combatCategoryService.updateCombatCategory(principal.getUserId(), tournamentId, categoryId, request);
    }

    @GetMapping("/combat_categories/{categoryId}")
    @ResponseStatus(code = HttpStatus.OK)
    public CombatCategoryResponse getCombatCategory(@PathVariable("tournamentId") Long tournamentId,
                                                    @PathVariable("categoryId") Long categoryId) {

        CombatCategory category = combatCategoryService.findByTournamentIdAndId(tournamentId, categoryId);

        return to(category);
    }

    @GetMapping("/combat_categories")
    @ResponseStatus(code = HttpStatus.OK)
    public List<CombatCategoryResponse> findAllCombatCategory(@PathVariable("tournamentId") Long tournamentId) {

        List<CombatCategory> categories = combatCategoryService.findByTournamentId(tournamentId);

        return categories.stream().map(this::to).collect(Collectors.toList());
    }

    private CombatCategoryResponse to(CombatCategory category) {
        return CombatCategoryResponse
                .builder()
                .id(category.getId())
                .ageCategory(category.getAgeCategory().getId())
                .sex(category.getSex())
                .agesRange(IntPair.builder()
                        .from(category.getAgeCategory().getMinAge())
                        .to(category.getAgeCategory().getMaxAge())
                        .build())
                .weightRange(IntPair.builder()
                        .from(category.getMinWeight())
                        .to(category.getMaxWeight())
                        .build())
                .name(category.getName())
                .maxParticipant(category.getMaxParticipant())
                .type(category.getType())
                .build();
    }
}
