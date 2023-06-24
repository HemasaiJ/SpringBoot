package com.hemasai.spring.Model;

import com.hemasai.spring.Constants.Constants;
import org.bson.Document;
import org.springframework.data.annotation.Id;

public class Student {

    private String name;
    @Id
    private String id;
    private String emailId;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student() {
    }

    public Student(String name, String id, String emailId, int age) {
        this.name = name;
        this.id = id;
        this.emailId = emailId;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", emailId='" + emailId + '\'' +
                ", age=" + age +
                '}';
    }

    public Document toDoc() {
        Document document = new Document();
        document.append(Constants.NAME, this.name);
        document.append(Constants.AGE, this.age);
        document.append(Constants.ID, this.id);
        document.append(Constants.EMAIL_ID, this.emailId);
        return document;
    }
}
