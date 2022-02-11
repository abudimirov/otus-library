package ru.budimirov.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.budimirov.library.domain.Author;
import ru.budimirov.library.domain.Book;
import ru.budimirov.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;
    private final AuthorDaoJdbc authorDao;

    @Override
    public int count() {
        Optional<Integer> booksCount;
        booksCount = Optional.ofNullable(jdbc
            .getJdbcOperations()
            .queryForObject("select count(*) from books", Integer.class));
        return booksCount.orElse(0);
    }

    @Override
    public void insert(Book book) {
        jdbc.update("insert into books (id, name, author, genre) values (:id, :name, :author, :genre)",
            Map.of("id", book.getId(),
                "name", book.getName(),
                "author", book.getAuthor(),
                "genre", book.getGenre()));
    }

    @Override
    public Book getById(long id) {
        return jdbc.queryForObject(
            "select * from books where id = :id", Map.of("id", id), new BooksMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books", new BooksMapper());
    }

    @Override
    public void deleteById(long id) {
        jdbc.update(
            "delete from books where id = :id", Map.of("id", id)
        );
    }

    private static class BooksMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Author author = (Author) resultSet.getObject("author");
            Genre genre = (Genre) resultSet.getObject("genre");

            return new Book(id, name, author, genre);
        }
    }
}
