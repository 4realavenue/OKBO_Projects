package com.okbo_projects.domain.board.model.response;

import com.okbo_projects.common.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ViewListOfMyArticlesWrittenResponse {
    private Long id;
    private String title;
    private String content;
    private String team;
     private Long likes;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ViewListOfMyArticlesWrittenResponse from(Board board) {
        return new ViewListOfMyArticlesWrittenResponse(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getTeam().getTeamName(),
                5L, //TODO : likes 구현 후, 게시글별 좋아요 수 가져오도록 수정
                board.getCreatedAt(),
                board.getModifiedAt()
        );
    }
}