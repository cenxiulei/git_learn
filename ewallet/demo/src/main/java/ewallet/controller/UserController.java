package ewallet.controller;


import ewallet.application.UserApplicationService;
import ewallet.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserApplicationService userApplicationService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userApplicationService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO user = userApplicationService.getUser(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping
    public ResponseEntity<UserDTO> getUser1() {
        return ResponseEntity.ok(null);
    }
}
