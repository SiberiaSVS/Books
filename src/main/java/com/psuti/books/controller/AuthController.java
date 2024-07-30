package com.psuti.books.controller;

import com.psuti.books.dto.UserDTO;
import com.psuti.books.model.User;
import com.psuti.books.service.AuthService;
import com.psuti.books.service.UserService;
import com.psuti.books.utils.LoginRequest;
import com.psuti.books.utils.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(
        origins = {
                "http://localhost:9090",
                "http://kaka",
                "http://localhost:8081",
        },
        methods = {
                RequestMethod.OPTIONS,
                RequestMethod.GET,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.POST
        })
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }

    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody UserDTO dto) {
        return new ResponseEntity<>(userService.create(dto), HttpStatus.OK);
    }
}
