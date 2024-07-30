package com.psuti.books.controller;

import com.psuti.books.dto.AutorDTO;
import com.psuti.books.dto.BookLiteraryDTO;
import com.psuti.books.dto.CategoryDTO;
import com.psuti.books.dto.StatusDTO;
import com.psuti.books.model.*;
import com.psuti.books.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final CategoryService categoryService;
    private final StatusService statusService;
    private final AutorService autorService;
    private final BookLiteraryService bookLiteraryService;
    private final UserMsgService userMsgService;

    @GetMapping("/messageByUser/{id}")
    public ResponseEntity<List<UserMsg>> getMessagesByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(userMsgService.getByUserId(id), HttpStatus.OK);
    }

    @PutMapping("/ban-user/{id}")
    public ResponseEntity<User> banUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.banUser(id), HttpStatus.OK);
    }

    @PutMapping("/unban-user/{id}")
    public ResponseEntity<User> unbanUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.unbanUser(id), HttpStatus.OK);
    }

    //----------Категории
    @GetMapping("/category")
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO dto) {
        return new ResponseEntity<>(categoryService.create(dto), HttpStatus.OK);
    }

    @PutMapping("/category")
    public ResponseEntity<Category> updateCategory(@RequestBody Category dto) {
        return new ResponseEntity<>(categoryService.update(dto), HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}")
    public HttpStatus deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return HttpStatus.OK;
    }

    //----------Статусы
    @GetMapping("/status")
    public ResponseEntity<List<Status>> getStatus() {
        return new ResponseEntity<>(statusService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/status")
    public ResponseEntity<Status> createStatus(@RequestBody StatusDTO dto) {
        return new ResponseEntity<>(statusService.create(dto), HttpStatus.OK);
    }

    @PutMapping("/status")
    public ResponseEntity<Status> updateStatus(@RequestBody StatusDTO dto) {
        return new ResponseEntity<>(statusService.update(dto), HttpStatus.OK);
    }

    @DeleteMapping("/status/{id}")
    public HttpStatus deleteStatus(@PathVariable Long id) {
        statusService.delete(id);
        return HttpStatus.OK;
    }

    //----------Авторы
    @GetMapping("/autor")
    public ResponseEntity<List<Autor>> getAutor() {
        return new ResponseEntity<>(autorService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/autor")
    public ResponseEntity<Autor> createAutor(@RequestBody AutorDTO dto) {
        return new ResponseEntity<>(autorService.create(dto), HttpStatus.OK);
    }

    @PutMapping("/autor")
    public ResponseEntity<Autor> updateAutor(@RequestBody AutorDTO dto) {
        return new ResponseEntity<>(autorService.update(dto), HttpStatus.OK);
    }

    @DeleteMapping("/autor/{id}")
    public HttpStatus deleteAutor(@PathVariable Long id) {
        autorService.delete(id);
        return HttpStatus.OK;
    }

    //----------Книги
    @GetMapping("/book")
    public ResponseEntity<List<BookLiterary>> getBook() {
        return new ResponseEntity<>(bookLiteraryService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<BookLiterary> createBook(@RequestBody BookLiteraryDTO dto) {
        return new ResponseEntity<>(bookLiteraryService.create(dto), HttpStatus.OK);
    }

    @PutMapping("/book")
    public ResponseEntity<BookLiterary> updateBook(@RequestBody BookLiteraryDTO dto) {
        return new ResponseEntity<>(bookLiteraryService.update(dto), HttpStatus.OK);
    }

    @DeleteMapping("/book/{id}")
    public HttpStatus deleteBook(@PathVariable Long id) {
        bookLiteraryService.delete(id);
        return HttpStatus.OK;
    }
}
