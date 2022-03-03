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

    // no completion here - https://youtrack.jetbrains.com/issue/IDEA-289964
    String Q1 = "{team: ?1, hireDate: {$lt: ?0}}";

    @Query(value = "{'team':?0}", fields = "{'name': 1,'team':1,'sickDays':1,'availableVacationDays':1}", sort = "{'name': -1}", collation = "{ 'locale' :  'en_US' }")
    List<Employee> findByProjectFiltered(String name);

    @Query(value = Q1)
    List<Employee> getProjectVeterans(Date date, String project);

    @Query("{$or : [{$and : [{'Sick_Days': {$gt: 14}}, {'Vacations' : {$lt: 20}}]}, {'Sick_Days': {$gt: 30}}, {'Hire_Date': {$lt: ?0}}]}")
    List<Employee> gotoDoctor(Date date);

    // error is shown for SpEL (named parameters): https://youtrack.jetbrains.com/issue/IDEA-288941
    @Query("{'team' : :#{#project}, 'availableVacationDays' : :#{#vacations}}")
    List<Employee> byProjectAndVacationDays(@Param("project") String pr, @Param("vacations") int days);

    @Query("{'team' : {$in: [?0, ?1]}, 'name': {$nin: ['vasya','petya']}}")
    List<Employee> byProjects(String p1, String p2);

    // no completion in case of used double quotes - https://youtrack.jetbrains.com/issue/IDEA-289963
    @Query("{$nor: [{\"sickDays\": { $not: { $gte: 7 } }}, {\"hireDate\" : {$not: { $type : \"date\" } }} ]} ")
    List<Employee> testOperators();

    @Query("{ $expr: { $gt: [ '$sickDays' , '$availableVacationDays' ] } }")
        // doesn't work for now
    List<Employee> hrCheck();

    // compare results for fields and columns - https://youtrack.jetbrains.com/issue/IDEA-289909
    @Query("{'Vacations': ?0, 'Project': ?1}")
    List<Employee> test1(int p1, String p2);

    @Query("{'availableVacationDays': ?0, 'team': ?1}")
    List<Employee> test2(int p1, String p2);

    // for completion testing - https://youtrack.jetbrains.com/issue/IDEA-289915
    @Query("{Project: ?0,Full_Name: ?1,Vacations: ?2}")
    List<Employee> test3(String team, String name, int vacations);


    List<Employee> getEmployeesByHireDateBeforeAndSickDaysGreaterThan(Date hireDate, int sickDays);

    List<Employee> sickDaysGreaterThanAndNameLike(int d, String n);

}
