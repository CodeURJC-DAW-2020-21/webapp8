package com.practicaweb.practicadaw.DatabaseUsers;

import javax.annotation.PostConstruct;;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.*;
import com.practicaweb.practicadaw.repository.CommentRepository;
import com.practicaweb.practicadaw.repository.CriptocurrencyRepository;
import com.practicaweb.practicadaw.repository.EntryRepository;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class DatabaseDataLoader {

    private final UserRepository userRepository;
    private final CriptocurrencyRepository criptocurrencyRepository;
    private final EntryRepository entryRepository;
    private final PasswordEncoder passwordEncoder;
    final UserService userService;
    final CommentRepository commentRepository;

    public DatabaseDataLoader(UserRepository userRepository, EntryRepository entryRepository, PasswordEncoder passwordEncoder, CriptocurrencyRepository criptocurrencyRepository, UserService userService, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.entryRepository = entryRepository;
        this.passwordEncoder = passwordEncoder;
        this.criptocurrencyRepository = criptocurrencyRepository;
        this.userService = userService;
        this.commentRepository = commentRepository;
    }



    @PostConstruct
    private void initDatabase() throws IOException {
        //Users
        User user1 = new User(1, passwordEncoder.encode("adminpass"), "admin", "admin", "admin", "admin@admin.com", LocalDateTime.now(), "images/Seguir.png",null, "USER", "ADMIN");
        userService.setUserImage(user1, "static/profileImages/defaultImage.jpg");
        userRepository.save(user1);
        User user2 = new User(2, passwordEncoder.encode("pass"), "marcoszas", "Rodriguez", "Marcos", "marcos@gmail.com", LocalDateTime.now(), "images/Seguir.png",null, "USER");
        userService.setUserImage(user2, "static/profileImages/profileImage2.jfif");
        userRepository.save(user2);
        User user3 = new User(3, passwordEncoder.encode("pass"), "angelote", "Dominguez", "Angel", "angel@gmail.com", LocalDateTime.now(), "images/Seguir.png",null, "USER");
        userService.setUserImage(user3, "static/profileImages/profileImage1.jfif");
        userRepository.save(user3);
        //Cryptocurrencies
        criptocurrencyRepository.save(new Criptocurrency(1, "Bitcoin", 40453.67, "BTC-USD", "images/starEmpty.svg"));
        criptocurrencyRepository.save(new Criptocurrency(2, "Ethereum", 1900.67, "ETH-USD", "images/starEmpty.svg"));
        criptocurrencyRepository.save(new Criptocurrency(3, "Badger", 27.67, "BADGER-USD", "images/starEmpty.svg"));
        //Entries
        entryRepository.save(new Entry(1, "1 Pues esta es la primera entrada", "Esperemos que se muestre", LocalDateTime.now(), user1));
        Entry entry2 = entryRepository.save(new Entry(2, "2 Esta es otra de las entradas de prueba", "Pues por lo que parece si que se muestra si jeje", LocalDateTime.now(), user2));
        Entry entry3 = entryRepository.save(new Entry(3, "3 Esta es la entrada escrita por el usuario de angel", "Asi es soy la entrada escrita por angel", LocalDateTime.now(), user3));
        entryRepository.save(new Entry(4, "4 Pues esta es la primera entrada", "Esperemos que se muestre", LocalDateTime.now(), user1));
        entryRepository.save(new Entry(5, "5 Esta es otra de las entradas de prueba", "Pues por lo que parece si que se muestra si jeje", LocalDateTime.now(), user2));
        entryRepository.save(new Entry(6, "6 Esta es la entrada escrita por el usuario de angel", "Asi es soy la entrada escrita por angel", LocalDateTime.now(), user3));
        entryRepository.save(new Entry(7, "7 Pues esta es la primera entrada", "Esperemos que se muestre", LocalDateTime.now(), user1));
        entryRepository.save(new Entry(8, " 8Esta es otra de las entradas de prueba", "Pues por lo que parece si que se muestra si jeje", LocalDateTime.now(), user2));
        entryRepository.save(new Entry(9, " 9Esta es la entrada escrita por el usuario de angel", "Asi es soy la entrada escrita por angel", LocalDateTime.now(), user3));
        entryRepository.save(new Entry(10, "10 Pues esta es la primera entrada", "Esperemos que se muestre", LocalDateTime.now(), user1));
        entryRepository.save(new Entry(11, "11 Esta es otra de las entradas de prueba", "Pues por lo que parece si que se muestra si jeje", LocalDateTime.now(), user2));
        entryRepository.save(new Entry(12, "12 Pues esta es la doce entrada", "Esperemos que se muestres", LocalDateTime.now(), user1));
        entryRepository.save(new Entry(13, "13 Esta es otra de las entradas de prueba", "Pues por lo que parece si que se muestra si jejeje", LocalDateTime.now(), user2));
        entryRepository.save(new Entry(14, "14 Esta es la entrada escrita por el usuario de angel", "Asi es soy la entrada escrita por el angel", LocalDateTime.now(), user3));
        //Comments
        commentRepository.save(new Comment(1, "Estos son algunos de los comentarios de prueba que hablaran sobre criptomonedas", LocalDateTime.now(), user2, entry3));
        commentRepository.save(new Comment(2, "Asi es estos son algunos de los comentarios que hablaran sobre criptomonedas", LocalDateTime.now(), user3, entry3));
        commentRepository.save(new Comment(3, "El mercado de las criptomonedas crece cada dia con nuevas altcoins.", LocalDateTime.now(), user2, entry2));
//        entryRepository.save(new Entry(12, "12 Esta es la entrada escrita por el usuario de angel", "Asi es soy la entrada escrita por angel", LocalDateTime.now(), user3));
    }
}
