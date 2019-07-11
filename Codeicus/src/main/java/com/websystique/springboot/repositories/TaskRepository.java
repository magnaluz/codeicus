package com.websystique.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websystique.springboot.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	Optional<Task> findByName(String name);

	Task findById(Long id);
	
}
