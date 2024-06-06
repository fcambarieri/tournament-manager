package com.fcctech.tournament.repository;

import com.fcctech.tournament.entity.AgeCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends CrudRepository<AgeCategory, Long> {

    @Query("select c from AgeCategory c where c.tournament.id = :tournamentId")
    List<AgeCategory> findAllCategoriesByTournamentId(@Param("tournamentId") Long tournamentId);
}
