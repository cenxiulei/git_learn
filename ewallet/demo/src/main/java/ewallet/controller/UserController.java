package ewallet.controller;

import ewallet.dto.UserDto;
import ewallet.model.User;
import ewallet.respository.UserRepository;
import ewallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
//        return ResponseEntity.ok(userService.login(userDto));
//    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }
}
