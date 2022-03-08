package ru.budimirov.library.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class Author implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    private String name;
}
