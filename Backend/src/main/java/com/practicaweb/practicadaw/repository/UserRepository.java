package com.practicaweb.practicadaw.repository;

import com.practicaweb.practicadaw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT id_user, email, name, nickname, image, password, registration_date, surname, friends, cryptocurrencies role  FROM webapp8_bbdd.user WHERE email= :userEmail",
            nativeQuery = true)
    User selectByEmail(@Param("userEmail")String email);
}
