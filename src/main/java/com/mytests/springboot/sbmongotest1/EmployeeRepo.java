package com.mytests.springboot.sbmongotest1;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/22/2019.
 * Project: sb-mongo-test1
 * *******************************
 */

public interface EmployeeRepo extends MongoRepository<Employee, Long> {

    String Q1 = "{\"project\": ?1, \"hireDate\": {$lt: ?0}}";

    @Query(value = "{'project':?0}", fields = "{'name': 1,'project':1,'sickDays':1,'availableVacationDays':1}", sort = "{'name': -1}", collation = "{ 'locale' :  'en_US' }")
    List<Employee> findByProjectFiltered(String name);

    @Query(value = Q1)
    List<Employee> getProjectVeterans(Date date, String project);

    @Query("{$or : [{$and : [{'sickDays': {$gt: 14}}, {'availableVacationDays' : {$lt: 20}}]}, {'sickDays': {$gt: 30}}, {'hireDate': {$lt: ?0}}]}")
    List<Employee> gotoDoctor(Date date);

    @Query("{'project' : :#{#project}, 'availableVacationDays' : :#{#vacations}}")
    List<Employee> byProjectAndVacationDays(@Param("project") String pr, @Param("vacations") int days);

    @Query("{'project' : {$in: [?0, ?1]}, 'name': {$nin: ['vasya','petya']}}")
    List<Employee> byProjects(String p1, String p2);

    @Query("{$nor: [{\"sickDays\": { $not: { $gte: 7 } }}, {\"hireDate\" : {$not: { $type : \"date\" } }} ]} ")
    List<Employee> testOperators();

    @Query("{ $expr: { $gt: [ '$sickDays' , '$availableVacationDays' ] } }")
        // doesn't work for now
    List<Employee> hrCheck();

    List<Employee> getEmployeesByHireDateBeforeAndSickDaysGreaterThan(Date hireDate, int sickDays);

    List<Employee> sickDaysGreaterThanAndNameLike(int d, String n);

}
