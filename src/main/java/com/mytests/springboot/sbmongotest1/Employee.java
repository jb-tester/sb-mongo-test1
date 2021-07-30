package com.mytests.springboot.sbmongotest1;

import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigInteger;
import java.util.Date;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/21/2019.
 * Project: sb-mongo-test1
 * *******************************
 */
@Document(collection = "employees")
public class Employee {
    @Id
    BigInteger id;
    @Field("Full_Name")
    String name;
    @Field("Project")
    String project;
    @Field("Hire_Date")
    Date hireDate;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }



    public Employee(String name, String project, Date hireDate) {

        this.name = name;
        this.project = project;
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", project='" + project + '\'' +
                ", hireDate=" + hireDate +
                ' ';
    }
}
