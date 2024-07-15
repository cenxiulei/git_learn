package ewallet.service;

import ewallet.dto.UserDto;
import ewallet.model.User;
import ewallet.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        user.setUserId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
