package com.okbo_projects.domain.board.controller;

import com.okbo_projects.common.model.SessionUser;
import com.okbo_projects.domain.board.model.request.CreateBoardRequest;
import com.okbo_projects.domain.board.model.request.UpdateBoardRequest;
import com.okbo_projects.domain.board.model.response.*;
import com.okbo_projects.domain.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //게시글 생성
    @PostMapping("/create")
    public ResponseEntity<CreateBoardResponse> createBoard(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @Valid @RequestBody CreateBoardRequest request
    ){
        CreateBoardResponse result = boardService.createBoard(sessionUser, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    //게시글 수정
    @PutMapping("/{boardId}")
    public ResponseEntity<UpdateBoardResponse> updateBoard(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PathVariable Long boardId,
            @Valid @RequestBody UpdateBoardRequest request
    ){
        UpdateBoardResponse result = boardService.updateBoard(sessionUser, boardId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //게시글 상세조회
    @GetMapping("/{boardId}")
    public ResponseEntity<DetailedInquiryBoardResponse> detailedInquiryBoard(
            @PathVariable Long boardId
    ){
        DetailedInquiryBoardResponse result = DetailedInquiryBoardResponse.from(boardService.detailedInquiryBoard(boardId));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //내가 작성한 게시글 목록 조회
    @GetMapping("/myBoard")
    public ResponseEntity<Page<ViewListOfMyArticlesWrittenResponse>> viewListOfMyArticlesWritten(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        Page<ViewListOfMyArticlesWrittenResponse> result = boardService.viewListOfMyArticlesWritten(sessionUser, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 게시글 전체 조회
    @GetMapping
    public ResponseEntity<Page<BoardReadAllPageResponse>> getBoardAllPage(
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<BoardReadAllPageResponse> result = boardService.getBoardAllPage(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 게시글 구단별 전체 조회
    @GetMapping("/teams/{teamName}")
    public ResponseEntity<Page<BaordReadTeamPageResponse>> getBoardTeamAllPage(
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable String teamName
    ) {
        Page<BaordReadTeamPageResponse> result = boardService.getBoardTeamAllPage(pageable, teamName);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 팔로워 게시글 조회
    @GetMapping("/followings")
    public ResponseEntity<Page<BoardReadFollowPageResponse>> getBoardFollowAllPage(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<BoardReadFollowPageResponse> result = boardService.getBoardFollowAllPage(sessionUser, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 게시글 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PathVariable Long boardId
    ) {
        boardService.deleteBoard(sessionUser, boardId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}