package com.websystique.springboot.service;

import java.util.List;

import com.websystique.springboot.model.Task;

public interface TaskService {

    boolean isTaskExist(Task task);

    void saveTask(Task task);

    List<Task> findAllTasks();

    Task findById(long id);

    void updateUser(Task currentTask);

    void deleteTaskById(long id);

}
