package com.example.finaltest.controller;

import com.example.finaltest.model.CourseInstance;
import com.example.finaltest.service.InstanceService;
import com.example.finaltest.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instances")
@CrossOrigin(origins = "*")
public class InstanceController {

    @Autowired
    private InstanceService instanceService;

    @PostMapping
    public ResponseEntity<?> createInstance(@RequestBody CourseInstance instance) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(instanceService.createInstance(instance));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/{year}/{semester}")
    public ResponseEntity<List<CourseInstance>> getInstances(
            @PathVariable int year, @PathVariable int semester) {
        return ResponseEntity.ok(instanceService.getInstancesByYearAndSemester(year, semester));
    }

    @GetMapping("/{year}/{semester}/{courseId}")
    public ResponseEntity<?> getInstanceDetail(
            @PathVariable int year,
            @PathVariable int semester,
            @PathVariable String courseId) {
        try {
            return ResponseEntity.ok(instanceService.getInstanceDetail(year, semester, courseId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, e.getMessage()));
        }
    }

    @DeleteMapping("/{year}/{semester}/{courseId}")
    public ResponseEntity<?> deleteInstance(
            @PathVariable int year,
            @PathVariable int semester,
            @PathVariable String courseId) {
        try {
            instanceService.deleteInstance(year, semester, courseId);
            return ResponseEntity.ok(new ApiResponse(true, "Instance deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, e.getMessage()));
        }
    }
}
