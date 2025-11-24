package com.okbo_projects.domain.board.service;

import com.okbo_projects.common.entity.Board;
import com.okbo_projects.common.entity.User;
import com.okbo_projects.domain.board.model.dto.BoardDto;
import com.okbo_projects.domain.board.model.request.CreateBoardRequest;
import com.okbo_projects.domain.board.model.request.UpdateBoardRequest;
import com.okbo_projects.domain.board.model.response.CreateBoardResponse;
import com.okbo_projects.domain.board.model.response.UpdateBoardResponse;
import com.okbo_projects.domain.board.model.response.ViewListOfMyArticlesWrittenResponse;
import com.okbo_projects.domain.board.repository.BoardRepository;
import com.okbo_projects.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    //게시글 생성
    public CreateBoardResponse createBoard(Long userId, CreateBoardRequest request) {
        //1.유저의 아이디를 검색하여 없으면 예외처리
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("없는 유저입니다."));
        //2.게시글 생성
        Board board = new Board(
                request.getTitle(),
                request.getContent(),
                user
        );
        //3.게시글 저장
        boardRepository.save(board);
        BoardDto dto = BoardDto.from(board);

        return CreateBoardResponse.from(dto);
    }

    //게시글 수정
    public UpdateBoardResponse updateBoard(Long id, UpdateBoardRequest request) {
        //1.게시글의 아이디를 검색하여 없으면 예외처리
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없는 게시글입니다."));
        //2.게시글 수정
        board.update(request);
        //3.게시글 저장
        boardRepository.save(board);
        //4.게시글 반환
        BoardDto dto = BoardDto.from(board);
        return UpdateBoardResponse.from(dto);

    }
    //게시글 상세조회
    @Transactional(readOnly = true)
    public BoardDto detailedInquiryBoard(Long id) {
        //1.게시글의 아이디를 검색하여 없으면 예외처리
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없는 게시글입니다."));
        //2.게시글 반환
        return BoardDto.from(board);
    }

    //내가 작성한 게시글 목록 조회
    @Transactional(readOnly = true)
    public List<ViewListOfMyArticlesWrittenResponse> viewListOfMyArticlesWritten(Long userId) {
        //1.유저의 아이디를 검색하여 없으면 예외처리
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("없는 유저입니다."));
        //2.내가 작성한 게시글 목록 조회
        List<Board> boards = boardRepository.findAllByWriter(user);
        //3.내가 작성한 게시글 목록 반환
        return boards.stream() //1.데이터 흐름 준비단계
                .map(ViewListOfMyArticlesWrittenResponse::from) //2.중간 연산 등록단계
                .collect(Collectors.toList());//3.최종 연산단계: 결과를 리스트로 반환을 받겠다.
    }

}