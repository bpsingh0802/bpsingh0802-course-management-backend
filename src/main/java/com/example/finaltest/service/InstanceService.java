package com.example.finaltest.service;

import com.example.finaltest.model.Course;
import com.example.finaltest.model.CourseInstance;
import com.example.finaltest.repository.CourseRepository;
import com.example.finaltest.repository.InstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InstanceService {

    @Autowired
    private InstanceRepository instanceRepository;

    @Autowired
    private CourseRepository courseRepository;

    public CourseInstance createInstance(CourseInstance instance) {
        // Ensure the course exists
        Course course = courseRepository.findByCourseId(instance.getCourseId())
                .orElseThrow(() -> new NoSuchElementException("Course not found: " + instance.getCourseId()));

        return instanceRepository.save(instance);
    }

    public List<CourseInstance> getInstancesByYearAndSemester(int year, int semester) {
        return instanceRepository.findByYearAndSemester(year, semester);
    }

    public CourseInstance getInstanceDetail(int year, int semester, String courseId) {
        return instanceRepository.findByYearAndSemesterAndCourseId(year, semester, courseId)
                .orElseThrow(() -> new NoSuchElementException("Course instance not found"));
    }

    public void deleteInstance(int year, int semester, String courseId) {
        CourseInstance instance = getInstanceDetail(year, semester, courseId);
        instanceRepository.delete(instance);
    }
}
