package ewallet.controller;

import ewallet.application.UserApplicationService;
import ewallet.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserApplicationService userApplicationService;

    @Operation(summary = "注册用户", description = "根据用户信息注册一个新用户")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userApplicationService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    @Operation(summary = "用户登录", description = "根据用户名和密码进行登录")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        UserDTO user = userApplicationService.findUserByUsername(userDTO.getUsername());
        if (user != null && user.getPassword().equals(userDTO.getPassword())) {
            return ResponseEntity.ok("JWT-TOKEN");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @Operation(summary = "获取所有用户")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userApplicationService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "根据用户名查找用户")
    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> findUserByUsername(@PathVariable String username) {
        UserDTO user = userApplicationService.findUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "更新用户信息", description = "根据用户ID更新用户信息")
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        userDTO.setId(userId);
        UserDTO updatedUser = userApplicationService.updateUser(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "删除用户", description = "根据用户ID删除用户")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userApplicationService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}