/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs;

/**
 *
 * @author denis
 */
public class Product implements ProductOrderList {
    private final int id;
    private String name;
    private String color;
    private int price;
    private int balance;

    public Product (Product other){
        this(other.id, other.name, other.color, other.price, other.balance);
    }
    
    public Product(int article, String name, String color, int price, int balance) {
        this.id = article;
        this.name = name;
        this.color = color;
        this.price = price;
        this.balance = balance;
    }

    public void setName(String name) {this.name = name;}
    public void setColor(String color) {this.color = color;}
    public void setPrice(int price) {this.price = price;}
    public void setBalance(Integer balance) {this.balance = balance;}

    public int getId() {return id;}
    public String getName() {return name;}
    public String getColor() {return color;}
    public int getPrice() {return price;}
    public int getBalance() {return balance;}

    @Override
    public String toString() {
        return "Product{" + "id= " + id + ", name= " + name + ", color= " + color + ", price= " + price + ", balance= " + balance + '}';
    }
}
