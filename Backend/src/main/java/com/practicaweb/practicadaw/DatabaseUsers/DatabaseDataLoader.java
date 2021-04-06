package com.practicaweb.practicadaw.DatabaseUsers;

import javax.annotation.PostConstruct;;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.auxClasses.Auxiliar;
import com.practicaweb.practicadaw.model.Bitcoin;
import com.practicaweb.practicadaw.model.Criptocurrency;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.CriptocurrencyRepository;
import com.practicaweb.practicadaw.repository.EntryRepository;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseDataLoader {

    private static final Path IMAGES_FOLDER = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/profileImages");

    private final UserRepository userRepository;

    private final CriptocurrencyRepository criptocurrencyRepository;

    private final EntryRepository entryRepository;

    private final PasswordEncoder passwordEncoder;

    public DatabaseDataLoader(UserRepository userRepository, EntryRepository entryRepository, PasswordEncoder passwordEncoder, CriptocurrencyRepository criptocurrencyRepository) {
        this.userRepository = userRepository;
        this.entryRepository = entryRepository;
        this.passwordEncoder = passwordEncoder;
        this.criptocurrencyRepository = criptocurrencyRepository;
    }

    @Autowired
    UserService userService;

    @PostConstruct
    private void initDatabase() throws IOException {
        User user1 = new User(1, passwordEncoder.encode("adminpass"), "admin", "admin", "admin", "admin@admin.com", LocalDateTime.now(), null, "USER", "ADMIN");
        userService.setUserImage(user1, "static/profileImages/defaultImage.jpg");
        userRepository.save(user1);
        User user2 = new User(2, passwordEncoder.encode("pass"), "marcoszas", "Rodriguez", "Marcos", "marcos@gmail.com", LocalDateTime.now(), null, "USER");
        userService.setUserImage(user2, "static/profileImages/defaultImage.jpg");
        userRepository.save(user2);
        User user3 = new User(3, passwordEncoder.encode("pass"), "angelote", "Dominguez", "Angel", "angel@gmail.com", LocalDateTime.now(), null, "USER");
        userService.setUserImage(user3, "static/profileImages/defaultImage.jpg");
        userRepository.save(user3);
        Criptocurrency cripto1 = criptocurrencyRepository.save(new Criptocurrency(1, "Bitcoin", 40453.67, "BTC-USD", "images/starEmpty.svg"));
        Criptocurrency cripto2 = criptocurrencyRepository.save(new Criptocurrency(2, "Ethereum", 1900.67, "ETH-USD", "images/starEmpty.svg"));
        Criptocurrency cripto3 = criptocurrencyRepository.save(new Criptocurrency(3, "Badger", 27.67, "BADGER-USD", "images/starEmpty.svg"));
        entryRepository.save(new Entry(1, "1 Pues esta es la primera entrada", "Esperemos que se muestre", LocalDateTime.now(), user1));
        entryRepository.save(new Entry(2, "2 Esta es otra de las entradas de prueba", "Pues por lo que parece si que se muestra si jeje", LocalDateTime.now(), user2));
        entryRepository.save(new Entry(3, "3 Esta es la entrada escrita por el usuario de angel", "Asi es soy la entrada escrita por angel", LocalDateTime.now(), user3));
        entryRepository.save(new Entry(4, "4 Pues esta es la primera entrada", "Esperemos que se muestre", LocalDateTime.now(), user1));
        entryRepository.save(new Entry(5, "5 Esta es otra de las entradas de prueba", "Pues por lo que parece si que se muestra si jeje", LocalDateTime.now(), user2));
        entryRepository.save(new Entry(6, "6 Esta es la entrada escrita por el usuario de angel", "Asi es soy la entrada escrita por angel", LocalDateTime.now(), user3));
        entryRepository.save(new Entry(7, "7 Pues esta es la primera entrada", "Esperemos que se muestre", LocalDateTime.now(), user1));
        entryRepository.save(new Entry(8, "8 Esta es otra de las entradas de prueba", "Pues por lo que parece si que se muestra si jeje", LocalDateTime.now(), user2));
        entryRepository.save(new Entry(9, "9 Esta es la entrada escrita por el usuario de angel", "Asi es soy la entrada escrita por angel", LocalDateTime.now(), user3));
        entryRepository.save(new Entry(10, "10 Pues esta es la primera entrada", "Esperemos que se muestre", LocalDateTime.now(), user1));
        entryRepository.save(new Entry(11, "11 Esta es otra de las entradas de prueba", "Pues por lo que parece si que se muestra si jeje", LocalDateTime.now(), user2));
//        entryRepository.save(new Entry(12, "12 Esta es la entrada escrita por el usuario de angel", "Asi es soy la entrada escrita por angel", LocalDateTime.now(), user3));
    }
}
