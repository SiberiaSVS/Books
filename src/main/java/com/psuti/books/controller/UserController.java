package com.psuti.books.controller;

import com.psuti.books.dto.UserDTO;
import com.psuti.books.model.User;
import com.psuti.books.security.UserPrincipal;
import com.psuti.books.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
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
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/guest")
    public String guest() {
        return "Если ты это видишь, то у тебя права ГОСТЯ";
    }

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return "В бане";//Проверка на бан
        return "Если ты это видишь, то у тебя есть права ПОЛЬЗОВАТЕЛЯ " + principal.getEmail() + " id: " + principal.getUserId();
    }

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return "В бане";//Проверка на бан
        return "Если ты это видишь, то у тебя есть права АДМИНИСТРАТОРА " + principal.getEmail() + " id: " + principal.getUserId();
    }

    @GetMapping("/me")
    public ResponseEntity<Optional<User>> getUserByPrincipal(@AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);//Проверка на бан
        return new ResponseEntity<>(userService.getByEmail(principal.getEmail()), HttpStatus.OK);
    }

    @PostMapping("/me")
    public ResponseEntity<User> updateUserByPrincipal(UserDTO dto, @AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);//Проверка на бан
        return new ResponseEntity<>(userService.update(dto, principal), HttpStatus.OK);
    }
}
