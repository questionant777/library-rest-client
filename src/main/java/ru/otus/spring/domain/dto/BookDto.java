package ru.otus.spring.domain.dto;

import lombok.*;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookDto {

    private Long id;

    private String name;

    private String author;

    private String genre;

    private List<String> bookCommentList;

}
