package com.example.Book.Store.Service;


import com.example.Book.Store.Model.Books;
import com.example.Book.Store.Repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BooksService {
    private final BookRepository bookRepository;

    public BooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Books saveBook(Books book) {
        return bookRepository.save(book);
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
    public List<Books> filterBooks(String author, String category, Double rating) {
        return bookRepository.findAll().stream()
                .filter(book -> (author == null || book.getAuthor().equalsIgnoreCase(author)))
                .filter(book -> (category == null || book.getCategory().equalsIgnoreCase(category)))
                .filter(book -> (rating == null || book.getRating() >= rating))
                .collect(Collectors.toList());
    }
    public List<Books> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
    public Page<Books> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
    public List<Books> getAllBooksSorted(String sortBy) {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
    }



}
