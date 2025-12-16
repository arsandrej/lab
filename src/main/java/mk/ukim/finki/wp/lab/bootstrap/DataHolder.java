package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.*;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.repository.BookReservationRepository;
import mk.ukim.finki.wp.lab.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();
    public static List<User> users = new ArrayList<>();

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookReservationRepository bookReservationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataHolder(AuthorRepository authorRepository, BookRepository bookRepository, BookReservationRepository bookReservationRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookReservationRepository = bookReservationRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {

        if(authorRepository.findAll().isEmpty()){
            authors.add(new Author("Martina", "Ivanovska", "Makedonija", "Very talented writer", Gender.FEMALE));
            authors.add(new Author("Andrej", "Arsovski","Makedonija", "Very untalented writer", Gender.MALE));
            authors.add(new Author("Antonio", "Krpacovski", "Makedonija", "Very writer", Gender.MALE));
            authors.add(new Author("Marko", "Markovski","Makedonija", "Very", Gender.MALE));
            authorRepository.saveAll(authors);
        }
        if(bookRepository.findAll().isEmpty()){
            books.add(new Book("Harry Potter", "Fantasy", 5.0, authors.get(0)));
            books.add(new Book("1984", "Dystopian", 4.5, authors.get(1)));
            books.add(new Book("The Hitchhiker's Guide To The Galaxy", "Sci-Fi", 4.2, authors.get(2)));
            books.add(new Book("Brave New World", "Dystopian", 3.7, authors.get(3)));
            bookRepository.saveAll(books);
        }

        if (userRepository.findAll().isEmpty()) {
            users.add(new User("martina", passwordEncoder.encode("martina"), "Martina", "Ivanovska", Role.ROLE_USER));
            users.add(new User("andrej", passwordEncoder.encode("andrej"), "Andrej", "Arsovski", Role.ROLE_USER));
            users.add(new User("admin", passwordEncoder.encode("admin"), "admin", "admin", Role.ROLE_ADMIN));
            userRepository.saveAll(users);
        }

    }

}