package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "authors")
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String country;
    private String biography;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    public Author(String name, String surname, String country, String biography, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.biography = biography;
        this.gender = gender;
    }
}