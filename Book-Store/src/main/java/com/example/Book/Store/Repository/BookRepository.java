package com.example.Book.Store.Repository;

import com.example.Book.Store.Model.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Books,Long> {
    List<Books> findByAuthor(String author);
    List<Books> findByCategory(String category);
    List<Books> findByRatingGreaterThanEqual(Double rating);
    Page<Books> findAll(Pageable pageable);

}
