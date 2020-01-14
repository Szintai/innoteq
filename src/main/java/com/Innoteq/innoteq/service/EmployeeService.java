package com.Innoteq.innoteq.service;

import com.Innoteq.innoteq.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee save(Employee employee);



}
