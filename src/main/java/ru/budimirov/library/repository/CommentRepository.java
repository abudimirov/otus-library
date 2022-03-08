package ru.budimirov.library.repository;

import org.springframework.data.repository.CrudRepository;
import ru.budimirov.library.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
