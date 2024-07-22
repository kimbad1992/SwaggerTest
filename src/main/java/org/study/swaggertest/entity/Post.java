package org.study.swaggertest.entity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
@Schema(name = "게시글", description = "게시글 엔티티입니다.")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long postId;

    private String title;
    private String content;
    private String author;

    @Hidden
    private LocalDateTime createdAt;
    @Hidden
    private LocalDateTime updatedAt;
}
