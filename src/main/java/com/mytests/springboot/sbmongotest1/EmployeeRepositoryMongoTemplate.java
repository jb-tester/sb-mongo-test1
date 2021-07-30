package com.mytests.springboot.sbmongotest1;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
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
    
    public List<Employee> templateFindTest(Date date){
        
        return mongoTemplate.find(new Query(Criteria.where("hireDate").gt(date)), Employee.class);
    }
    public Document templateExecCommandTest(){
        
        return mongoTemplate.executeCommand(
                "{\n" +
                        "  find : \"employees\",\n" +
                        "  filter: " +
                        "{ Project :\"idea\"}" +
                " \n" +
                "}");
    }
    
    public List<Employee> basicQueryTest(){

        BasicQuery query = new BasicQuery("{'project':'platform qa'}");
        return mongoTemplate.find(query, Employee.class);
    }
}
