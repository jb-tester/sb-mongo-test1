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

    @Query(value = "{'project':?0}", fields = "{'name': 1,'project':1,'sickDays':1,'availableVacationDays':1}", sort = "{'name': -1}")
    List<Employee> findByProjectFiltered(String name);

    @Query(value = "{project: ?1, hireDate: {$lt: ?0}}")
    List<Employee> getProjectVeterans(Date date, String project);

    @Query("{$or : [{$and : [{'sickDays': {$gt: 14}}, {'availableVacationDays' : {$lt: 20}}]}, {'sickDays': {$gt: 30}}, {'hireDate': {$lt: ?0}}]}")
    List<Employee> gotoDoctor(Date date);

    List<Employee> getEmployeesByHireDateBeforeAndSickDaysGreaterThan(Date hireDate, int sickDays);

    List<Employee> sickDaysGreaterThanAndNameLike(int d, String n);

}
