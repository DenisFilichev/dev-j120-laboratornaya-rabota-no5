/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.models;

import java.io.Serializable;
import ru.avalon.java.dev.j12.labs.controlers.IDSearch;

/**
 *
 * @author denis
 */
public class Product implements Serializable, IDSearch {
    private final int ID;
    private String name;
    private String color;
    private int price;
    private int balance;

    public Product (Product other){
        this(other.ID, other.name, other.color, other.price, other.balance);
    }
    
    public Product(int ID, String name, String color, int price, int balance) {
        this.ID = ID;
        this.name = name;
        this.color = color;
        this.price = price;
        this.balance = balance;
    }

    public void setName(String name) {this.name = name;}
    public void setColor(String color) {this.color = color;}
    public void setPrice(int price) {this.price = price;}
    public void setBalance(Integer balance) {this.balance = balance;}

    public int getID() {return ID;}
    public String getName() {return name;}
    public String getColor() {return color;}
    public int getPrice() {return price;}
    public int getBalance() {return balance;}

    @Override
    public String toString() {
        return "Product{" + "ID= " + ID + ", name= " + name + ", color= " + color + ", price= " + price + ", balance= " + balance + '}';
    }
}
