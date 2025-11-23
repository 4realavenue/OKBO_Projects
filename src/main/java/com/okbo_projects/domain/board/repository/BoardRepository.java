package com.okbo_projects.domain.board.repository;

import com.okbo_projects.common.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
