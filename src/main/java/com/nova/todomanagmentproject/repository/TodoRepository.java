package com.nova.todomanagmentproject.repository;

import com.nova.todomanagmentproject.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
