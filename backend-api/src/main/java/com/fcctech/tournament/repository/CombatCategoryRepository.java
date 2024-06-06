package com.fcctech.tournament.repository;

import com.fcctech.tournament.domain.SexType;
import com.fcctech.tournament.entity.CombatCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CombatCategoryRepository extends CrudRepository<CombatCategory, Long> {

    @Query("select w from CombatCategory w left join AgeCategory a on w.ageCategory.id = a.id " +
            "where w.tournament.id = :tid and :weight between w.minWeight and w.maxWeight " +
            " and :age between a.minAge and a.maxAge")
    Optional<CombatCategory> findByWeightAndAge(@Param("tid") Long tournamentId, @Param("weight") int weight , @Param("age") int age);

    @Query("select c from CombatCategory c where c.tournament.id = :tournamentId and c.id = :id")
    Optional<CombatCategory> findByTournamentIdAndId(@Param("tournamentId") Long tournamentId, @Param("id") Long categoryId);

    @Query("select c from CombatCategory c where c.tournament.id = :tournamentId")
    List<CombatCategory> findByTournamentId(@Param("tournamentId") Long tournamentId);

    @Query("select c from CombatCategory c where c.tournament.id = :tournamentId and c.sex = :sex" +
            " and c.ageCategory.id = :categoryId")
    List<CombatCategory> findByTournamentIdAndSexAndAgeCategory(@Param("tournamentId")Long tournamentId,
                                                                @Param("sex")SexType sex,
                                                                @Param("categoryId") Long categoryId);
}
