package com.Innoteq.innoteq.controller;

import com.Innoteq.innoteq.model.Employee;
import com.Innoteq.innoteq.service.EmployeeService;
import com.Innoteq.innoteq.service.ItemService;
import com.Innoteq.innoteq.service.ProductService;
import com.Innoteq.innoteq.service.PurchaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final EmployeeService employeeService;
    private final ProductService productService;
    private final ItemService itemService;
    private final PurchaseService purchaseService;

    public MainController(EmployeeService employeeService, ProductService productService, ItemService itemService, PurchaseService purchaseService) {
        this.employeeService = employeeService;
        this.productService = productService;
        this.itemService = itemService;
        this.purchaseService = purchaseService;
    }

    @GetMapping("/")
    public String index(Model model)
    {
        model.addAttribute("employees", employeeService.findAll());

        model.addAttribute("products", productService.findAll());

        return "home/main";
    }

    @GetMapping("/employeeReports")
    public String employeeReports()
    {

        return "home/employeeReports";
    }

    @GetMapping("/productReports")
    public String productReports()
    {

        return "home/productReports";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam("employeeId") String employeeId,
            @RequestParam("productId") String productId,
            @RequestParam("quantity") int quantity ){

        System.out.println(quantity+"id"+employeeId + productId);
        return "redirect:/";
    }


}
