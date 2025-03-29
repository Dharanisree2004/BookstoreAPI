package com.example.Book.Store.Controller;

import com.example.Book.Store.Model.Books;
import com.example.Book.Store.Service.BooksService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BooksService bookService;
    public BookController(BooksService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Books book) {
        try {
            Books savedBook = bookService.saveBook(book);
            return ResponseEntity.ok(savedBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving book: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        try {
            Optional<Books> book = bookService.getBookById(id);
            if (book.isPresent()) {
                return ResponseEntity.ok(book.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching book: " + e.getMessage());
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Books updatedBook) {
        try {
            Optional<Books> existingBook = bookService.getBookById(id);
            if (existingBook.isPresent()) {
                Books book = existingBook.get();
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setCategory(updatedBook.getCategory());
                book.setPrice(updatedBook.getPrice());
                book.setRating(updatedBook.getRating());

                Books savedBook = bookService.saveBook(book);
                return ResponseEntity.ok(savedBook);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating book: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            Optional<Books> book = bookService.getBookById(id);
            if (book.isPresent()) {
                bookService.deleteBookById(id);
                return ResponseEntity.ok("Book deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting book: " + e.getMessage());
        }
    }
    @GetMapping("/filter")
    public ResponseEntity<List<Books>> filterBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double rating) {
        return ResponseEntity.ok(bookService.filterBooks(author, category, rating));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Books>> searchBooks(@RequestParam String title) {
        return ResponseEntity.ok(bookService.searchBooksByTitle(title));
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Books>> getSortedBooks(@RequestParam String sortBy) {
        return ResponseEntity.ok(bookService.getAllBooksSorted(sortBy));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Books>> getPaginatedBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }




}
