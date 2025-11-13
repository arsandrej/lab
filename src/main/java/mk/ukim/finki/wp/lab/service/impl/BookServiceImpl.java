package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) throws NullPointerException {
        return bookRepository.searchBooks(text,rating);
    }

    @Override
    public Book save(Long id, String title, String genre, double averageRating, Author author) {
        return bookRepository.save(id, title,genre,averageRating,author);
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.getById(id);
    }

    @Override
    public Book update(Long bookId,String title, String genre, double averageRating, Author author) {
        Book book = bookRepository.getById(bookId);
        if(title != null || !title.isEmpty()
                || genre != null || !genre.isEmpty()
                || averageRating!=0 || author!=null
        ){
            book.setTitle(title);
            book.setGenre(genre);
            book.setAverageRating(averageRating);
            book.setAuthor(author);
            book.setId(bookId);
        }
        return book;

    }

    @Override
    public Book deleteById(Long id) {
        Book book= bookRepository.getById(id);
        bookRepository.delete(book);
        return book;
    }
}