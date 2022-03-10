package ru.budimirov.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.budimirov.library.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

