package com.practicaweb.practicadaw.Service;

import com.practicaweb.practicadaw.ServiceInterfaces.UserServiceInterface;
import com.practicaweb.practicadaw.auxClasses.auxiliar;
import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.w3c.dom.UserDataHandler;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private auxiliar aux;

    @Autowired
    PasswordEncoder passwordEncoder;

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
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> selectAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteUserById(long IdUser) {
        userRepository.deleteById(IdUser);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
