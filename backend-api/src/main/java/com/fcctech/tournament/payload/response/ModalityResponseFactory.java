package com.fcctech.tournament.payload.response;

import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.entity.AgeCategory;
import com.fcctech.tournament.entity.CombatCategory;
import com.fcctech.tournament.entity.FormCategory;
import com.fcctech.tournament.entity.Modality;

public class ModalityResponseFactory {

    public static ModalityResponse build(Modality modality) {
        if (modality.getCategory() instanceof CombatCategory) {
            CombatCategory combatCategory = (CombatCategory) modality.getCategory();
            return ModalityFightResponse.builder()
                    .categoryId(combatCategory.getId())
                    .name(combatCategory.getAgeCategory().getName())
                    .ageRange(IntPair.builder()
                            .from(combatCategory.getMinWeight())
                            .to(combatCategory.getMaxWeight())
                            .build())
                    .weightRange(IntPair.builder()
                            .from(combatCategory.getMinWeight())
                            .to(combatCategory.getMaxWeight())
                            .build())
                    .id(modality.getId())
                    .type(modality.getType())
                    .build();
        } else {
            FormCategory formCategory = (FormCategory) modality.getCategory();

            return ModalityStyleResponse.builder()
                    .id(modality.getId())
                    .categoryId(formCategory.getId())
                    .type(modality.getType())
                    .name(formCategory.getName())
                    .agesRange(IntPair.builder()
                            .from(formCategory.getMinAge())
                            .to(formCategory.getMaxAge())
                            .build())
                    .build();
        }
    }

    private static AgeCategoryResponse buildAgeCategory(AgeCategory ageCategory) {
        return AgeCategoryResponse.builder()
                .id(ageCategory.getId())
                .minAge(ageCategory.getMinAge())
                .maxAge(ageCategory.getMaxAge())
                .name(ageCategory.getName())
                .build();
    }
}
