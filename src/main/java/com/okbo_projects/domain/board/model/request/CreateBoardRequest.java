package com.okbo_projects.domain.board.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBoardRequest {
    private String title;
    private String team;
    private String content;
}
