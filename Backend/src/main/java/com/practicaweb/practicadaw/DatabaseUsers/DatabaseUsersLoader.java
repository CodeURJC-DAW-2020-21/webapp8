package com.practicaweb.practicadaw.DatabaseUsers;

import javax.annotation.PostConstruct;
import javax.swing.text.html.parser.Entity;

import com.practicaweb.practicadaw.auxClasses.auxiliar;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.EntryRepository;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUsersLoader {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private auxiliar aux;

    @PostConstruct
    private void initDatabase() {
        User user1 = userRepository.save(new User(1, passwordEncoder.encode("adminpass"), "admin", "admin", "admin", "admin@admin.com", aux.getActualDate(), "defaultImage", "USER", "ADMIN"));
        User user2 = userRepository.save(new User(2, passwordEncoder.encode("pass"), "marcoszas", "Rodriguez", "Marcos", "marcos@gmail.com", aux.getActualDate(), "defaultImage", "USER"));
        User user3 = userRepository.save(new User(3, passwordEncoder.encode("pass"), "angelote", "Dominguez", "Angel", "angel@gmail.com", aux.getActualDate(), "defaultImage", "USER"));
        entryRepository.save(new Entry(1, "Pues esta es la primera entrada", "Esperemos que se muestre", aux.getActualDate(), user1));
        entryRepository.save(new Entry(2, "Esta es otra de las entradas de prueba", "Pues por lo que parece si que se muestra si jeje", aux.getActualDate(), user2));
        entryRepository.save(new Entry(3, "Esta es la entrada escrita por el usuario de angel", "Asi es soy la entrada escrita por angel", aux.getActualDate(), user3));
    }
}
