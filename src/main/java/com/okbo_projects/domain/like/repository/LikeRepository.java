package com.okbo_projects.domain.like.repository;

import com.okbo_projects.common.entity.Board;
import com.okbo_projects.common.entity.Comment;
import com.okbo_projects.common.entity.Like;
import com.okbo_projects.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    // 로그인 한 유저가 좋아요 한 게시글의 좋아요 삭제
    void deleteByBoardAndUser(Board board, User user);

    // 좋아요 여부 확인
    boolean existsByBoardAndUser(Board board, User user);
    boolean existsByCommentAndUser(Comment comment, User user);

    // 게시글 별 좋아요 카운트
    Long countByBoard(Board board);

    // 댓글 별 좋아요 카운트
    Long countByComment(Comment comment);
}