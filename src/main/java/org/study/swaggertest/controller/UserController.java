package org.study.swaggertest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.study.swaggertest.dto.LoginRequestDto;
import org.study.swaggertest.entity.User;
import org.study.swaggertest.service.UserService;

import java.util.List;
import java.util.Optional;

@Tag(name = "회원 API", description = "회원 관련 API입니다.")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "[회원] 모든 회원 가져오기", description = "모든 회원을 가져옵니다.")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @Operation(summary = "[회원] 회원ID로 회원 가져오기", description = "회원 ID로 특정 회원을 가져옵니다.")
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(
            @Parameter(description = "상세 정보를 가져올 회원의 ID")
            @PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "[회원] 회원 생성하기", description = "회원에 대한 상세 정보로 새 회원을 생성합니다.")
    @PostMapping("/users")
    public ResponseEntity<User> createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "회원에 대한 상세정보",
                    required = true, content = @Content(schema = @Schema(implementation = User.class)))
            @RequestBody User user) {
        User createdUser = userService.save(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @Operation(summary = "[회원] 회원 수정하기", description = "존재하는 회원을 수정합니다.")
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(
            @Parameter(description = "수정할 회원의 ID")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "수정될 회원의 상세정보",
                    required = true, content = @Content(schema = @Schema(implementation = User.class)))
            @RequestBody User user) {
        return userService.findById(id)
                .map(existingUser -> {
                    user.setUserId(existingUser.getUserId());
                    return ResponseEntity.ok(userService.save(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "[회원] 회원 삭제하기", description = "회원 ID로 특정 회원을 삭제합니다.")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(
            @Parameter(description = "삭제될 회원의 ID")
            @PathVariable Long id ) {
        return userService.findById(id)
                .map(existingUser -> {
                    userService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "[회원] 로그인", description = "username과 password로 로그인합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 요청 성공시 닉네임을 응답 받습니다.", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"nickname\": \"닉네임\"}")
            )),
            @ApiResponse(responseCode = "401", description = "일치하는 회원이 없을 경우의 응답 코드입니다.", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "일치하는 정보가 없습니다.")
            ))
    })
    @PostMapping("/users/login")
    public ResponseEntity<String> login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "로그인 정보",
                    required = true, content = @Content(schema = @Schema(implementation = LoginRequestDto.class)))
            @RequestBody LoginRequestDto loginRequest) {
        Optional<String> nickname = userService.login(loginRequest.username(), loginRequest.password());
        return nickname.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).body("일치하는 정보가 없습니다."));
    }

}
