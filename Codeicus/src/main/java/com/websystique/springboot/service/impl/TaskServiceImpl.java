package com.websystique.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springboot.model.Task;
import com.websystique.springboot.repositories.AuditTaskRepository;
import com.websystique.springboot.repositories.TaskRepository;
import com.websystique.springboot.service.TaskService;
import com.websystique.springboot.util.AuditTaskHelper;
import com.websystique.springboot.util.TaskOperationEnum;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

    public static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AuditTaskRepository auditTaskRepository;

    @Override
    public boolean isTaskExist(Task task) {
	return taskRepository.findByName(task.getName()).isPresent();
    }

    @Override

    public void saveTask(Task task) {
	try {
	    logger.info("Guardando Task, {}", task);
	    taskRepository.save(task);
	    auditTaskRepository.save(AuditTaskHelper.createSuccessAudit(TaskOperationEnum.CREATE, task.toString()));
	    logger.info("Guardado de la Task exitoso, {}", task);
	} catch (Exception e) {
	    logger.error("Error al guardar Task, {}", task, e);
	    auditTaskRepository.save(AuditTaskHelper.createErrorAudit(TaskOperationEnum.CREATE, task.toString(), e.getMessage()));
	}

    }

    @Override
    public List<Task> findAllTasks() {
	List<Task> result = new ArrayList<>();
	try {
	    logger.info("Leyendo todas las Tasks");
	    result = taskRepository.findAll();
	    auditTaskRepository.save(AuditTaskHelper.createSuccessAudit(TaskOperationEnum.READ_ALL, ""));
	    logger.info("lectura de todas las Tasks exitoso");
	} catch (Exception e) {
	    logger.error("Error al leer todas las Tasks", e);
	    auditTaskRepository.save(AuditTaskHelper.createErrorAudit(TaskOperationEnum.READ_ALL, "", e.getMessage()));
	}

	return result;
    }

    @Override
    public Task findById(long id) {
	Task task = null;
	try {
	    logger.info("Leyendo la Task, {}", id);
	    task = taskRepository.findById(id);
	    auditTaskRepository.save(AuditTaskHelper.createSuccessAudit(TaskOperationEnum.READ_ONE, String.valueOf(id)));
	    logger.info("Lectura de la Task exitoso, {}", id);
	} catch (Exception e) {
	    logger.error("Error al leer la Task {}", id, e);
	    auditTaskRepository.save(AuditTaskHelper.createErrorAudit(TaskOperationEnum.READ_ONE, "", e.getMessage()));
	}
	return task;
    }

    @Override
    public void updateUser(Task currentTask) {

	try {
	    logger.info("Actualizando la Task, {}", currentTask);
	    Task task = taskRepository.save(currentTask);
	    logger.info("Actualizacion de la Task exitosa, {}", currentTask);
	    auditTaskRepository.save(AuditTaskHelper.createSuccessAudit(TaskOperationEnum.UPDATE, task.toString()));
	} catch (Exception e) {
	    logger.error("Error al actualizar la Task {}", currentTask, e);
	    auditTaskRepository.save(AuditTaskHelper.createErrorAudit(TaskOperationEnum.UPDATE, currentTask.toString(), e.getMessage()));
	}
    }

    @Override
    public void deleteTaskById(long id) {
	try {
	    logger.info("Eliminando la Task, {}", id);
	    taskRepository.delete(id);
	    logger.info("Eliminacion de la Task exitosa, {}", id);
	    auditTaskRepository.save(AuditTaskHelper.createSuccessAudit(TaskOperationEnum.DELETE, String.valueOf(id)));
	} catch (Exception e) {
	    logger.error("Error al eliminar la Task {}", id, e);
	    auditTaskRepository.save(AuditTaskHelper.createErrorAudit(TaskOperationEnum.DELETE, String.valueOf(id), e.getMessage()));
	}
    }

}
