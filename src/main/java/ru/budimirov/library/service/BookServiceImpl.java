package ru.budimirov.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.budimirov.library.dao.BookDaoJdbc;
import ru.budimirov.library.domain.Author;
import ru.budimirov.library.domain.Book;
import ru.budimirov.library.domain.Genre;

import java.util.Random;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDaoJdbc bookDao;

    @Override
    @Transactional
    public void createBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Book id:");
        long id = Long.parseLong(scanner.nextLine());
        System.out.println("Book name:");
        String name = scanner.nextLine();
        System.out.println("Book author:");
        String authorName = scanner.nextLine();
        Author author = new Author(new Random().nextLong(), authorName);
        System.out.println("Book genre:");
        String genreName = scanner.nextLine();
        Genre genre = new Genre(new Random().nextLong(), genreName);
        Book book = new Book(id, name, author, genre);
        bookDao.insert(book);
        System.out.printf("Book %s by %s is created\n", book.getName(), book.getAuthor().getName());
    }

    @Override
    @Transactional
    public void getBookById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Book id:");
        long id = scanner.nextLong();
        System.out.println(bookDao.getById(id));
    }

    @Override
    public void updateBookById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Book id:");
        long id = scanner.nextLong();
        Book book = bookDao.getById(id);
        System.out.println("New book name:");
        String name = scanner.nextLine();
        book.setName(name);
        bookDao.insert(book);
        System.out.println("Book was updated");
    }

    @Override
    public void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Book id:");
        long id = scanner.nextLong();
        System.out.println("Book to be deleted: " + bookDao.getById(id));
        bookDao.deleteById(id);
        System.out.println("Book deleted!");
    }

    @Override
    public void countBooks() {
        System.out.println("Total books in library: " + bookDao.count());
    }
}
