package ru.budimirov.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.budimirov.library.domain.Author;
import ru.budimirov.library.repository.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public void createAuthor(final String name) {
        Author author = new Author();
        author.setName(name);
        authorRepository.save(author);
    }
}
