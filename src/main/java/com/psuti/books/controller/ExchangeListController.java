package com.psuti.books.controller;

import com.psuti.books.dto.ExchangeListDTO;
import com.psuti.books.model.ExchangeList;
import com.psuti.books.security.UserPrincipal;
import com.psuti.books.service.ExchangeListService;
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
@RequestMapping("/exchange")
public class ExchangeListController {
    private final ExchangeListService exchangeListService;
    private final UserService userService;

    @GetMapping("/incoming")
    public ResponseEntity<List<ExchangeList>> getIncomingExchanges(@AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);//Проверка на бан
        return exchangeListService.getIncoming(principal);
    }

    @GetMapping("/outgoing")
    public ResponseEntity<List<ExchangeList>> getOutgoingExchanges(@AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);//Проверка на бан
        return exchangeListService.getOutgoing(principal);
    }

    @PutMapping("/confirm/{id}")
    public HttpStatus confirm(@AuthenticationPrincipal UserPrincipal principal, @PathVariable Long id) {
        return exchangeListService.confirm(principal, id);
    }

    @GetMapping
    public ResponseEntity<Object> getExchanges(@AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);//Проверка на бан
        return new ResponseEntity<>(exchangeListService.get(principal), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExchangeList> createExchangeList(@AuthenticationPrincipal UserPrincipal principal, @RequestBody ExchangeListDTO dto) {
        return exchangeListService.create(principal, dto);
    }
}
