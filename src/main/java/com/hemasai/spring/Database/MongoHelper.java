package com.hemasai.spring.Database;

import com.hemasai.spring.Model.Student;
import org.bson.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoHelper extends MongoRepository<Student, String> {

    Document insert(Document document);
}
