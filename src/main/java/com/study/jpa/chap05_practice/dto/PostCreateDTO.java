package com.study.jpa.chap05_practice.dto;

import com.study.jpa.chap05_practice.entity.Post;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateDTO {

    // @NotNull -> null (x), "", " " (o)
    // @NotEmpty -> null, "" (x), " " (o)
    @NotBlank // -> null, "", " " (x)
    @Size(min = 2, max = 5)
    private String writer;

    @NotBlank
    @Size(min = 1, max = 20)
    private String title;

    private String content;
    private List<String> hashTags;

    // dto를 엔터티로 변환하는 메서드
    public Post toEntity() {
        return Post.builder()
                .writer(this.writer)
                .content(this.content)
                .title(this.title)
                // 해시태그 (x) 컬럼 없음, 조회 용
                .build();
    }

}
