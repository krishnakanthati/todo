package com.example.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Task;
import com.example.repository.TaskRepository;
import com.example.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	private TaskRepository taskRepository;

	public TaskServiceImpl(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}

	@Override
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Task saveTask(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Task getTaskById(Long Id) {
		return taskRepository.findById(Id).get();
	}

	@Override
	public Task updateTask(Task task) {
		return taskRepository.save(task);
	}
	
	@Override
	public void deleteTaskById(Long Id) {
		taskRepository.deleteById(Id);
	}

}
