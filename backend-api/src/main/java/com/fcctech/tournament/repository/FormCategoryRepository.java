package com.fcctech.tournament.repository;

import com.fcctech.tournament.entity.FormCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormCategoryRepository extends CrudRepository<FormCategory, Long> {

    @Query("select f from FormCategory f where f.tournament.id = :tournamentId")
    List<FormCategory> findAllByTournamentId(@Param("tournamentId") Long tournamentId);
}
