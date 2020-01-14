package com.Innoteq.innoteq.controller;

import com.Innoteq.innoteq.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }






}
