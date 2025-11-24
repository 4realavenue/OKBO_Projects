package com.okbo_projects.domain.board.repository;

import com.okbo_projects.common.entity.Board;
import com.okbo_projects.common.utils.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {






































    Page<Board> findByTeam(Team team, Pageable pageable);
}
