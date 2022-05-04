package com.example.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Task;
import com.example.service.TaskService;

@Controller
public class TaskController {
	private TaskService taskService;

	public TaskController(TaskService taskService) {
		super();
		this.taskService = taskService;
	}
	
	// handler method to handle list tasks requests and return model and view
	@GetMapping("/tasks")
	public String listTasks(Model model) {
		model.addAttribute("tasks", taskService.getAllTasks());
		return "tasks";
	}
	
	@GetMapping("/tasks/new")
	public String createTask(Model model) {
		Task task = new Task();
		model.addAttribute("task", task);
		return "create_task";
	}
	
	@PostMapping("/tasks")
	public String saveTask(@ModelAttribute("task") Task task) {
		taskService.saveTask(task);
		return "redirect:/tasks";
	}
	
	@GetMapping("/tasks/edit/{id}")
	public String editTask(@PathVariable Long id, Model model) {
		model.addAttribute("task", taskService.getTaskById(id));
		return "edit_task";
	}
	
	@PostMapping("/tasks/{id}")
	public String updateTask(@PathVariable Long id, @ModelAttribute("task") Task task,
			Model model) {
		Date date = new Date();
		// get task from database by id
		Task addedTask = taskService.getTaskById(id);
		addedTask.setId(id);
		addedTask.setName(task.getName());
		addedTask.setDate(date);
		
		// save updated task object
		taskService.updateTask(addedTask);
		return "redirect:/tasks";	
	}
	
	// handler method to handle delete task requests
	
	@GetMapping("/tasks/{id}")
	public String deleteTask(@PathVariable Long id) {
		taskService.deleteTaskById(id);
		return "redirect:/tasks";
	}
}
