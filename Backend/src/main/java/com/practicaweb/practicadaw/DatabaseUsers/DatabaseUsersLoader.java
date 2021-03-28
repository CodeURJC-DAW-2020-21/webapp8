package com.practicaweb.practicadaw.DatabaseUsers;

import javax.annotation.PostConstruct;

import com.practicaweb.practicadaw.auxClasses.auxiliar;
import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUsersLoader {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private auxiliar aux;

    @PostConstruct
    private void initDatabase() {
        userRepository.save(new User(1, "admin", "user2", "oscarprezz", "oscar@gmail.com", aux.getActualDate(), "defaultimage.jpg", null, passwordEncoder.encode("adminpass"), "USER", "ADMIN"));
        userRepository.save(new User(2, "Marcos", "Rodriguez", "marcoszas", "marcos@gmail.com", aux.getActualDate(), "defaultimage.jpg", null, passwordEncoder.encode("pass"), "USER"));
        userRepository.save(new User(3, "Angel", "Dominguez", "angeldom", "angel@gmail.com", aux.getActualDate(), "defaultimage.jpg", null, passwordEncoder.encode("pass"), "USER"));
    }
}
