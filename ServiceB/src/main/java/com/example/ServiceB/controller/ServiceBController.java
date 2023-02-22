package com.example.ServiceB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ServiceB.entity.Book;
import com.example.ServiceB.service.ServiceBBookService;


@RestController
@RequestMapping("/ServiceB")
public class ServiceBController {
	
    @Autowired
    private ServiceBBookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }


    @PostMapping("/post/book")
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }
}
