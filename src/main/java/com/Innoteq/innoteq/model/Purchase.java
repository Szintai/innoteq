package com.Innoteq.innoteq.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "purchases")
@SequenceGenerator(name = "default_gen", sequenceName = "purchase_seq", allocationSize = 1)
public class Purchase extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> items;


    public Purchase() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
