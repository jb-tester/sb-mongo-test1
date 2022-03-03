package com.mytests.springboot.sbmongotest1;

import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/22/2019.
 * Project: sb-mongo-test1
 * *******************************
 */
public interface NewEmployeeRepo extends Repository<Employee, Long> {
    
    List<Employee> findAllByTeamAndHireDateBetween(String project, Date from, Date to);

    Employee findByName(String name);
    Employee save(Employee employee);
}
