/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.orders;

import java.util.ArrayList;
import java.util.Date;
import ru.avalon.java.dev.j12.labs.*;


/**
 *
 * @author denis
 */

enum OrderStatus {
    preparing, shipped, canceled
}

public class Order {
    private final Integer idOrder;
    private final Date date;
    private String contactName;
    private String contactTel;
    private String address;
    private Integer discount = 0;
    private OrderStatus orderStatus = OrderStatus.preparing;
    private final ArrayList <Product> productList = new ArrayList<>(); // Переменная для хранения списка заказанных товаров
    
    //Конструктор для Заказа без скидки
    public Order(Integer idOrder, String contactName, String contactTel, String address) {
        date = new Date();
        this.contactName = contactName;
        this.contactTel = contactTel;
        this.address = address;
        this.idOrder = idOrder;
    }
    
    //Конструтор для заказа со скидкой
    public Order(Integer idOrder, String contactName, String contactTel, String address, Integer discount) {
        this(idOrder, contactName, contactTel, address);
        this.discount = discount;
        
    }
    
    public void addProductToList (int article, Storage storage, int quantity){
        if (storage.getStorage().isEmpty()) return;
        for (Object obj : storage.getStorage()){
            if (((Product)obj).getArticle()==article){
                Product prod = new Product(((Product)obj).getArticle(), ((Product)obj).getName(),
                        ((Product)obj).getColor(), (((Product)obj).getPrice()*((100-discount))/100), quantity);
                productList.add(prod);
            }
        }
    }
    
    public void setContactName(String contactName) {this.contactName = contactName;}
    public void setContactTel(String contactTel) {this.contactTel = contactTel;}
    public void setAddress(String address) {this.address = address;}
    public void setDiscount(Integer discount) {this.discount = discount;}
    public void setOrderStatus(OrderStatus orderStatus) {this.orderStatus = orderStatus;}

    public Integer getIdOrder() {return idOrder;}
    public Date getDate() {return date;}
    public String getContactName() {return contactName;}
    public String getContactTel() {return contactTel;}
    public String getAddress() {return address;}
    public Integer getDiscount() {return discount;}
    public OrderStatus getOrderStatus() {return orderStatus;}

    @Override
    public String toString() {
        return "Order{" + "idOrder=" + idOrder + ", date=" + date + ", contactName=" + contactName + ", contactTel=" + contactTel + ", address=" + address + ", discount=" + discount + ", orderStatus=" + orderStatus + '}';
    }
    
}
