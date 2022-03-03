package com.mytests.springboot.sbmongotest1;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
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
public class EmployeeRepositoryMongoTemplate {


    @Autowired
    private MongoTemplate mongoTemplate;
    
    public List<Employee> templateFindTest(Date date, String project){
        
        return mongoTemplate.find(new Query(Criteria.where("hireDate").lt(date).and("project").is(project)), Employee.class);
    }
    public Document templateExecCommandTest(){

        return mongoTemplate.executeCommand(
                "{\n" +
                        "  find : \"employees\",\n" +
                        "  filter: " +
                        "{ Project :\"mega\"}" +
                        " \n" +
                        "}");
    }

    public List<Employee> basicQueryTest() {
        // https://youtrack.jetbrains.com/issue/IDEA-274992
        BasicQuery query = new BasicQuery("{Project:'services',Vacations: {$lte: 10}}");
        return mongoTemplate.find(query, Employee.class);
    }

    public List<Employee> basicQueryTestFiltered() {
        // https://youtrack.jetbrains.com/issue/IDEA-289928
        BasicQuery query = new BasicQuery("{Project:'services',Vacations: {$lte: 10}}", "{'name': 1,'sickDays':1,'availableVacationDays':1}");
        return mongoTemplate.find(query, Employee.class);
    }
}
