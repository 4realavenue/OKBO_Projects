package com.okbo_projects.domain.board.model.response;

import com.okbo_projects.common.entity.Comment;
import com.okbo_projects.domain.board.model.dto.BoardDto;
import com.okbo_projects.domain.comment.model.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDetailedInquiryResponse {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String team;
    private Long likes;
    private LocalDateTime creatAt;
    private LocalDateTime modifiedAt;

    public static BoardDetailedInquiryResponse from(BoardDto dto, Long likes) {
        return new BoardDetailedInquiryResponse(
                dto.getId(),
                dto.getTitle(),
                dto.getContent(),
                dto.getWriter(),
                dto.getTeam().getTeamName(),
                likes,
                dto.getCreatedAt(),
                dto.getModifiedAt()
        );
    }
}