package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.repository.specifications.BookSpecification;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.data.jpa.domain.Specification;
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
    public List<Book> searchBooksAdvanced(String text, Double rating) {
        Specification<Book> spec = null;

        Specification<Book> textSpec = BookSpecification.titleOrGenreContains(text);

        Specification<Book> ratingSpec = BookSpecification.averageRatingGreaterThanEqual(rating);

            // Case 1: Only text
        if (textSpec != null && ratingSpec == null) {
            spec = textSpec;
            // Case 2: Only rating
        } else if (textSpec == null && ratingSpec != null) {
            spec = ratingSpec;
            // Case 3: Both text AND rating
        } else if (textSpec != null && ratingSpec != null) {
            spec = textSpec.and(ratingSpec);
        }

            // Case 4: Neither text nor rating
        if (spec == null) {
            return listAll();
        }

        // Method which executes the query.
        return bookRepository.findAll(spec);
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
