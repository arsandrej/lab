package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Gender;

import java.util.List;

public interface AuthorService {
    public List<Author> findAll();
    public Author findById(Long id);
    Author deleteById(Long id);
    void update(long authorId, String name, String surname, String country, String biography, Gender gender);
    void save(String name, String surname, String country, String biography, Gender gender);
}