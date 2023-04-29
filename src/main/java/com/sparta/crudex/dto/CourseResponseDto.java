package com.sparta.crudex.dto;

import com.sparta.crudex.entity.Course;

public class CourseResponseDto {
    private Long id;
    private String title;
    private String instructor;
    private double cost;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getInstructor() {
        return instructor;
    }

    public double getCost() {
        return cost;
    }

    public CourseResponseDto(){}

    public CourseResponseDto(Course course) {
        this.id = course.getId();
        this.title = course.getTitle();
        this.instructor = course.getInstructor();
        this.cost = course.getCost();
    }
}
