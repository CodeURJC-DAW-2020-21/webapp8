package com.practicaweb.practicadaw.Service;

import com.practicaweb.practicadaw.ServiceInterfaces.UserServiceInterface;
import com.practicaweb.practicadaw.auxClasses.auxiliar;
import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private auxiliar aux;

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
        return userRepository.selectByEmail(email);
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

    @PostConstruct
    public void examples() {
        save(new User(10, "Marcos", "Rodriguez", "marcoszas", "marquitos@gmail.com", "hola", "User", aux.getActualDate(), null));
        save(new User(22, "Angel", "Fideos", "angel_Fideos", "angel@gmail.com", "hola", "User", aux.getActualDate(), null));
        save(new User(1, "admin", "admin", "admin", "admin@admin.com", "admin", "admin", aux.getActualDate(), null));
    }
}
