package ru.budimirov.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.budimirov.library.domain.Author;
import ru.budimirov.library.domain.Book;
import ru.budimirov.library.domain.Genre;
import ru.budimirov.library.exception.BookServiceException;
import ru.budimirov.library.repository.AuthorRepository;
import ru.budimirov.library.repository.BookRepository;
import ru.budimirov.library.repository.GenreRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    @Transactional
    public void createBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Book name:");
        String name = scanner.nextLine();
        System.out.println("Book author:");
        String authorName = scanner.nextLine();
        Author author = new Author();
        author.setName(authorName);
        authorRepository.save(author);
        System.out.println("Book genre:");
        String genreName = scanner.nextLine();
        Genre genre = new Genre();
        genre.setName(genreName);
        genreRepository.save(genre);
        Book book = new Book();
        book.setName(name);
        book.setAuthor(Collections.singletonList(author));
        book.setGenre(Collections.singletonList(genre));
        bookRepository.save(book);

        System.out.printf("Book %s by %s is created\n", book.getName(), book.getAuthor().stream().toString());
    }

    @Override
    @Transactional(readOnly = true)
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
}
