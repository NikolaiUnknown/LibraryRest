package com.libriary.libraryrest.controller;

import com.libriary.libraryrest.entity.Book;
import com.libriary.libraryrest.entity.User;
import com.libriary.libraryrest.security.UserDetailsImpl;
import com.libriary.libraryrest.service.book.BookService;
import com.libriary.libraryrest.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
    private final BookService bookService;
    @Autowired
    public MainController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/all")
    public List<Book> getAllBook(){
        return bookService.getAllBook();
    }

    @GetMapping("/taken")
    public List<Book> getTakenBook(Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return bookService.getTakenBook(userDetails.getUser().getId());
    }
    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable("id") int id){
        return bookService.getBook(id);
    }
    @PostMapping("/take/{id}")
    public ResponseEntity<?> takeBook(@PathVariable("id") int id, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        bookService.saveUserBook((int) userDetails.getUser().getId(),id);
        return ResponseEntity.ok("book was taked");
    }
    @PostMapping("/return/{id}")
    public ResponseEntity<?> returnBook(@PathVariable("id") int id, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        bookService.returnUserBook((int) userDetails.getUser().getId(),id);
        return ResponseEntity.ok("book was returned");
    }

}
