package org.study.swaggertest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.study.swaggertest.entity.CountryEnum;
import org.study.swaggertest.entity.Explain;
import org.study.swaggertest.service.WebScraper;

@Tag(name = "특정 API 그룹", description = "이 Tag를 설명하는 설명란입니다.\n" +
        "컨트롤러 최상단에 Tag 어노테이션을 사용해서 해당 컨트롤러에서 사용되는 API를 하나의 카테고리로 묶을 수 있습니다.")
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ExplanationController {

    @Autowired
    private WebScraper webScraper;

    @Operation(summary = "Operation 어노테이션의 summary 속성입니다.", description = "description 속성입니다. 해당 API를 설명합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "API 실행결과(HTTP 응답코드)에 따라 설명을 작성할 수 있습니다."),
            @ApiResponse(responseCode = "404", description = "응답 코드 404에 대한 설명을 별개로 작성할 수 있습니다.", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"message\": \"응답 오브젝트에 대한 설명도\", \"details\": \"추가할 수 있습니다.\"}")
            ))
    })
    @GetMapping("/explain/{id}")
    public ResponseEntity<?> explanation(
            @Parameter(description = "해당 파라미터의 설명을 작성합니다.")
            @PathVariable Object someObject) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Swagger 파라미터 설명용")
    @PostMapping("/explain/parameter")
    public ResponseEntity<?> explanationParam(
            @Parameter(description = "Enum등을 파라미터로 활용할 수도 있습니다.")
            @RequestParam CountryEnum countryEnum,
            @Parameter(description = "필드에 스키마를 추가하여 설명을 작성하거나, 검증 어노테이션과 같이 활용할 수 있습니다.")
            @RequestBody @Valid Explain explain,
            HttpServletRequest request) {
        log.debug("Authorization Header : {}", request.getHeader("Authorization"));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Selenium 테스트용 API")
    @PostMapping("/explain/selenium")
    public ResponseEntity<?> seleniumTest(
            @Parameter(description = "Triggered URL") @RequestBody String httpUrl) {
        // 동적으로 대상 URL을 지정할 경우 사용할?
        log.debug("Scrapping URL : {}", httpUrl);
        webScraper.scrapping();
        return ResponseEntity.ok().build();
    }
}
