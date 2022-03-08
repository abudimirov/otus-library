package ru.budimirov.library.repository;

import org.springframework.data.repository.CrudRepository;
import ru.budimirov.library.domain.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}
