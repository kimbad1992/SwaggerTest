package org.study.swaggertest.entity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(name = "설명용 DTO", description = "스키마를 DTO 클래스에 추가할 경우 DTO의 설명을 작성할 수 있습니다.")
public class Explain {

    @Schema(description = "스키마를 DTO의 필드에 추가할 경우 해당 필드에 대한 설명을 작성할 수 있습니다.")
    private Long id;

    @Schema(description = "example 속성을 통해 해당 필드의 예시를 추가할 수 있습니다.", example = "이런값을주세요")
    private String name;

    @Schema(description = "Validation 설명용 필드")
    @NotBlank(message = "난비어있을수없어")
    private String validMe;

    @Hidden
    private String hideMe;
}
