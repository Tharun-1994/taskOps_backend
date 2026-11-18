package com.taskops.backend.tasks;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	private final TaskRepository repository;

	public TaskController(TaskRepository repository) {
		this.repository = repository;
	}

	record CreateTaskRequest(String title) {
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Task create(@RequestBody CreateTaskRequest request) {
		if (request.title() == null || request.title().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title is required");
		}
		return repository.save(new Task(null, request.title().trim(), false));
	}

	@GetMapping
	public List<Task> list() {
		return repository.findAll();
	}

	@PatchMapping("/{id}/complete")
	public Task complete(@PathVariable String id) {
		Task task = repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

		return repository.save(new Task(task.id(), task.title(), true));
	}
}