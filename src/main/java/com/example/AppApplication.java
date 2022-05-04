package com.example;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.entity.Task;
import com.example.repository.TaskRepository;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		Date date = new Date();
		SpringApplication.run(AppApplication.class, args);
		System.out.println("---------><-" + date.toString() + "-><----------");
		System.out.println("Hello World!");
	}

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public void run(String... args) throws Exception {
		Date date = new Date();
		Task task1 = new Task("Water Plants", date);
		taskRepository.save(task1);
		Task task2 = new Task("Learn Java", date);
		taskRepository.save(task2);
	}
}
