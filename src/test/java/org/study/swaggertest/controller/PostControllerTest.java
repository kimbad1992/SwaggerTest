package org.study.swaggertest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.study.swaggertest.entity.Post;
import org.study.swaggertest.service.PostService;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    private Post post;

    @BeforeEach
    void setUp() {
        post = new Post();
        post.setPostId(1L);
        post.setTitle("Test Title");
        post.setContent("Test Content");
    }

    @Test
    void testGetAllPosts() throws Exception {
        given(postService.findAll()).willReturn(Arrays.asList(post));

        mockMvc.perform(get("/api/v1/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(post.getTitle()));
    }

    @Test
    void testGetPostById() throws Exception {
        given(postService.findById(anyLong())).willReturn(Optional.of(post));

        mockMvc.perform(get("/api/v1/posts/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(post.getTitle()));
    }

    @Test
    void testCreatePost() throws Exception {
        given(postService.save(any(Post.class))).willReturn(post);

        mockMvc.perform(post("/api/v1/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Test Title\", \"content\": \"Test Content\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(post.getTitle()));
    }

    @Test
    void testUpdatePost() throws Exception {
        given(postService.findById(anyLong())).willReturn(Optional.of(post));
        given(postService.save(any(Post.class))).willReturn(post);

        mockMvc.perform(put("/api/v1/posts/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Updated Title\", \"content\": \"Updated Content\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

    @Test
    void testDeletePost() throws Exception {
        given(postService.findById(anyLong())).willReturn(Optional.of(post));
        doNothing().when(postService).deleteById(anyLong());

        mockMvc.perform(delete("/api/v1/posts/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
