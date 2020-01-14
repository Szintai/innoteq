package com.Innoteq.innoteq.config;

import com.Innoteq.innoteq.model.Employee;
import com.Innoteq.innoteq.model.Product;
import com.Innoteq.innoteq.service.EmployeeService;
import com.Innoteq.innoteq.service.ProductService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class Initial implements ApplicationListener<ContextRefreshedEvent> {

    private final EmployeeService employeeService;
    private final ProductService productService;

    public Initial(EmployeeService employeeService, ProductService productService) {
        this.employeeService = employeeService;
        this.productService = productService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(CollectionUtils.isEmpty(employeeService.findAll())) {
            init();
        }
    }


    private void init() {

        Employee employee;
        Product product;

        for (int i=1; i<11 ; i++){

            employee=new Employee("Employee"+i);
            product=new Product("Product"+i, i*100);

            employeeService.save(employee);
            productService.save(product);
        }



    }
}
