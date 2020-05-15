/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.list;

import java.io.Serializable;
import ru.avalon.java.dev.j12.labs.models.Order;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public class OrderList implements Serializable{
    public final static OrderList orderListObject = new OrderList();
    final ArrayList <Order> orderList = new ArrayList<>();
    
    public void addOrder (Order order){
        orderList.add(order);
    }

    public ArrayList<Order> getList() {
        return orderList;
    }
    
    //Метод для получения уникального ID заказа
    public Integer getUniqueID(){
        if (orderList==null) return 0;
        if (orderList.isEmpty()) return 1;
        Integer i = 1;
        for (Object obj : orderList){
            if (((Order)obj).getID()>=i) i=((Order)obj).getID();
        }
        return i+1;
    }
    
    //Метод для поиска заказа по его ID
    public Order findOrder (int id){
        for (Object obj : orderList){
            if (((Order)obj).getID()==id) return (Order)obj;
        }
        return null;
    }

    @Override
    public String toString() {
        return "OrderList{" + "orderList=" + orderList + '}';
    }
}
