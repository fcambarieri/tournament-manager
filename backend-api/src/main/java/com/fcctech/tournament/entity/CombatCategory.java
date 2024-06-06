package com.fcctech.tournament.entity;

import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.SexType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "combat_categories")
@PrimaryKeyJoinColumn(name="id")
public class CombatCategory extends BaseCategory {

    private Integer minWeight;

    private Integer maxWeight;

    @ManyToOne
    @JoinColumn(name="age_category_id", nullable=false)
    private AgeCategory ageCategory;



    public boolean isInWeightRange(Double weight) {
        return weight >= minWeight && weight <= maxWeight;
    }

    public boolean isNotInWeightRange(Double weight) {
        return !isInWeightRange(weight);
    }

    @Override
    @Transient
    public String display() {
        String name = getName() != null ? getName() : "";
        return String.format("%s %s %s %d-%d - kg %d-%d ", name,
                getSex().name().toLowerCase(),
                getAgeCategory().getName(),
                ageCategory.getMinAge(),
                ageCategory.getMaxAge(),
                getMinWeight(),
                getMaxWeight());
    }

    @Override
    public Integer getMinYear() {
        return getAgeCategory().getMinAge();
    }

    @Override
    public Integer getMaxYear() {
        return getAgeCategory().getMaxAge();
    }
}
