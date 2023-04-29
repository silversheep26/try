package com.sparta.crudex.entity;

import com.sparta.crudex.dto.CourseRequestDto;
import com.sparta.crudex.dto.CourseResponseDto;

public class Course {
    private Long id;
    private String title;
    private String instructor;
    private double cost;

    public void setId(Long id) {
        this.id = id;
    }

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

    public Course(CourseRequestDto courseRequestDto){
        this.title = courseRequestDto.getTitle();
        this.instructor = courseRequestDto.getInstructor();
        this.cost = courseRequestDto.getCost();
    }

    public void update(CourseResponseDto courseResponseDto) {
        this.title = courseResponseDto.getTitle();
        this.instructor = courseResponseDto.getInstructor();
        this.cost = courseResponseDto.getCost();
    }
}
