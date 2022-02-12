package ru.budimirov.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.budimirov.library.service.BookServiceImpl;


@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {
    private final BookServiceImpl bookService;

    @ShellMethod(value = "Create book", key = {"c", "create"})
    public void createBook() {
        bookService.createBook();
    }

    @ShellMethod(value = "Update book", key = {"u", "update"})
    public void updateBook() {
        bookService.updateBookById();
    }

    @ShellMethod(value = "Get book", key = {"g", "get"})
    public void getBook() {
        bookService.getBookById();
    }

    @ShellMethod(value = "Delete book", key = {"d", "delete"})
    public void deleteBook() {
        bookService.deleteBook();
    }

    @ShellMethod(value = "Count books", key = {"n", "number"})
    public void countBook() {
        bookService.countBooks();
    }
}
