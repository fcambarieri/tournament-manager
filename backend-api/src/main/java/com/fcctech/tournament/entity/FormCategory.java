package com.fcctech.tournament.entity;

import com.fcctech.tournament.domain.SexType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "form_categories")
@PrimaryKeyJoinColumn(name="id")
public class FormCategory extends BaseCategory{

    private Integer minAge;

    private Integer maxAge;

    @Override
    @Transient
    public String display() {
        return String.format("%s [%d, %d]", getName(), minAge, maxAge);
    }

    public Integer getMinYear() {
        return minAge;
    }

    public Integer getMaxYear() {
        return maxAge;
    }
}
