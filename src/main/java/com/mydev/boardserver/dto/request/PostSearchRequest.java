package com.mydev.boardserver.dto.request;

import com.mydev.boardserver.dto.SortStatus;
import lombok.*;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostSearchRequest {

    private int id;
    private String name;
    private String contents;
    private int views;
    private int categoryId;
    private int userId;
    private SortStatus sortStatus;
}
//객체를 생성하는 패턴
