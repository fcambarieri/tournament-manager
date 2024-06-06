package com.fcctech.tournament.repository;

import com.fcctech.tournament.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {


    @Query("select case when count(a) > 0 then true else false end from Account a where a.owner.id = :userId")
    boolean existUserAccount(@Param("userId") Long userId);

    @Query("select a from Account a where a.owner.id = :ownerId")
    Optional<Account> findByOwnerId(@Param("ownerId") Long ownerId);
}
