package org.study.swaggertest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.study.swaggertest.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}