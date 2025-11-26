package com.okbo_projects.domain.board.model.response;

import com.okbo_projects.domain.board.model.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardReadTeamPageResponse {
    private Long id;
    private String title;
    private String writer;

    public static BoardReadTeamPageResponse from(BoardDto boardDto) {
        return new BoardReadTeamPageResponse(
                boardDto.getId(),
                boardDto.getTitle(),
                boardDto.getWriter()
        );
    }
}