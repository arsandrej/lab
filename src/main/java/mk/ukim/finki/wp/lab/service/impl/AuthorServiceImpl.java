package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Gender;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private  final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author deleteById(Long id) {
        Author author = authorRepository.findById(id);
        authorRepository.delete(author);
        return author;
    }

    @Override
    public void update(long authorId, String name, String surname, String country, String biography, Gender gender) {
        authorRepository.update(authorId,name,surname,country,biography,gender);
    }

    @Override
    public void save(String name, String surname, String country, String biography, Gender gender) {
        authorRepository.save(name,surname,country,biography,gender);
    }
}