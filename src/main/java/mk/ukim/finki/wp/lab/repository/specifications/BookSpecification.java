package mk.ukim.finki.wp.lab.repository.specifications;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public static Specification<Book> titleOrGenreContains(String text) {
        if (text == null || text.isBlank()) {
            return null;
        }
        String pattern = "%" + text.toLowerCase() + "%";

        // Root is the 'Book' entity,
        // CriteriaQuery defines the query,
        // CriteriaBuilder builds the predicate
        return (root, query, builder) -> {
            // Predicate for Title LIKE '%text%'
            jakarta.persistence.criteria.Predicate titlePredicate =
                    builder.like(builder.lower(root.get("title")), pattern);

            // Genre LIKE '%text%'
            jakarta.persistence.criteria.Predicate genrePredicate =
                    builder.like(builder.lower(root.get("genre")), pattern);

            return builder.or(titlePredicate, genrePredicate);
        };
    }

    public static Specification<Book> averageRatingGreaterThanEqual(Double rating) {
        if (rating == null) {
            return null;
        }
        return (root, query, builder) ->
                builder.greaterThanOrEqualTo(root.get("averageRating"), rating);
    }

}
