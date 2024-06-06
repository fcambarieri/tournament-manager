package com.fcctech.tournament.repository;

import com.fcctech.tournament.entity.BeltCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BeltCategoryRepository extends CrudRepository<BeltCategory, Long> {

    @Query("select b from BeltCategory b where b.tournament.id = :tournamentId and b.id = :id")
    Optional<BeltCategory> findBeltCategoryByTournamentAndId(@Param("tournamentId") Long tournamentId, @Param("id") Long id);

    @Query("select b from BeltCategory b where b.tournament.id = :tournamentId")
    List<BeltCategory> findAllBeltByTournamentId(@Param("tournamentId") Long tournamentId);
}
