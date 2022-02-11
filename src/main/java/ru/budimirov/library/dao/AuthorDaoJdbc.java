package ru.budimirov.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.budimirov.library.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int count() {
        Optional<Integer> authorCount;
        authorCount = Optional.ofNullable(jdbc
            .getJdbcOperations()
            .queryForObject("select count(*) from authors", Integer.class));
        return authorCount.orElse(0);
    }

    @Override
    public void insert(Author author) {
        jdbc.update("insert into authors (id, name) values (:id, :name)",
            Map.of("id", author.getId(), "name", author.getName()));
    }

    @Override
    public Author getById(long id) {
        return jdbc.queryForObject(
            "select * from authors where id = :id", Map.of("id", id), new AuthorMapper()
        );
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors", new AuthorMapper());
    }

    @Override
    public void deleteById(long id) {
        jdbc.update(
            "delete from authors where id = :id", Map.of("id", id)
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }
}
