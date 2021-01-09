package com.example.optimisticlock.web;

import com.example.optimisticlock.repo.Book;
import com.example.optimisticlock.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  @PostMapping("/book/new")
  public Book create(@RequestBody String name) {
    return bookService.createBook(name);
  }

  @PostMapping("/book/opt-lock/{id}")
  public void produceOptLock(@PathVariable Long id) {
    bookService.produceOptimisticLock(id);
  }
}
