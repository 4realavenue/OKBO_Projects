package com.okbo_projects.domain.board.model.response;

import com.okbo_projects.domain.board.model.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardReadAllPageResponse {
    private Long id;
    private String title;
    private String team;
    private String writer;
     private Long likes;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static BoardReadAllPageResponse from(BoardDto boardDto) {
        return new BoardReadAllPageResponse(
                boardDto.getId(),
                boardDto.getTitle(),
                boardDto.getTeam().getTeamName(),
                boardDto.getWriter(),
                5L, //TODO : likes 구현 후, 게시글별 좋아요 수 가져오도록 수정
                boardDto.getCreatedAt(),
                boardDto.getModifiedAt()
        );
    }
}