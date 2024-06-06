package com.fcctech.tournament.payload.response.competition;

import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.SexType;
import com.fcctech.tournament.entity.BaseCategory;
import com.fcctech.tournament.entity.BeltCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.Objects;

@Getter
@Setter
@Builder
public class CompositionKey implements Comparable<CompositionKey>{

    private BaseCategory baseCategory;
    private BeltCategory belt;
    private SexType sex;
    private ModalityType modality;
    private Long categoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositionKey that = (CompositionKey) o;

        if (!Objects.equals(belt, that.belt)) return false;
        if (sex != that.sex) return false;
        if (modality != that.modality) return false;
        return Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        int result = belt != null ? belt.hashCode() : 0;
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (modality != null ? modality.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CompositionKey{" +
                 belt +
                ", sex=" + sex +
                ", modality=" + modality +
                ", categoryId=" + categoryId +
                '}';
    }

    @Override
    public int compareTo(CompositionKey o) {
        return Comparator
                .comparing((CompositionKey ck) -> ck.getBaseCategory().getMinYear())
                .thenComparing(CompositionKey::getModality)
                .compare(this, o);
    }


}
