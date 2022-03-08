package ru.budimirov.library.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@RequiredArgsConstructor
@Setter
@Getter
@ToString
public class Genre implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String name;
}
