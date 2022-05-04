package com.example.service;

import java.util.List;

import com.example.entity.Task;

public interface TaskService {
	List<Task> getAllTasks();

	Task saveTask(Task task);

	Task getTaskById(Long Id);

	Task updateTask(Task task);
	
	void deleteTaskById(Long Id);
}
