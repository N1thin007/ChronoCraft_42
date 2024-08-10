package com.project.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.practice.model.Login;
import com.project.practice.service.LoginService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<?> createLogin(@RequestBody Login login) {
        if (loginService.usernameExists(login.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("success", false, "message", "Username already exists"));
        }
        if (loginService.emailExists(login.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("success", false, "message", "Email already exists"));
        }

        // Create the new login with encrypted password
        Login newLogin = loginService.createLogin(login);
        return ResponseEntity.ok(Map.of("success", true, "message", "Registration successful", "login", newLogin));
    }

    @GetMapping
    public List<Login> getAllLogins() {
        return loginService.getAllLogins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Login> getLoginById(@PathVariable Long id) {
        return loginService.getLoginById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogin(@PathVariable Long id) {
        if (loginService.getLoginById(id).isPresent()) {
            loginService.deleteLogin(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/check")
    public ResponseEntity<?> checkLogin(@RequestBody Login login) {
        Login existingLogin = loginService.findByUsername(login.getUsername());
        if (existingLogin != null && loginService.checkPassword(existingLogin, login.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Login successful!");
            response.put("role", existingLogin.getRole());
            response.put("email", existingLogin.getEmail()); // Ensure email is returned
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success", false, "message", "Invalid username or password"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLogin(@PathVariable Long id, @RequestBody Login loginDetails) {
        Login updatedLogin = loginService.updateLogin(id, loginDetails);
        if (updatedLogin != null) {
            return ResponseEntity.ok(Map.of("success", true, "message", "User details updated", "login", updatedLogin));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", false, "message", "User not found"));
        }
    }
}
