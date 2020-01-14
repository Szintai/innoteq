package com.Innoteq.innoteq.repository;

import com.Innoteq.innoteq.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {


    List<Employee> findAll();



}
