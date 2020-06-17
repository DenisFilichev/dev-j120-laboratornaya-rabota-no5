/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.models;

import java.io.Serializable;
import ru.avalon.java.dev.j12.labs.list.ProductList;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import static ru.avalon.java.dev.j12.labs.Application.productListObject;
import ru.avalon.java.dev.j12.labs.controlers.DBOrders;
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
    private int ID = 0;
    GregorianCalendar calendar = new GregorianCalendar();
    private final Date DATE;
    private final ArrayList <Product> PRODUCTLIST = new ArrayList<>(); // Переменная для хранения списка заказанных товаров
    private String contactName;
    private String contactTel;
    private String address;
    private int discount = 0;
    private OrderStatus status = OrderStatus.PREPARING;
    
    //Конструктор для Заказа без скидки
    public Order(String contactName, String contactTel, String address) {
        DATE = new Date(calendar.getTimeInMillis());
        //DATE = new Date();
        this.contactName = contactName;
        this.contactTel = contactTel;
        this.address = address;
        //this.ID = ID;
    }

    /* Конструктор для создания объектов из файла.
    В данном конструкторе не вызываются другие конструторы, т.к. в них устанавливается текущая дата,
    а при восстановлении объектов из файла нам необходимо устанавливать прошедшую дату.
    */
    public Order(Date DATE, String contactName, String contactTel, String address, int discount){//, OrderStatus status) {
        DATE = new Date(calendar.getTimeInMillis());
        //this.ID = ID;
        this.DATE = DATE;
        this.contactName = contactName;
        this.contactTel = contactTel;
        this.address = address;
        this.discount = discount;
        //this.status = status;
        
    }
    
    
    
    // добавление товара в заказ
    public boolean addProductToOrder (int ID, int quantity) throws SQLException{
        ArrayList <Product> list = productListObject.getList();
        if (list==null || list.isEmpty()) return false;
        int index = SearchByID.objectSearch(ID, list);
        Product prod = list.get(index);
        if (prod.getBalance() >= quantity && quantity > 0){
            Product prodOrder = new Product(prod.getID(), prod.getName(), prod.getColor());
            prodOrder.setPrice(prod.getPrice()*((100-discount))/100);
            prodOrder.setBalance(quantity);
            prod.setBalance(prod.getBalance()-quantity); // уменьшение остатка товара на складе
            //Проверка на дублирование товара в заказе
            for (Product i : PRODUCTLIST){
                if (i.getID()==prodOrder.getID()){
                    i.setBalance(i.getBalance()+prodOrder.getBalance());
                    return true;
                }
            }
            PRODUCTLIST.add(prodOrder);
            new DBOrders().addProductToOrder(prodOrder, this);
            return true;
        } else return false;
    }
    
    //
    public void addProductToOrder (Product prod){
        PRODUCTLIST.add(prod);
    }
    
    // Удаление товара из заказа и передача товара на склад
    public void delProduct (int ID, ProductList productListObject) throws SQLException{
        int index1 = SearchByID.objectSearch(ID, PRODUCTLIST);
        int index2 = SearchByID.objectSearch(ID, productListObject.getList());
        int balance = PRODUCTLIST.get(index1).getBalance();
        balance += ((Product)productListObject.getList().get(index2)).getBalance();
        ((Product)productListObject.getList().get(index2)).setBalance(balance);
        new DBOrders().delProductToOrder(PRODUCTLIST.get(index1), this);
        PRODUCTLIST.remove(index1);
    }
    
    /* Изменение скидки
    Скидка должна быть в диапазоне от 0 до 100, в этом случае скидка
    принимается и возвращается число 1, иначе возвращается число 0.
    */
    public int setDiscount(int discount) {
        if (discount<0 || discount>100) return 0;
        this.discount = discount;
        return 1;
    }
    
    public void setID (int ID){
        if (this.ID==0){this.ID = ID;}
    }
    public void setContactName(String contactName) {this.contactName = contactName;}
    public void setContactTel(String contactTel) {this.contactTel = contactTel;}
    public void setAddress(String address) {this.address = address;}
    public void setOrderStatus(OrderStatus orderStatus) {this.status = orderStatus;}

    public ArrayList<Product> getList() {return PRODUCTLIST;}
    public Date getDate() {return DATE;}
    public String getContactName() {return contactName;}
    public String getContactTel() {return contactTel;}
    public String getAddress() {return address;}
    public int getDiscount() {return discount;}
    public OrderStatus getOrderStatus() {return status;}
    
    @Override
    public int getID() {return ID;}
    
    @Override
    public String toString() {
        return "Order{" + "ID= " + ID + ", date= " + DATE + ", contactName= "
                + contactName + ", contactTel= " + contactTel + ", address= " + address
                + ", discount= " + discount + ", orderStatus= " + status + '}';
    }
}
