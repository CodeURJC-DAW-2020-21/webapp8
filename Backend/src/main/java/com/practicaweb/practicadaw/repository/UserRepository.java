package com.practicaweb.practicadaw.repository;

import com.practicaweb.practicadaw.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Qualifier("users")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    User findByEmail(String email);
    User findByTokenPass(String tokenPass);
    List<User> findByFirstname(String firstname);
    List<User> findBySurname(String surname);
}
