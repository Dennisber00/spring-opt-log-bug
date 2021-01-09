package com.example.optimisticlock.dev;

import com.example.optimisticlock.repo.Book;
import com.example.optimisticlock.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Log
public class DoMagic {

  private final BookService bookService;

  @Value("${do.magic.fire.opt.lock}")
  private boolean isDoMagicFireOptLock;

  @PostConstruct
  public void init() {
    log.info("!!! DoMagic was created");
  }

  @EventListener
  public void handle(ApplicationReadyEvent event) {
    Book created = bookService.createBook("doMagicCreated");

    if (isDoMagicFireOptLock) {
      bookService.produceOptimisticLock(created.getId());
    }
  }

}
