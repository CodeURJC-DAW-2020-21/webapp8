package com.practicaweb.practicadaw.DatabaseUsers;

import javax.annotation.PostConstruct;

import com.practicaweb.practicadaw.auxClasses.Auxiliar;
import com.practicaweb.practicadaw.model.Criptocurrency;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.CriptocurrencyRepository;
import com.practicaweb.practicadaw.repository.EntryRepository;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DatabaseDataLoader {

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

    @PostConstruct
    private void initDatabase() {
        User user1 = userRepository.save(new User(1, passwordEncoder.encode("adminpass"), "admin", "admin", "admin", "admin@admin.com", Auxiliar.getActualDate(), "defaultImage", null, "USER", "ADMIN"));
        User user2 = userRepository.save(new User(2, passwordEncoder.encode("pass"), "marcoszas", "Rodriguez", "Marcos", "marcos@gmail.com", Auxiliar.getActualDate(), "defaultImage", null, "USER"));
        User user3 = userRepository.save(new User(3, passwordEncoder.encode("pass"), "angelote", "Dominguez", "Angel", "angel@gmail.com", Auxiliar.getActualDate(), "defaultImage", null, "USER"));
        Criptocurrency cripto1 = criptocurrencyRepository.save(new Criptocurrency(1, "Bitcoin", 40453.67));
        Criptocurrency cripto2 = criptocurrencyRepository.save(new Criptocurrency(2, "Ethereum", 1900.67));
        Criptocurrency cripto3 = criptocurrencyRepository.save(new Criptocurrency(3, "Badger", 27.67));
        entryRepository.save(new Entry(1, "Pues esta es la primera entrada", "Esperemos que se muestre", Auxiliar.getActualDate(), user1));
        entryRepository.save(new Entry(2, "Esta es otra de las entradas de prueba", "Pues por lo que parece si que se muestra si jeje", Auxiliar.getActualDate(), user2));
        entryRepository.save(new Entry(3, "Esta es la entrada escrita por el usuario de angel", "Asi es soy la entrada escrita por angel", Auxiliar.getActualDate(), user3));
    }
}
