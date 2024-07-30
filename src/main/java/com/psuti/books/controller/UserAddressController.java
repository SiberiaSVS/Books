package com.psuti.books.controller;

import com.psuti.books.dto.OfferListDTO;
import com.psuti.books.dto.UserAddressDTO;
import com.psuti.books.model.OfferList;
import com.psuti.books.model.UserAddress;
import com.psuti.books.security.UserPrincipal;
import com.psuti.books.service.UserAddressService;
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
@RequestMapping("/address")
public class UserAddressController {

    private final UserAddressService userAddressService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserAddress>> getAddressesByUserPrincipal(@AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);//Проверка на бан
        return new ResponseEntity<>(userAddressService.getByUserPrincipal(principal), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserAddress> createUserAddress(@RequestBody UserAddressDTO dto, @AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);//Проверка на бан
        return new ResponseEntity<>(userAddressService.create(dto, principal), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUserAddress(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return HttpStatus.FORBIDDEN;//Проверка на бан
        userAddressService.delete(id, principal);
        return HttpStatus.OK;
    }
}
