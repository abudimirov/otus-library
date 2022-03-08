package ru.budimirov.library.repository;

import org.springframework.data.repository.CrudRepository;
import ru.budimirov.library.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
