package com.practicaweb.practicadaw.Service;

import com.practicaweb.practicadaw.ServiceInterfaces.UserServiceInterface;
import com.practicaweb.practicadaw.api.user.UserDTO;
import com.practicaweb.practicadaw.auxClasses.Auxiliar;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public void delete(User user){
        userRepository.delete(user);
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
    public List<User> findByFirstname(String firstname) {
        return userRepository.findByFirstname(firstname);
    }

    @Override
    public List<User> findBySurname(String surname){
        return userRepository.findBySurname(surname);
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
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public String forgotPassword(String email){
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));

        if(!userOptional.isPresent()){
            return "Invalid email id.";
        }

        User user = userOptional.get();
        user.setTokenPass(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());

        user = userRepository.save(user);

        return user.getTokenPass();
    }

    public String resetPassword(String tokenPass, String password){

        Optional<User> userOptional = Optional.ofNullable(userRepository.findByTokenPass(tokenPass));

        if (!userOptional.isPresent()){
            return "Invalid token";
        }

        LocalDateTime tokenCreationTime = userOptional.get().getTokenCreationDate();

        if (isTokenExpired(tokenCreationTime)){
            return "Token expired";
        }

        User user = userOptional.get();

        user.setEncodedPassword(passwordEncoder.encode(password));
        user.setTokenPass(null);
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

    public void setUserImage(User user, String classpathResource) throws IOException{
        Resource image = new ClassPathResource(classpathResource);
        user.setImage(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

    public void updateUserImage(User user, MultipartFile image) throws IOException, SQLException {
        if(!image.isEmpty()){
            user.setImage(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
        } else {
            User userDB = findById(user.getIdUser()).orElseThrow();
            if (userDB.getImage().length() == 0){
                user.setImage(BlobProxy.generateProxy(userDB.getImage().getBinaryStream(), userDB.getImage().length()));
            }
        }
    }

    public void createUser(User user) throws IOException {

        user.setRegistrationDate(LocalDateTime.now());

        user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));

        List<String> roles = new ArrayList<>();
        roles.add("USER");
        user.setRoles(roles);
        user.setFollow("/images/Seguir.png");

        Resource image = new ClassPathResource("/static/profileImages/defaultImage.jpg");
        user.setImage(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
        userRepository.save(user);
    }

    public User updateUser(User user, UserDTO userDTO) throws SQLException, IOException {
        if (userDTO.getFirstname() != null){
            user.setFirstname(userDTO.getFirstname());
        } else {
            user.setFirstname(user.getFirstname());
        }
        if (userDTO.getSurname() != null){
            user.setSurname(userDTO.getSurname());
        } else {
            user.setSurname(user.getSurname());
        }
        if (userDTO.getImage() != null){
            updateUserImage(user, userDTO.getImage());
        } else {
            user.setImage(user.getImage());
        }
        return userRepository.save(user);
    }

}
