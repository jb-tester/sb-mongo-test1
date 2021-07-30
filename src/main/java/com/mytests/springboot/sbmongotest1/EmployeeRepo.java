package com.mytests.springboot.sbmongotest1;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/22/2019.
 * Project: sb-mongo-test1
 * *******************************
 */

public interface EmployeeRepo extends MongoRepository<Employee, Long> {

    @Query(value = "{'project':?0}", fields = "{'name': 1,'project':1}")
    List<Employee> findByProjectFiltered(String name);
    
    List<Employee> getEmployeesByHireDateBefore(Date hireDate);
}
