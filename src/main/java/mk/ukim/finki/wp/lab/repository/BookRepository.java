package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAuthor_Id(Long authorId);

    // За search функционалноста (наслов или жанр + рејтинг)
    List<Book> findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCaseAndAverageRatingGreaterThanEqual(
            String title, String genre, Double rating
    );

    // Ако не се внесе рејтинг, само по текст
    List<Book> findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCase(String title, String genre);
}