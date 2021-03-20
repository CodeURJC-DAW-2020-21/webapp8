package com.practicaweb.practicadaw.Service;

import com.practicaweb.practicadaw.ServiceInterfaces.UserServiceInterface;
import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> selectById(long id) {
        return Optional.empty();
    }

    @Override
    public User selectByEmail(String email) {
        User u = userRepository.selectByEmail(email);
        return u;
    }


}
