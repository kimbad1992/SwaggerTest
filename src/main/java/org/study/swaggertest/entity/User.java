package org.study.swaggertest.entity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(name = "유저", description = "유저 정보 Entity")
@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long userId;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, unique = true, length = 100) // unique 제약 조건 추가
    private String nickname;

    @Column(nullable = false, updatable = false)
    @Hidden
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @Hidden
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

