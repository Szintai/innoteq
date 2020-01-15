package com.Innoteq.innoteq.model;

import javax.persistence.*;
import java.util.Comparator;

@Entity
@Table( name = "items")
@SequenceGenerator(name = "default_gen", sequenceName = "item_seq", allocationSize = 1)
public class Item extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    private int quantity;

    private int price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Item() {
    }

    public Item(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
        this.price=quantity*product.getPrice();
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.price=this.product.getPrice() *quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public static Comparator<Item> PriceComparator =new Comparator<Item>(){



        @Override
        public int compare(Item p1, Item p2){

            int price1=p1.getPrice();
            int price2=p2.getPrice();

            return Integer.compare(price2,price1);

        }};

}
