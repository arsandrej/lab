package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Repository
public interface BookRepository extends  JpaSpecificationRepository<Book, Long> { // JpaRepository<Book, Long>,

    List<Book> findAllByAuthor_Id(Long authorId);

    List<Book> findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCaseAndAverageRatingGreaterThanEqual(
            String title, String genre, Double rating
    );

    List<Book> findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCase(String title, String genre);
}