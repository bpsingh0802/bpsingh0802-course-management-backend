package com.example.finaltest.repository;

import com.example.finaltest.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CourseRepository extends MongoRepository<Course, String> {
    Optional<Course> findByCourseId(String courseId);
    boolean existsByCourseId(String courseId);
    boolean existsByPrerequisitesContains(String courseId);
}
