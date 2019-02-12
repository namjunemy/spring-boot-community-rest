package io.namjune.community.rest.repository;

import io.namjune.community.rest.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
