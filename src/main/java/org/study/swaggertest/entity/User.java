package org.study.swaggertest.entity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, updatable = false)
    @Hidden
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @Hidden
    private LocalDateTime updatedAt;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(length = 50)
    private String oauthProvider;

    @Column(length = 100)
    private String oauthProviderId;

    @Column(length = 255)
    private String oauthProfilePicture;

    @Column(nullable = false)
    private Boolean twoFactorEnabled;

    @Column(length = 100)
    private String twoFactorSecret;

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
