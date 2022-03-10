package ru.budimirov.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.budimirov.library.domain.Book;
import ru.budimirov.library.exception.BookServiceException;
import ru.budimirov.library.repository.AuthorRepository;
import ru.budimirov.library.repository.BookRepository;
import ru.budimirov.library.repository.GenreRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreServiceImpl genreService;
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public void createBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Book name:");
        String name = scanner.nextLine();
        System.out.println("Book author:");
        String authorName = scanner.nextLine();
        authorService.createAuthor(authorName);
        System.out.println("Book genre:");
        String genreName = scanner.nextLine();
        genreService.createGenre(genreName);
        Book book = new Book();
        book.setName(name);
        book.setAuthor(Collections.singletonList(authorRepository.findAuthorByName(authorName)));
        book.setGenre(Collections.singletonList(genreRepository.findGenreByName(genreName)));
        bookRepository.save(book);
        StringBuilder sb = new StringBuilder();
        book.getAuthor().forEach(b -> sb.append(b.toString()));
        System.out.printf("Book %s by %s is created\n", book.getName(), sb);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public void getBookById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Book id:");
        long id = scanner.nextLong();
        System.out.println(bookRepository.findById(id));
    }

    @Override
    public void updateBookById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Book id:");
        long id = scanner.nextLong();
        Optional<Book> book = Optional.ofNullable(bookRepository.findById(id).orElseThrow(() -> new BookServiceException("No book with that id")));
        System.out.println("New book name:");
        String name = scanner.nextLine();
        book.get().setName(name);
        bookRepository.save(book.get());
        System.out.println("Book was updated");
    }

    @Override
    public void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Book id:");
        long id = scanner.nextLong();
        System.out.println("Book to be deleted: " + bookRepository.findById(id));
        bookRepository.deleteById(id);
        System.out.println("Book deleted!");
    }

    @Override
    public void countBooks() {
        System.out.println("Total books in library: " + bookRepository.count());
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public void listAllBooks() {
        countBooks();
        bookRepository.findAll().forEach(book -> System.out.println(book.toString()));
    }


}
