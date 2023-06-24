package com.hemasai.spring.Service;

import com.hemasai.spring.Constants.Constants;
import com.hemasai.spring.Database.MongoHelper;
import com.hemasai.spring.Model.Response;
import com.hemasai.spring.Model.Student;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private MongoHelper mongoHelper;
    public Response addStudent(Student student) {
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
        return response;
    }
}
