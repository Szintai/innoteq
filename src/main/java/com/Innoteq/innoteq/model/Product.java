package com.Innoteq.innoteq.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "products")
@SequenceGenerator(name = "default_gen", sequenceName = "product_seq", allocationSize = 1)
public class Product extends BaseEntity {

    private String name;

    private int price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> items;

    public Product() {
    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
