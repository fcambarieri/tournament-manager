package com.fcctech.tournament.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BeltCategory {
    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeltCategory that = (BeltCategory) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "BeltCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
