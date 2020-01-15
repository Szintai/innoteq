package com.Innoteq.innoteq.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table( name = "employees")
@SequenceGenerator(name = "default_gen", sequenceName = "employee_seq", allocationSize = 1)
public class Employee extends BaseEntity {

    @Column( nullable = false)
    private String name;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Purchase> purchases=new ArrayList<>();

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}
