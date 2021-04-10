package com.practicaweb.practicadaw.ServiceInterfaces;

import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    User save (User user);
    Optional<User> findById(long idUser);
    User findByEmail(String email);
    List<User> selectAll();
    void deleteUserById(long IdUser);
    Optional<User> findByName (String name);
    void delete(User user);
    List<User> findByFirstname(String firstname);
}
