package ru.budimirov.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Author implements Serializable {
    private long id;
    private String name;
}
