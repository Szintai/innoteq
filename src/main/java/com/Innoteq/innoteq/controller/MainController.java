package com.Innoteq.innoteq.controller;

import com.Innoteq.innoteq.model.Employee;
import com.Innoteq.innoteq.model.Item;
import com.Innoteq.innoteq.model.Product;
import com.Innoteq.innoteq.model.Purchase;
import com.Innoteq.innoteq.service.EmployeeService;
import com.Innoteq.innoteq.service.ItemService;
import com.Innoteq.innoteq.service.ProductService;
import com.Innoteq.innoteq.service.PurchaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private final EmployeeService employeeService;
    private final ProductService productService;
    private final ItemService itemService;
    private final PurchaseService purchaseService;

    private Employee selectedEmployee=null;
    private Employee reportEmployee=new Employee();
    private Product selectedProduct=null;
    private Purchase purchase=new Purchase();


    public MainController(EmployeeService employeeService, ProductService productService, ItemService itemService, PurchaseService purchaseService) {
        this.employeeService = employeeService;
        this.productService = productService;
        this.itemService = itemService;
        this.purchaseService = purchaseService;
    }

    @GetMapping("/")
    public String index(Model model)
    {
        if(purchase.getEmployee() != null){
            List<Employee> employees=new ArrayList<>();
            employees.add(selectedEmployee);
            model.addAttribute("employees",employees);

            model.addAttribute("products", productService.findAll());
        }
        else {
            model.addAttribute("employees", employeeService.findAll());

            model.addAttribute("products", productService.findAll());
        }
            model.addAttribute("purchase", purchase);

        return "home/main";
    }

    @GetMapping("/employeeReports")
    public String employeeReports(Model model)
    {
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("reportEmployee", reportEmployee);
        System.out.println(reportEmployee.getName()+"");

        return "home/employeeReports";
    }

    @GetMapping("/productReports")
    public String productReports()
    {

        return "home/productReports";
    }


    @PostMapping(value ="/purchase")
    public String purchase(){

        Employee employee=employeeService.findById(purchase.getEmployee().getId());
        employee.getPurchases().add(purchase);
        employeeService.save(employee);
        purchase=new Purchase();

        return "redirect:/";
    }


    @PostMapping(value ="/addProduct")
    public String addProduct(@RequestParam Map<String, String> reqParam,
                              @RequestParam("quantity") int quantity ){

        String employeeId= reqParam.get("employeeId");

        String productId= reqParam.get("productId");
        selectedEmployee=employeeService.findById(Long.parseLong(employeeId));

          purchase.setEmployee(selectedEmployee);
          Item item=new Item(quantity, productService.findById(Long.parseLong(productId)));
          purchase.getItems().add(item);


        return "redirect:/";
    }

    @PostMapping(value ="/employeeReports")
    public String addProduct(@RequestParam Map<String, String> reqParam){

        String employeeId= reqParam.get("employeeId");
        reportEmployee=employeeService.findById(Long.parseLong(employeeId));

        return"redirect:/employeeReports";
    }

}
