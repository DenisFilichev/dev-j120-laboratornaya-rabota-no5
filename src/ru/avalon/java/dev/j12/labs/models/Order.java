/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.models;

import java.io.Serializable;
import ru.avalon.java.dev.j12.labs.list.ProductList;
import java.util.ArrayList;
import java.util.Date;
import ru.avalon.java.dev.j12.labs.controlers.IDSearch;
import ru.avalon.java.dev.j12.labs.controlers.SearchByID;


/**
 *
 * @author denis
 */

enum OrderStatus {
    PREPARING, SHIPPED, CANCELED
}

public class Order implements Serializable, IDSearch {
    private final int ID;
    private final Date DATE;
    private final ArrayList <Product> PRODUCTLIST = new ArrayList<>(); // Переменная для хранения списка заказанных товаров
    private String contactName;
    private String contactTel;
    private String address;
    private int discount = 0;
    private OrderStatus orderStatus = OrderStatus.PREPARING;
    
    //Конструктор для Заказа без скидки
    public Order(int ID, String contactName, String contactTel, String address) {
        DATE = new Date();
        this.contactName = contactName;
        this.contactTel = contactTel;
        this.address = address;
        this.ID = ID;
    }
    
    //Конструтор для заказа со скидкой
    public Order(int ID, String contactName, String contactTel, String address, int discount) {
        this(ID, contactName, contactTel, address);
        this.discount = discount;
        
    }
    
    // добавление товара в заказ
    public void addProductToOrderList (int ID, ProductList productListObject, int quantity){
        if (productListObject==null || productListObject.getList().isEmpty()) return;
        int index = SearchByID.objectSearch(ID, productListObject.getList());
        Product prod = (Product)productListObject.getList().get(index);
        if (prod.getBalance() >= quantity){
            Product prodOrder = new Product(prod.getID(), prod.getName(), prod.getColor(), (prod.getPrice()*((100-discount))/100), quantity);
            prod.setBalance(prod.getBalance()-quantity); // уменьшение остатка товара на складе
            PRODUCTLIST.add(prodOrder);
        } else System.out.println("Остаток товара на складе = " + prod.getBalance());
    }
    
    // Удаление товара из заказа и передача количества товара на складе
    public void delProduct (int ID, ProductList productListObject){
        int index1 = SearchByID.objectSearch(ID, PRODUCTLIST);
        int index2 = SearchByID.objectSearch(ID, productListObject.getList());
        int balance = PRODUCTLIST.get(index1).getBalance();
        balance += ((Product)productListObject.getList().get(index2)).getBalance();
        ((Product)productListObject.getList().get(index2)).setBalance(balance);
        PRODUCTLIST.remove(index1);
    }
    
    public void setContactName(String contactName) {this.contactName = contactName;}
    public void setContactTel(String contactTel) {this.contactTel = contactTel;}
    public void setAddress(String address) {this.address = address;}
    public void setDiscount(int discount) {this.discount = discount;}
    public void setOrderStatus(OrderStatus orderStatus) {this.orderStatus = orderStatus;}

    public ArrayList<Product> getList() {return PRODUCTLIST;}
    public int getID() {return ID;}
    public Date getDate() {return DATE;}
    public String getContactName() {return contactName;}
    public String getContactTel() {return contactTel;}
    public String getAddress() {return address;}
    public int getDiscount() {return discount;}
    public OrderStatus getOrderStatus() {return orderStatus;}

    @Override
    public String toString() {
        return "Order{" + "ID= " + ID + ", date= " + DATE + ", contactName= "
                + contactName + ", contactTel= " + contactTel + ", address= " + address
                + ", discount= " + discount + ", orderStatus= " + orderStatus + '}';
    }
}
