package org.study.swaggertest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.study.swaggertest.entity.Post;
import org.study.swaggertest.service.PostService;

import java.util.List;

@Tag(name = "게시글 API", description = "게시글 관련 API입니다.")
@RestController
@RequestMapping("/api/v1")
public class PostController {

    @Autowired
    private PostService postService;

    @Operation(summary = "[게시글] 모든 게시글 가져오기", description = "모든 게시글을 가져옵니다.")
    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postService.findAll();
    }

    @Operation(summary = "[게시글] 게시글ID로 게시글 가져오기", description = "게시글 ID로 특정 게시글을 가져옵니다.")
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(
            @Parameter(description = "상세 정보를 가져올 게시글의 ID")
            @PathVariable Long id) {
        return postService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "[게시글] 게시글 생성하기", description = "게시글에 대한 상세 정보로 새 게시글을 생성합니다.")
    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "게시글에 대한 상세정보",
                    required = true, content = @Content(schema = @Schema(implementation = Post.class)))
            @RequestBody Post post) {
        Post createdPost = postService.save(post);
        return ResponseEntity.status(201).body(createdPost);
    }

    @Operation(summary = "[게시글] 게시글 수정하기", description = "존재하는 게시글을 수정합니다.")
    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(
            @Parameter(description = "수정할 게시글의 ID")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "수정될 게시글의 상세정보",
                    required = true, content = @Content(schema = @Schema(implementation = Post.class)))
            @RequestBody Post post) {
        return postService.findById(id)
                .map(existingPost -> {
                    post.setPostId(existingPost.getPostId());
                    return ResponseEntity.ok(postService.save(post));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "[게시글] 게시글 삭제하기", description = "게시글 ID로 특정 게시글을 삭제합니다.")
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(
            @Parameter(description = "삭제될 게시글의 ID")
            @PathVariable Long id) {
        return postService.findById(id)
                .map(existingPost -> {
                    postService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
