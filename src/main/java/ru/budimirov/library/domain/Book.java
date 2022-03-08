package ru.budimirov.library.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(targetEntity = Author.class)
    private List<Author> author;

    @OneToMany(targetEntity = Genre.class)
    private List<Genre> genre;
}
