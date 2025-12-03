package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Gender;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
    }

    @Override
    @Transactional
    public Author save(String name, String surname, String country, String biography, Gender gender) {
        Author author = new Author(name, surname, country, biography, gender);
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public Author update(Long id, String name, String surname, String country, String biography, Gender gender) {
        Author author = findById(id);
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        author.setBiography(biography);
        author.setGender(gender);
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
