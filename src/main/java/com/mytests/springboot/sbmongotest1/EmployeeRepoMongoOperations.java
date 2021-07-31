package com.mytests.springboot.sbmongotest1;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/22/2019.
 * Project: sb-mongo-test1
 * *******************************
 */
@Repository
public class EmployeeRepoMongoOperations implements NewEmployeeRepo {
    
    @Autowired
    MongoOperations operations;
    
    @Override
    public List<Employee> findAllByProjectAndHireDateBetween(String project, Date from, Date to) {
        Criteria criteria = Criteria.where("hireDate").gte(from).andOperator(Criteria.where("hireDate").lt(to)).and("project").is(project);
        Query query = query(criteria);
        return operations.find(query, Employee.class);
    }

    @Override
    public Employee findByName(String name) {
        Query query = query(where("name").is(name));
        return operations.findOne(query, Employee.class);
    }

    @Override
    public Employee save(Employee employee) {
        operations.save(employee);
        return employee;
    }
}
