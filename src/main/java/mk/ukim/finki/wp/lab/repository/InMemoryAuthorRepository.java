package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Gender;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryAuthorRepository implements AuthorRepository {

    @Override
    public List<Author> findAll() {
        return DataHolder.authors;
    }

    @Override
    public Author findById(Long id) {
        return DataHolder.authors.stream().filter(a->a.getId().equals(id)).findFirst().get();
    }

    @Override
    public Author delete(Author Author) {
        DataHolder.authors.remove(Author);
        return Author;

    }

    @Override
    public Author save(String name, String surname, String country, String biography, Gender gender) {
        Author author = new Author(name,surname,country,biography,gender);
        DataHolder.authors.add(author);
        return author;
    }

    @Override
    public Author update(long authorId, String name, String surname, String country, String biography, Gender gender) {
        Author a = findById(authorId);
        a.setName(name);
        a.setSurname(surname);
        a.setCountry(country);
        a.setBiography(biography);
        a.setGender(gender);
        return a;
    }
}