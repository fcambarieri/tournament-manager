package com.fcctech.tournament.repository;

import com.fcctech.tournament.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u where u.email = :param or u.userName = :param")
    Optional<User> findByEmailOrUserName(@Param("param") String emailOrUserName);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    @Modifying
    @Query("update User u set u.password = :pass where u.id = :userId")
    void updatePassword(@Param("userId") Long userId, @Param("pass") String password);
}
