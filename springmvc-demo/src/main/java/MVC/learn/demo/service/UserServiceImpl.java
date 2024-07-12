package MVC.learn.demo.service;


import MVC.learn.demo.model.User;
import MVC.learn.demo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        Optional<Optional<User>> optionalUser = Optional.of(userRepository.findById(id));
        return optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User creatUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @PostConstruct
    public void init() {
        userRepository.save(new User(1,"Alice"));
        userRepository.save(new User(2,"Bob"));
        userRepository.save(new User(3,"Charlie"));
    }
}

