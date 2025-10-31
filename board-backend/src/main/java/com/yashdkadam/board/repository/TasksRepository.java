package com.yashdkadam.board.repository;

import com.yashdkadam.board.entity.Task;
import com.yashdkadam.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Task, Long> {
}
