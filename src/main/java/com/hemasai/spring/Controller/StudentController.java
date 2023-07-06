package com.hemasai.spring.Controller;

import com.hemasai.spring.Constants.Constants;
import com.hemasai.spring.Model.Response;
import com.hemasai.spring.Model.Student;
import com.hemasai.spring.Service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController()
public class StudentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public Response addStudent(
            HttpServletRequest httpServletRequest,
            @RequestBody Student student
            ) {
        LOGGER.info("Entered into addStudent with student: {}", student.toString());
        Response response = studentService.addStudent(student);
        if (Constants.SUCCESS.equalsIgnoreCase(response.getStatus())) {
            LOGGER.info("Student added successfully");
            return response;
        }
        return response;
    }

    @GetMapping("/student/{studentId}")
    public Response getStudent(
            HttpServletRequest httpServletRequest,
            @PathVariable String studentId
    ) {
        LOGGER.info("Entered into getStudent with studentId: {}", studentId);
        Response response = studentService.getStudent(studentId);
        if (Constants.SUCCESS.equalsIgnoreCase(response.getStatus())) {
            LOGGER.info("Student added successfully");
            return response;
        }
        return response;
    }

    @PatchMapping("/student/{studentId}")
    public Response updateStudent(
            HttpServletRequest httpServletRequest,
            @PathVariable String studentId,
            @RequestBody Student student
    ){
        LOGGER.info("Entered into updateStudent with studentId: {}", studentId);
        Response response = studentService.updateStudent(studentId, student);
        if (Constants.SUCCESS.equalsIgnoreCase(response.getStatus())) {
            LOGGER.info("Student added successfully");
            return response;
        }
        return response;
    }
}
