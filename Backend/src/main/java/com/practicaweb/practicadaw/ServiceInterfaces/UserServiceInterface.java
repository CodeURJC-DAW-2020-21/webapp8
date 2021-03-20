package com.practicaweb.practicadaw.ServiceInterfaces;

import com.practicaweb.practicadaw.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserServiceInterface {
    User save (User user);
    Optional<User> selectById (long id);
    User selectByEmail(String email);
}
