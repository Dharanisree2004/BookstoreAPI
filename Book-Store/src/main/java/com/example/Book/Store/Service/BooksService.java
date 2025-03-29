package com.example.Book.Store.Service;


import com.example.Book.Store.Model.Books;
import com.example.Book.Store.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    private final BookRepository bookRepository;

    public BooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Books saveBook(Books book) {
        return bookRepository.save(book);
    }

    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Books> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void deleteBookById(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new RuntimeException("Book not found with ID: " + id);
        }
    }
}
