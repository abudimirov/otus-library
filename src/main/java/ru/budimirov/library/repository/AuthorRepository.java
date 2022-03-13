package ru.budimirov.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.budimirov.library.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorByName(String name);
}
