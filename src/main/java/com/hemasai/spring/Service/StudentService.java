package com.hemasai.spring.Service;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hemasai.spring.Constants.Constants;
import com.hemasai.spring.Database.MongoHelper;
import com.hemasai.spring.Model.Response;
import com.hemasai.spring.Model.Student;

@Service
public class StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private MongoHelper mongoHelper;

    public Response addStudent(Student student) {
        LOGGER.info("Entered into addStudent");
        Response response = new Response().setStatus(Constants.FAILURE);

        try {
            Document result = mongoHelper.insert(student.toDoc());
            if (result.containsKey(Constants._ID)) {
                response.setStatus(Constants.SUCCESS);
                response.setMessage(Constants.STUDENT_ADDED_SUCCESSFULLY + " with _id"
                        + result.get(Constants._ID).toString());
            }
        } catch (Exception exception) {
            LOGGER.error("Exception occurred in addStudent and exception: ", exception);
        }
        LOGGER.info("Exit from addStudent");
        return response;
    }

    public Response getStudent(String studentId) {
        LOGGER.info("Entered into getStudent");
        Response response = new Response().setStatus(Constants.FAILURE);

        try {
            Optional<Student> result = mongoHelper.findById(studentId);
            result.ifPresent(response::setData);
            response.setStatus(Constants.SUCCESS);
        } catch (Exception exception) {
            LOGGER.error("Exception occurred in getStudent and exception: ", exception);
        }
        LOGGER.info("Exit from getStudent");
        return response;
    }

    public Response updateStudent(String studentId, Student student) {
        LOGGER.info("Entered into updateStudent");
        Response response = new Response().setStatus(Constants.FAILURE);

        try {
            Optional<Student> result = mongoHelper.findById(studentId);
            if (result.isPresent()) {
                student.setId(studentId);
                mongoHelper.save(student);
                response.setStatus(Constants.SUCCESS);
            } else {
                response.setStatus(Constants.FAILURE);
                response.setMessage("Student not found");
            }
        } catch (Exception exception) {
            LOGGER.error("Exception occurred in updateStudent and exception: ", exception);
        }
        LOGGER.info("Exit from updateStudent");
        return response;
    }

    public Response getAllStudents() {
        LOGGER.info("Entered into getAllStudents");
        Response response = new Response().setStatus(Constants.FAILURE);

        try {
             List<Student> students = mongoHelper.findAll();
             if (!CollectionUtils.isEmpty(students)) {
                 response.setData(students);
                 response.setStatus(Constants.SUCCESS);
            } else {
                response.setStatus(Constants.FAILURE);
                response.setMessage("Students not found");
            }
        } catch (Exception exception) {
            LOGGER.error("Exception occurred in getAllStudents and exception: ", exception);
        }
        LOGGER.info("Exit from getAllStudents");
        return response;
    }
}
