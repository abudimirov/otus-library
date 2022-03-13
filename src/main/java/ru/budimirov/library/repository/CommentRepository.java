package ru.budimirov.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.budimirov.library.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
