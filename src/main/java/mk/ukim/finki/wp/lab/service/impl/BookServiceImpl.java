package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        if ((text == null || text.isBlank()) && rating == null) {
            return listAll();
        }
        if (rating == null) {
            return bookRepository.findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCase(text, text);
        } else {
            return bookRepository.findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCaseAndAverageRatingGreaterThanEqual(text, text, rating);
        }
    }

    @Override
    @Transactional
    public Book save(String title, String genre, double averageRating, Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found: " + authorId));
        Book book = new Book(title, genre, averageRating, author);
        return bookRepository.save(book);
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    @Override
    @Transactional
    public Book update(Long bookId, String title, String genre, double averageRating, Long authorId) {
        Book book = getById(bookId);
        if (title != null && !title.isBlank()) book.setTitle(title);
        if (genre != null && !genre.isBlank()) book.setGenre(genre);
        if (averageRating >= 0) book.setAverageRating(averageRating);
        if (authorId != null) {
            Author author = authorRepository.findById(authorId)
                    .orElseThrow(() -> new RuntimeException("Author not found: " + authorId));
            book.setAuthor(author);
        }
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAllByAuthorId(Long authorId) {
        return bookRepository.findAllByAuthor_Id(authorId);
    }
}
