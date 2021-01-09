package com.example.optimisticlock.service;

import com.example.optimisticlock.repo.Book;
import com.example.optimisticlock.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  public Book createBook(String name) {
    Book created = new Book();
    created.setName(name);
    return save(created);
  }

  public void produceOptimisticLock(Long id) {
    Book found = bookRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Oops"));
    found.setName("optLock !");
    found.setVersion(-777L);
    save(found);
  }

  private Book save(Book input) {
    return bookRepository.save(input);
  }
}
