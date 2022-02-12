package ru.budimirov.library.service;

import ru.budimirov.library.domain.Book;

public interface BookService {
    void createBook();
    void getBookById();
    void updateBookById();
    void deleteBook();
    void countBooks();
}
