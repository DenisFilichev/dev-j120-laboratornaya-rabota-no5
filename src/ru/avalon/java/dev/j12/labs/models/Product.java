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
    private int price = 0;
    private int balance = 0;

    public Product(int ID, String name, String color) {
        this.ID = ID;
        this.name = name;
        this.color = color;
    }
    
    /* Изменение стоимости товара
    Стоимость должна быть не ниже или равной 0, в этом случае стоимость
    принимается и возвращается число 1, иначе возвращается число 0.
    */
    public int setPrice(int price) {
        if (price <=0) return 0;
        this.price = price;
        return 1;
    }
    
    /* Изменение количества товара
    Количество должно быть не ниже 0, в этом случае количество
    принимается и возвращается число 1, иначе возвращается число 0.
    */
    public int setBalance(Integer balance) {
        if (balance <0) return 0;
        this.balance = balance;
        return 1;
    }
    
    public void setName(String name) {this.name = name;}
    public void setColor(String color) {this.color = color;}

    @Override
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
