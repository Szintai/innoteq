package com.Innoteq.innoteq.controller;

import com.Innoteq.innoteq.model.*;
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
    private Employee reportEmployee=null;
    private Product reportProduct=new Product();
    private Product selectedProduct=null;
    private Purchase purchase=new Purchase();
    private YearAndMonth selectedYearAndMonth;
    List<YearAndMonth> yearAndMonths=new ArrayList<>();


    public MainController(EmployeeService employeeService, ProductService productService, ItemService itemService, PurchaseService purchaseService) {
        this.employeeService = employeeService;
        this.productService = productService;
        this.itemService = itemService;
        this.purchaseService = purchaseService;
    }

    @GetMapping("/")
    public String index(Model model)
    {
        if(!purchase.getItems().isEmpty()){
            List<Employee> employees=new ArrayList<>();
            employees.add(purchase.getEmployee());
            model.addAttribute("employees",employees);
            List<Product> products=new ArrayList<>();

          boolean contains=false;
            for (Product product: productService.findAll()) {
                contains=false;
                for (Item item: purchase.getItems()) {
                    if(item.getProduct().getId().equals(product.getId())){
                        contains=true;
                    }
                }
                if(!contains){products.add(product);}
            }
            model.addAttribute("products", products);
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
        List<Item> items=new ArrayList<>();
        int consumption=0;

        if(reportEmployee !=null)
        { for (Purchase p:reportEmployee.getPurchases()) {
            for (Item i : p.getItems()) {
                if (i.getCreatedAt().getYear() == selectedYearAndMonth.getYear() && i.getCreatedAt().getMonth().getValue() == selectedYearAndMonth.getMonth()) {
                    consumption += i.getPrice();
                }
            }
        }
        }
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("reportEmployee", reportEmployee);
        model.addAttribute("consumption",consumption);
        model.addAttribute("dates", dates());

        return "home/employeeReports";
    }

    @GetMapping("/productReports")
    public String productReports(Model model)
    {
        List<Item> items=new ArrayList<>();

        for (Item item: itemService.findAll()) {
            if(item.getProduct().getId().equals(reportProduct.getId()) && item.getCreatedAt().getYear() == selectedYearAndMonth.getYear() &&
            item.getCreatedAt().getMonth().getValue() == selectedYearAndMonth.getMonth())
            {items.add(item); }
        }
        items.sort(Item.PriceComparator);
        model.addAttribute("items", items);
        model.addAttribute("products", productService.findAll());
        model.addAttribute("dates", dates());

        return "home/productReports";
    }


    @PostMapping(value ="/purchase")
    public String purchase(){

        Employee employee=employeeService.findById(purchase.getEmployee().getId());
        for (Item i: purchase.getItems()) {
            i.setId(null);
            i.setPurchase(purchase);
        }
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

          purchase.setEmployee(employeeService.findById(Long.parseLong(employeeId)));
          Item item=new Item(quantity, productService.findById(Long.parseLong(productId)));
          item.setId(new Long(purchase.getItems().size()));
          purchase.getItems().add(item);

        return "redirect:/";
    }

    @PostMapping(value ="/employeeReports")
    public String employeeReport(@RequestParam Map<String, String> reqParam){

        String employeeId= reqParam.get("employeeId");
        String dateId= reqParam.get("dateId");
        reportEmployee=employeeService.findById(Long.parseLong(employeeId));
        selectedYearAndMonth =yearAndMonths.get(Integer.parseInt(dateId));

        return"redirect:/employeeReports";
    }

    @PostMapping(value ="/productReports")
    public String productReport(@RequestParam Map<String, String> reqParam){

        String productId= reqParam.get("productId");
        String dateId= reqParam.get("dateId");
        reportProduct=productService.findById(Long.parseLong(productId));
        selectedYearAndMonth =yearAndMonths.get(Integer.parseInt(dateId));

        return"redirect:/productReports";
    }

    @GetMapping("/deleteItem={itemId}")
    public String deleteItem(@PathVariable("itemId") Long itemId)
    {
        Item item=new Item();
        List<Item> items=new ArrayList<>();
        for (Item i: purchase.getItems()) {

            if(!i.getId().equals(itemId)) items.add(i);
        }

        purchase.setItems(items);

        return "redirect:/";
    }


    @PostMapping(value ="/updateItem={itemId}")
    public String addProduct(@PathVariable("itemId") Long itemId,
            @RequestParam("updateItem") int quantity ){

        for (Item item: purchase.getItems()) {
            if(item.getId().equals(itemId)){item.setQuantity(quantity);}
        }

        return "redirect:/";
    }

    public List<YearAndMonth> dates(){
        yearAndMonths=new ArrayList<>();
        YearAndMonth yearAndMonth;
        int id=0;
        boolean contains=false;
        for (Item i: itemService.findAll()) {
            contains=false;
            for (YearAndMonth y:yearAndMonths) {
                if(y.getYear() == i.getModifiedAt().getYear() && y.getMonth() == i.getModifiedAt().getMonth().getValue())
                {contains= true;}
            }
            if(!contains){
                yearAndMonth = new YearAndMonth(id, i.getModifiedAt().getYear(), i.getModifiedAt().getMonth().getValue());
                yearAndMonths.add(yearAndMonth);
                id++;}

        }

        return yearAndMonths;
    }

}
