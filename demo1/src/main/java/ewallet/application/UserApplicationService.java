package ewallet.application;

import ewallet.domian.model.User;
import ewallet.domian.service.UserService;
import ewallet.dto.UserDTO;
import ewallet.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserApplicationService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    public UserDTO createUser(UserDTO userDTO) {
        User user = userService.createUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
        return userMapper.userToUserDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }


    public UserDTO findUserByUsername(String username) {
        User user = userService.findByUsername(username);
        return userMapper.userToUserDTO(user);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = userService.updateUser(userDTO);
        return userMapper.userToUserDTO(user);
    }

    public void deleteUser(Long userId) {
        userService.deleteUser(userId);
    }
}
