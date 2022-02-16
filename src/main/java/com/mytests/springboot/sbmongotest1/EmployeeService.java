package com.mytests.springboot.sbmongotest1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/22/2019.
 * Project: sb-mongo-test1
 * *******************************
 */
@Service
public class EmployeeService {

    @Autowired private EmployeeRepo employeeRepo;
    @Autowired EmployeeRepoMongoOperations newEmployeeRepo;
    @Autowired EmployeeRepositoryMongoTemplate employeeRepositoryMongoTemplate;


    public void displayAll(){
        System.out.println("**********************************");
        System.out.println("-- all existing employee:");
        for (Employee employee : employeeRepo.findAll()) {
            System.out.println(employee.toString());
        }
        System.out.println("**********************************");
        System.out.println("-- @Query (all by project filtered)");
        for (Employee employee : employeeRepo.findByProjectFiltered("platform qa")) {
            System.out.println(employee.toString());
        }
        System.out.println("**********************************");
        System.out.println("-- @Query (by project and date before specified)");
        for (Employee employee : employeeRepo.getProjectVeterans(new Date(104, Calendar.DECEMBER, 1), "idea")) {
            System.out.println(employee.toString());
        }
        System.out.println("**********************************");
        System.out.println("-- @Query (complex)");
        for (Employee employee : employeeRepo.gotoDoctor(new Date(104, Calendar.DECEMBER, 1))) {
            System.out.println(employee.toString());
        }
        System.out.println("**********************************");
        System.out.println("-- @Query with SpEL");
        for (Employee employee : employeeRepo.byProjectAndVacationDays("idea", 24)) {
            System.out.println(employee.toString());
        }
        System.out.println("**********************************");
        System.out.println("-- @Query $in operator");
        for (Employee employee : employeeRepo.byProjects("idea", "webstorm")) {
            System.out.println(employee.toString());
        }
        System.out.println("**********************************");
        System.out.println("-- just column name");
        for (Employee employee : employeeRepo.sickDaysGreaterThanAndNameLike(7, "kat")) {
            System.out.println(employee.toString());
        }
        System.out.println("**********************************");
        System.out.println("-- MongoOperations.findOne(criteria) (one by name):");

        System.out.println(newEmployeeRepo.findByName("maxim m").toString());

        System.out.println("**********************************");
        System.out.println("-- MongoOperations.find(criteria) (all by project and hire date period):");
        Date from_date = new Date(100, Calendar.JANUARY, 1);
        Date to_date = new Date(105, Calendar.DECEMBER, 31);
        for (Employee employee : newEmployeeRepo.findAllByProjectAndHireDateBetween("idea", from_date, to_date)) {
            System.out.println(employee.toString());
        }
        System.out.println("**********************************");
        System.out.println("-- mongoTemplate.basicQuery  (all by project):");
        for (Employee employee : employeeRepositoryMongoTemplate.basicQueryTest()) {
            System.out.println(employee.toString());
        }
        System.out.println("**********************************");
        System.out.println("-- mongoTemplate.executeCommand(json) (all by project):");
            System.out.println(employeeRepositoryMongoTemplate.templateExecCommandTest().toJson());
        System.out.println("**********************************");
        System.out.println("-- mongoTemplate.find  (by project and hire_date < today):");
        for (Employee employee : employeeRepositoryMongoTemplate.templateFindTest(new Date(),"idea")) {
            System.out.println(employee.toString());
        }
    }

    public void setUpDB() {
        employeeRepo.deleteAll();
        addPerson("irina p", "platform qa", new Date(106, Calendar.SEPTEMBER, 1), 7, 10);
        addPerson("katya a", "platform qa", new Date(119, Calendar.JULY, 22), 30, 10);
        addPerson("nastya s", "platform qa", new Date(119, Calendar.APRIL, 15), 5, 5);
        addPerson("maxim m", "idea", new Date(103, Calendar.MARCH, 1), 1, 24);
        addPerson("anna k", "idea", new Date(104, Calendar.MARCH, 1), 5, 24);
        addPerson("sergey v", "idea", new Date(104, Calendar.NOVEMBER, 1), 0, 14);
        addPerson("sasha b", "idea", new Date(120, Calendar.SEPTEMBER, 1), 5, 0);
        addPerson("lena p", "webstorm", new Date(110, Calendar.SEPTEMBER, 1), 5, 0);
        addPerson("andrey s", "webstorm", new Date(111, Calendar.SEPTEMBER, 1), 5, 0);

    }

    void addPerson(String name, String project, Date date, int sickDays, int vacations) {
        newEmployeeRepo.save(new Employee(name, project, date, sickDays, vacations));
    }
}
