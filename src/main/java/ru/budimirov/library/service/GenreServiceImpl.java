package ru.budimirov.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.budimirov.library.domain.Genre;
import ru.budimirov.library.repository.GenreRepository;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    @Transactional
    public void createGenre(final String name) {
        Genre genre = new Genre();
        genre.setName(name);
        genreRepository.save(genre);
    }
}
