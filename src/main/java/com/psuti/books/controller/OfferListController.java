package com.psuti.books.controller;

import com.psuti.books.dto.OfferListDTO;
import com.psuti.books.model.OfferList;
import com.psuti.books.security.UserPrincipal;
import com.psuti.books.service.OfferListService;
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
@RequestMapping("/offer-list")
public class OfferListController {
    private final OfferListService offerListService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<OfferList>> getAllOfferList(@AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);//Проверка на бан
        return new ResponseEntity<>(offerListService.getAll(principal), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OfferList> createOfferList(@RequestBody OfferListDTO dto, @AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);//Проверка на бан
        return new ResponseEntity<>(offerListService.create(dto, principal), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteOfferList(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal principal) {
        if (!userService.checkEnabledPrincipal(principal)) return HttpStatus.FORBIDDEN;//Проверка на бан
        offerListService.delete(id, principal);
        return HttpStatus.OK;
    }

//    @PutMapping("/{offerListId}/category/{categoryId}")
//    public ResponseEntity<OfferList> assignCategoryToOfferList(@PathVariable Long offerListId,
//        @PathVariable Long categoryId) {
//        return new ResponseEntity<>(offerListService.assignCategoryToOfferList(offerListId, categoryId), HttpStatus.OK);
//    }
}
