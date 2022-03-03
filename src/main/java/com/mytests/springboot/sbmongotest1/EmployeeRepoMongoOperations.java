package com.mytests.springboot.sbmongotest1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

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
    public List<Employee> findAllByTeamAndHireDateBetween(String project, Date from, Date to) {
        Criteria criteria = Criteria.where("hireDate").gte(from).andOperator(Criteria.where("hireDate").lt(to)).and("team").is(project);
        Query query = query(criteria).with(Sort.by(Sort.Direction.DESC, "name"));
        query.fields().exclude("hireDate");
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
