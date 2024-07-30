package com.psuti.books.controller;

import com.psuti.books.dto.UserExchangeListDTO;
import com.psuti.books.model.UserExchangeList;
import com.psuti.books.security.UserPrincipal;
import com.psuti.books.service.UserExchangeListService;
import com.psuti.books.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
@RequestMapping("/user-exchange-list")
public class UserExchangeListController {
    private final UserExchangeListService userExchangeListService;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserExchangeList> getById(@AuthenticationPrincipal UserPrincipal principal, @PathVariable Long id) {
        if (!userService.checkEnabledPrincipal(principal)) return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);//Проверка на бан
        return userExchangeListService.getUserExchangeList(principal, id);
    }

    @GetMapping
    public ResponseEntity<List<UserExchangeList>> getByUser(@AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);//Проверка на бан
        return userExchangeListService.getUserExchangeLists(principal);
    }

    @GetMapping("/archived")
    public ResponseEntity<List<UserExchangeList>> getArchivedByUser(@AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);//Проверка на бан
        return userExchangeListService.getArchivedUserExchangeLists(principal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserExchangeList> updateTrackNumber(@AuthenticationPrincipal UserPrincipal principal, @RequestBody String trackNumber,@PathVariable Long id) {
        if (!userService.checkEnabledPrincipal(principal)) return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);//Проверка на бан
        return userExchangeListService.updateTrackNumber(principal, trackNumber, id);
    }

    @PutMapping("/set-receiving/{id}")
    public ResponseEntity<UserExchangeList> setReceiving(@AuthenticationPrincipal UserPrincipal principal, @PathVariable Long id) {
        if (!userService.checkEnabledPrincipal(principal)) return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);//Проверка на бан
        return userExchangeListService.setReceiving(principal, id);
    }
}
