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
public class Product {
    private final Integer article;
    private String name;
    private String color;
    private Integer price;
    private Integer balance;

    public Product (Product other){
        this(other.article, other.name, other.color, other.price, other.balance);
    }
    
    public Product(Integer article, String name, String color, Integer price, Integer balance) {
        this.article = article;
        this.name = name;
        this.color = color;
        this.price = price;
        this.balance = balance;
    }

    public void setName(String name) {this.name = name;}
    public void setColor(String color) {this.color = color;}
    public void setPrice(Integer price) {this.price = price;}
    public void setBalance(Integer balance) {this.balance = balance;}

    public Integer getArticle() {return article;}
    public String getName() {return name;}
    public String getColor() {return color;}
    public Integer getPrice() {return price;}
    public Integer getBalance() {return balance;}

    @Override
    public String toString() {
        return "Product{" + "article=" + article + ", name=" + name + ", color=" + color + ", price=" + price + ", balance=" + balance + '}';
    }  
}
