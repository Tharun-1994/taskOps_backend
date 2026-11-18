package com.taskops.backend.tasks;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
public record Task(
    @Id String id,
    String title,
    boolean completed
) {}