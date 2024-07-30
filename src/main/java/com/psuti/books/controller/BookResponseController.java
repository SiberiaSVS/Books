package com.psuti.books.controller;

import com.psuti.books.dto.BookResponseDTO;
import com.psuti.books.model.BookResponse;
import com.psuti.books.security.UserPrincipal;
import com.psuti.books.service.BookResponseService;
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
@RequestMapping("/book-response")
public class BookResponseController {
    private final BookResponseService bookResponseService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllResponses() {
        return new ResponseEntity<>(bookResponseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getResponse(@PathVariable Long id) {
        return new ResponseEntity<>(bookResponseService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookResponse> editResponse(@AuthenticationPrincipal UserPrincipal principal, BookResponseDTO dto) {
        if (!userService.checkEnabledPrincipal(principal)) return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);//Проверка на бан
        return new ResponseEntity<>(bookResponseService.update(principal, dto), HttpStatus.OK);
    }

    @DeleteMapping("/id")
    public HttpStatus deleteResponse(@AuthenticationPrincipal UserPrincipal principal, @PathVariable Long id) {
        if (!userService.checkEnabledPrincipal(principal)) return HttpStatus.FORBIDDEN;//Проверка на бан
        bookResponseService.delete(id);
        return HttpStatus.OK;
    }
}
