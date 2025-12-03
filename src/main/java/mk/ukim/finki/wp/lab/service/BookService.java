package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);
    Book save(String title, String genre, double averageRating, Long authorId);
    Book getById(Long id);
    Book update(Long bookId, String title, String genre, double averageRating, Long authorId);
    void deleteById(Long id);
    List<Book> findAllByAuthorId(Long authorId);
}
