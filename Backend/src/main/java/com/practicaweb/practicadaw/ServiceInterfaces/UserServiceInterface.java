package com.practicaweb.practicadaw.ServiceInterfaces;

import com.practicaweb.practicadaw.model.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    User save (User user);
    //Optional<User> selectById(long idUser);
    User findByEmail(String email);
    List<User> selectAll();
    void deleteUserById(long IdUser);
    User findByName (String name);
}
