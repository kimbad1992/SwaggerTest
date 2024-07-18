package org.study.swaggertest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.study.swaggertest.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}