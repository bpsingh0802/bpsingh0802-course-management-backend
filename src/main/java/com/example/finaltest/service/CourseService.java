package com.example.finaltest.service;

import com.example.finaltest.model.Course;
import com.example.finaltest.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        List<String> prerequisites = course.getPrerequisites();
        if (prerequisites != null) {
            for (String prereqId : prerequisites) {
                if (!courseRepository.existsByCourseId(prereqId)) {
                    throw new IllegalArgumentException("Prerequisite course not found: " + prereqId);
                }
            }
        }

        if (courseRepository.existsByCourseId(course.getCourseId())) {
            throw new IllegalArgumentException("Course with ID already exists: " + course.getCourseId());
        }

        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseByCourseId(String courseId) {
        return courseRepository.findByCourseId(courseId)
                .orElseThrow(() -> new NoSuchElementException("Course not found: " + courseId));
    }

    public void deleteCourse(String courseId) {
        if (courseRepository.existsByPrerequisitesContains(courseId)) {
            throw new IllegalStateException("Cannot delete course. It is a prerequisite for other courses.");
        }

        Course course = courseRepository.findByCourseId(courseId)
                .orElseThrow(() -> new NoSuchElementException("Course not found: " + courseId));

        courseRepository.delete(course);
    }
}
