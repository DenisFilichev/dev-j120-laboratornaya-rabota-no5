/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.orders;

import java.util.ArrayList;

/**
 *
 * @author denis
 */
public class OrderList{
    ArrayList <Order> orderList = new ArrayList<>();
    
    public void addOrder (Order order){
        orderList.add(order);
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }
    
    //Метод для получения уникального ID заказа
    public Integer getUniqueNumber(){
        if (orderList==null) return 0;
        if (orderList.isEmpty()) return 1;
        Integer i = 1;
        for (Object obj : orderList){
            if (((Order)obj).getIdOrder()>=i) i=((Order)obj).getIdOrder();
        }
        return i+1;
    }
    
    //Метод для поиска заказа по его ID
    public Order findOrder (Integer id){
        try{
        for (Object obj : orderList){
            if (((Order)obj).getIdOrder()==id) return (Order)obj;
        }
        throw new NullPointerException();
        }catch (NullPointerException e){
            System.out.println("Введен не существующий номер заказа");
        }
        return null;
    }

    @Override
    public String toString() {
        return "OrderList{" + "orderList=" + orderList + '}';
    }
    
    
}
