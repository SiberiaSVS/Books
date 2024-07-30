package com.psuti.books.controller;

import com.psuti.books.dto.WishListDTO;
import com.psuti.books.model.WishList;
import com.psuti.books.security.UserPrincipal;
import com.psuti.books.service.UserService;
import com.psuti.books.service.WishListService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/wishlist")
public class WishListController {
    private final WishListService wishListService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<WishList>> getAllWishList(@AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);//Проверка на бан
        return new ResponseEntity<>(wishListService.getAll(principal), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WishList> createWishList(@RequestBody WishListDTO dto, @AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);//Проверка на бан
        return new ResponseEntity<>(wishListService.create(dto, principal), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteWishList(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return HttpStatus.FORBIDDEN;//Проверка на бан
        return wishListService.delete(id, principal);
    }
}
