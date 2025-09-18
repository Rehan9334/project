package controller;

import model.AppUser;   // ✅ Correct import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String role) {

        // Correct usage of AppUser
        AppUser user = new AppUser();
        user.setName(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(role);

        userService.save(user);

        return "redirect:/register-success.html";
    }
}
