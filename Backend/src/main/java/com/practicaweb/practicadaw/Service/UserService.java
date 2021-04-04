package com.practicaweb.practicadaw.Service;

import com.practicaweb.practicadaw.ServiceInterfaces.UserServiceInterface;
import com.practicaweb.practicadaw.auxClasses.Auxiliar;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public byte[] extractBytes (String ImageName) throws IOException {
        // open image
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

        return ( data.getData() );
    }

//    public File ByteArrayToFile(byte[] image) throws IOException {
//        BufferedImage bImage = ImageIO.read(new File("sample.jpg"));
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ImageIO.write(bImage, "jpg", bos);
//        byte [] data = bos.toByteArray();
//        ByteArrayInputStream bis = new ByteArrayInputStream(data);
//        BufferedImage bImage2 = ImageIO.read(bis);
//        ImageIO.write(bImage2, "jpg", new File("output.jpg") );
//        System.out.println("image created");
//    }


}
