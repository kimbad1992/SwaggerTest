package org.study.swaggertest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.study.swaggertest.entity.Comment;
import org.study.swaggertest.entity.Post;
import org.study.swaggertest.service.CommentService;

import java.util.List;

@Tag(name = "댓글 API", description = "댓글 관련 API입니다.")
@RestController
@RequestMapping("/api/v1")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Operation(summary = "[댓글] 모든 댓글 가져오기", description = "모든 댓글을 가져옵니다.")
    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        return commentService.findAll();
    }

    @Operation(summary = "[댓글] 댓글ID로 댓글 가져오기", description = "댓글 ID로 특정 댓글을 가져옵니다.")
    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentById(
            @Parameter(description = "상세 정보를 가져올 댓글의 ID")
            @PathVariable Long id) {
        return commentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "[댓글] 댓글 생성하기", description = "댓글을 생성합니다.")
    @PostMapping("/comments")
    public Comment createComment(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "댓글에 대한 상세정보",
                    required = true, content = @Content(schema = @Schema(implementation = Comment.class)))
            @RequestBody Comment comment) {
        return commentService.save(comment);
    }

    @Operation(summary = "[댓글] 댓글 수정하기", description = "존재하는 댓글을 수정합니다.")
    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(
            @Parameter(description = "업데이트할 댓글의 ID")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "수정될 댓글의 상세정보",
                    required = true, content = @Content(schema = @Schema(implementation = Post.class)))
            @RequestBody Comment comment) {
        return commentService.findById(id)
                .map(existingComment -> {
                    comment.setCommentId(existingComment.getCommentId());
                    return ResponseEntity.ok(commentService.save(comment));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "[댓글] 댓글 삭제하기", description = "댓글 ID로 특정 댓글을 삭제합니다.")
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> deleteComment(
            @Parameter(description = "삭제될 댓글의 ID")
            @PathVariable Long id) {
        return commentService.findById(id)
                .map(existingComment -> {
                    commentService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
