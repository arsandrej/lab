package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();

    @PostConstruct
    public void init() {
        books.add(new Book("Harry Potter", "Fantasy", 5.0));
        books.add(new Book("1984", "Dystopian", 4.5));
        books.add(new Book("The Hitchhiker's Guide To The Galaxy", "Sci-Fi", 4.2));
        books.add(new Book("Brave New World", "Dystopian", 3.7));}
}