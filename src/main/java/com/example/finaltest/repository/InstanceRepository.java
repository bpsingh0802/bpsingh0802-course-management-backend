package com.example.finaltest.repository;

import com.example.finaltest.model.CourseInstance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface InstanceRepository extends MongoRepository<CourseInstance, String> {
    List<CourseInstance> findByYearAndSemester(int year, int semester);
    Optional<CourseInstance> findByYearAndSemesterAndCourseId(int year, int semester, String courseId);
    void deleteByYearAndSemesterAndCourseId(int year, int semester, String courseId);
}
