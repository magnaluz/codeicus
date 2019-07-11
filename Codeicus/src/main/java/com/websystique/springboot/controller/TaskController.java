package com.websystique.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.websystique.springboot.model.Task;
import com.websystique.springboot.service.TaskService;
import com.websystique.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/task")
public class TaskController {

    public static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    public TaskService taskService;

    // -------------------Create a
    // Task-------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createTask(@RequestBody Task task, UriComponentsBuilder ucBuilder) {
	logger.info("Creating Task : {}", task);
	if (taskService.isTaskExist(task)) {
	    logger.error("Unable to task. A Task with name {} already exist", task.getName());
	    return new ResponseEntity(new CustomErrorType("Unable to create. A Task with name " + task.getName() + " already exist."), HttpStatus.CONFLICT);
	}
	taskService.saveTask(task);

	HttpHeaders headers = new HttpHeaders();
	headers.setLocation(ucBuilder.path("/api/task/{id}").buildAndExpand(task.getId()).toUri());
	return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // -------------------Retrieve All
    // Task---------------------------------------------

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> listAllTasks() {
	List<Task> tasks = taskService.findAllTasks();
	if (tasks.isEmpty()) {
	    return new ResponseEntity(HttpStatus.NO_CONTENT);
	    // You many decide to return HttpStatus.NOT_FOUND
	}
	return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }
    // -------------------Retrieve Single
    // Task------------------------------------------

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTask(@PathVariable("id") long id) {
	logger.info("Fetching Task with id {}", id);
	Task task = taskService.findById(id);
	if (task == null) {
	    logger.error("Task with id {} not found.", id);
	    return new ResponseEntity(new CustomErrorType("Task with id " + id + " not found"), HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    // ------------------- Update a User
    // ------------------------------------------------

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody Task task) {
	logger.info("Updating User with id {}", id);

	Task currentTask = taskService.findById(id);

	if (currentTask == null) {
	    logger.error("Unable to update. Task with id {} not found.", id);
	    return new ResponseEntity(new CustomErrorType("Unable to upate. Task with id " + id + " not found."), HttpStatus.NOT_FOUND);
	}

	currentTask.setName(task.getName());
	currentTask.setPriority(task.getPriority());
	currentTask.setEstimate(task.getEstimate());

	taskService.updateUser(currentTask);
	return new ResponseEntity<Task>(currentTask, HttpStatus.OK);
    }

    // ------------------- Delete a User-----------------------------------------

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTask(@PathVariable("id") long id) {
	logger.info("Fetching & Deleting Task with id {}", id);

	Task task = taskService.findById(id);
	if (task == null) {
	    logger.error("Unable to delete. Task with id {} not found.", id);
	    return new ResponseEntity(new CustomErrorType("Unable to delete. Task with id " + id + " not found."), HttpStatus.NOT_FOUND);
	}
	taskService.deleteTaskById(id);
	return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
    }


}
