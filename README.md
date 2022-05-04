# todo
ToDo Web Application - CRUD Operations using Spring Boot Web Framework

## Tools Used ##
*	Java
*	Spring Boot
*	Spring MVC
*	Spring Data JPA
*	MySQL
*	Thymeleaf
*	Eclipse STS

## Project Workflow ##
Workflow of the Application : ![picture alt](https://github.com/krishnakanthati/todo/blob/master/snap/workflow.PNG "Workflow")

## Purpose ##
To showcase the CRUD operations in RDBMS using Spring Boot STS. The implementation of DAO layers that provide CRUD functionality on JPA entities can be a repetitive, 
time-consuming task that we want to avoid in most cases. Luckily, Spring Boot makes it easy to create CRUD applications through a layer of standard JPA-based CRUD repositories.

## Creation of Project ##
*	Install Spring Tool Suite IDE
*	Create a new Spring project
*	Fill details in the pop-up window and press Next.
*	Choose Spring Boot version and select dependencies and press Next.
*	Dependencies included were:
    *	Spring Web
    * Spring Data JPA
    * Thymeleaf
    * MySQL Driver
    * Spring Boot Devtools
* Click on the ‘Finish’ button.

## Methods Used ##
### TaskServiceImpl.java ###
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

## Entity ##
### Task.java ###
package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false)
	private Date date;

	@PrePersist
	private void onCreate() {
		date = new Date();
	}

	public Task() {

	}

	public Task(String name, Date date) {
		super();
		this.name = name;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}

## TaskController ##
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

## Snapshot ##
1. Add Task : ![picture alt](https://github.com/krishnakanthati/todo/blob/master/snap/add.jpg "Add")

2. Description of Table : ![picture alt](https://github.com/krishnakanthati/todo/blob/master/snap/desc.jpg, "Desc")

3. Home : ![picture alt](https://github.com/krishnakanthati/todo/blob/master/snap/home.jpg, "Home")

4. Update : ![picture alt](https://github.com/krishnakanthati/todo/blob/master/snap/edit.jpg, "Update")

5. Delete : ![picture alt](https://github.com/krishnakanthati/todo/blob/master/snap/delete.jpg, "Delete")

6. Select :![picture alt](https://github.com/krishnakanthati/todo/blob/master/snap/select.jpg, "Select")
