package com.okbo_projects.domain.comment.service;

import com.okbo_projects.common.entity.Board;
import com.okbo_projects.common.entity.Comment;
import com.okbo_projects.common.entity.User;
import com.okbo_projects.common.exception.CustomException;
import com.okbo_projects.common.model.SessionUser;
import com.okbo_projects.domain.board.repository.BoardRepository;
import com.okbo_projects.domain.comment.model.request.CommentUpdateRequest;
import com.okbo_projects.domain.comment.model.response.CommentUpdateResponse;
import com.okbo_projects.domain.comment.model.dto.CommentDto;
import com.okbo_projects.domain.comment.model.request.CommentCreateRequest;
import com.okbo_projects.domain.comment.model.response.CommentCreateResponse;
import com.okbo_projects.domain.comment.model.dto.CommentDto;
import com.okbo_projects.domain.comment.model.request.CommentUpdateRequest;
import com.okbo_projects.domain.comment.model.response.CommentUpdateResponse;
import com.okbo_projects.domain.comment.repository.CommentRepository;
import com.okbo_projects.domain.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.okbo_projects.common.exception.ErrorMessage.FORBIDDEN_ONLY_WRITER;

import static com.okbo_projects.common.exception.ErrorMessage.FORBIDDEN_ONLY_WRITER;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    // 댓글 생성
    public CommentCreateResponse createComment(Long boardId, SessionUser sessionUser, CommentCreateRequest request) {
        Board board = findByBoardId(boardId);
        User user = findByUserId(sessionUser.getUserId());
        Comment comment = new Comment(
                request.getComments(),
                user,
                board
        );
        commentRepository.save(comment);
        CommentDto commentDto = CommentDto.from(comment);
        return CommentCreateResponse.from(commentDto);
    }

    //댓글 수정
    public CommentUpdateResponse updateComment(SessionUser sessionUser, Long commentId, CommentUpdateRequest request) {
        Comment comment = findByCommentId(commentId);
        Long userId = sessionUser.getUserId();
        if (!comment.getWriter().getId().equals(userId)) {
            throw new CustomException(FORBIDDEN_ONLY_WRITER);
        }
        comment.update(request);
        commentRepository.save(comment);
        return CommentUpdateResponse.from(comment.toDto());
    }

    //댓글 삭제
    public void deleteComment(SessionUser sessionUser, Long commentId) {
        Comment comment = findByCommentId(commentId);
        Long userId = sessionUser.getUserId();
        if (!comment.getWriter().getId().equals(userId)) {
            throw new CustomException(FORBIDDEN_ONLY_WRITER);
        }
        commentRepository.delete(comment);
    }

    private Comment findByCommentId(Long commentId) {
        return commentRepository.findCommentById(commentId);
    }

}
