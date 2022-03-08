package ru.budimirov.library.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    private String content;
}
