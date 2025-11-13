package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Gender;

import java.util.List;

public interface AuthorRepository {
    public List<Author> findAll();
    public Author findById(Long id);
    Author delete(Author Author);
    Author save( String name, String surname, String country, String biography, Gender gender);
    Author update(long authorId, String name, String surname, String country, String biography, Gender gender);
}