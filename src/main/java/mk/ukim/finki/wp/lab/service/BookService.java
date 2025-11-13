package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);
    Book save(Long id,String title, String genre, double averageRating, Author author);
    Book getById(Long id);
    Book update(Long bookId,String title, String genre, double averageRating, Author author);
    Book deleteById(Long id);
}