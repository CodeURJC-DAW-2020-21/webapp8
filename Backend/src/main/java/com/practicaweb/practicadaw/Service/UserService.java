package com.practicaweb.practicadaw.Service;

import com.practicaweb.practicadaw.ServiceInterfaces.UserServiceInterface;
import com.practicaweb.practicadaw.auxClasses.Auxiliar;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private Auxiliar aux;
    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(long idUser) {
        return userRepository.findById(idUser);
    }

    @Override
    public User findByEmail(String email) {
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

    public String forgotPassword(String email){
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));

        if(!userOptional.isPresent()){
            return "Invalid email id.";
        }

        User user = userOptional.get();
        user.setToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());

        user = userRepository.save(user);

        return user.getToken();
    }

    public String resetPassword(String token, String password){

        Optional<User> userOptional = Optional.ofNullable(userRepository.findByToken(token));

        if (!userOptional.isPresent()){
            return "Invalid token";
        }

        LocalDateTime tokenCreationTime = userOptional.get().getTokenCreationDate();

        if (isTokenExpired(tokenCreationTime)){
            return "Token expired";
        }

        User user = userOptional.get();

        user.setEncodedPassword(passwordEncoder.encode(password));
        user.setToken(null);
        user.setTokenCreationDate(null);
        userService.save(user);

        return "Your password succesfully updated";
    }

    private String generateToken() {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }

    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
    }
}
