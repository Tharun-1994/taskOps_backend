package com.taskops.backend.tasks;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository
        extends MongoRepository<Task, String> {
}
