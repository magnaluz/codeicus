package com.websystique.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websystique.springboot.model.AuditTask;

@Repository
public interface AuditTaskRepository extends JpaRepository<AuditTask, Long> {

}
