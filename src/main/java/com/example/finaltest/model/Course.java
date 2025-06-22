package com.example.finaltest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "courses")
public class Course {
    @Id
    private String id;
    private String title;
    private String courseId;
    private String description;
    private List<String> prerequisites;

    public Course() {}

    public Course(String id, String title, String courseId, String description, List<String> prerequisites) {
        this.id = id;
        this.title = title;
        this.courseId = courseId;
        this.description = description;
        this.prerequisites = prerequisites;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getPrerequisites() { return prerequisites; }
    public void setPrerequisites(List<String> prerequisites) { this.prerequisites = prerequisites; }
}
