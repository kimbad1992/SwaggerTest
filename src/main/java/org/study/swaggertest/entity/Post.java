package org.study.swaggertest.entity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
