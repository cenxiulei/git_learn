package ewallet.application;

import ewallet.domain.model.User;
import ewallet.domain.service.UserService;
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
        User user = userMapper.dtoToEntity(userDTO);
        return userMapper.entityToDto(userService.createUser(user));
    }

    public UserDTO getUser(Long id) {
        return userMapper.entityToDto(userService.getUser(id));
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.stream()
                .map(userMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
