package com.sparta.crudex.controller;

import com.sparta.crudex.dto.CourseRequestDto;
import com.sparta.crudex.dto.CourseResponseDto;
import com.sparta.crudex.entity.Course;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/course")
public class CourseController {

    private static final Map<Long, Course> table = new HashMap<>();
    private static long ID; // Map 에 들어갈 ID

    // 강의 생성
    @PostMapping("/create")
    public String createCourse(@RequestBody CourseRequestDto courseRequestDto) {
        // 브라우저에서 받은 데이터를 저장하기 위해서 Course 객체로 변환
        // 매개변수가 있는 Course 생성자
        Course course = new Course(courseRequestDto);

        // ID 중복을 막기 위해 현재 table 의 최대 ID + 1
        // (@Id 사용 하지 않을 경우 중복 되지 않아야하기 때문에 ++ID 로 잡았다)
        course.setId(++ID);

        // table 에 생성한 Course 인스턴스를 저장
        table.put(ID, course);

        return "강의 저장에 성공하였습니다";
    }

    // 강의 전체 조회
    // table 에 저장 되어 있는 모든 강의 목록 조회
    @GetMapping("/list")
    public List<CourseResponseDto> getCourseList(){
        // table.valuse().stream 테이블에 있는 모든 정보를 가져와서 하나 씩 확인
        // map(CourseResponseDto::new) : map 은 courseResponstDto 로 가공하는데 하나씩 new 인스턴스 생성
        // .collect ~ : 결과 값을 list 타입으로 묶어 준다.
        return table.values().stream().map(CourseResponseDto::new).collect(Collectors.toList());
    }

    // http://localhost:8080/course/{id}
    // 강의 하나
    @GetMapping("/{id}")
    public CourseResponseDto getCourse(@PathVariable Long id) {
        // 조회하기 위해 받아온 course 의 id 를 사용하여 해당  course 인스턴스 객체가 table 에 존재하는지 확인 후 가져오기
        Course course = table.get(id);

        if (course != null) {
            return new CourseResponseDto(course);
        } else {
            return new CourseResponseDto();  // 기본 생성자
        }
    }

    // 강의 수정
    @PutMapping("/update/{id}")
    public CourseResponseDto updateCourse(@PathVariable Long id, @RequestBody CourseResponseDto courseResponseDto) {
        // 수정하기 위해 받아온 course 의 id 를 사용하여 해당 course 인스턴스 객체가 존재하는지 확인 후 가져오기
        Course course = table.get(id);

        if (course != null) {
            course.update(courseResponseDto);
            return new CourseResponseDto(course);
        } else {
            return new CourseResponseDto();
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        // 삭제 하기 위해 받아온 course 의 id 를 사용하여 해당 course 인스턴스 객체가 존재하는지 확인
        Course course = table.get(id);

        if (course != null) {
            table.remove(id);
            return "강의 삭제에 성공하였습니다.";
        } else {
            return "해당 강의가 없습니다.";
        }
    }
}
