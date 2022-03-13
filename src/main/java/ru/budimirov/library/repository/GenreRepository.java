package ru.budimirov.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.budimirov.library.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findGenreByName(String name);
}
