package ru.avalon.java.dev.j12.labs;

import java.util.Iterator;
import ru.avalon.java.dev.j12.labs.orders.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author denis
 */
public class Application {
    private final static Storage storage = new Storage(); // Создаем экземпляр Storage для хранения товаров
    public final static OrderList orderList = new OrderList(); // Создаем экземпляр OrderList для хранения заказов

    
    public static Storage getStorage() {
        return storage;
    }

    public static void main(String[] args) {
        //Создаем единицы товаров и помещаем их на склад
        storage.addProduct(new Product(storage.getUniqueNumber(), "banana", "yellow", 56, 100));
        storage.addProduct(new Product(storage.getUniqueNumber(), "apple", "green", 48, 100));
        storage.addProduct(new Product(storage.getUniqueNumber(), "grape", "red", 67, 100));
        storage.addProduct(new Product(storage.getUniqueNumber(), "orange", "orange", 65, 100));
        storage.addProduct(new Product(storage.getUniqueNumber(), "pear", "green", 78, 100));
        storage.addProduct(new Product(storage.getUniqueNumber(), "strawberries", "red", 63, 100));
        storage.addProduct(new Product(storage.getUniqueNumber(), "lemon", "yellow", 44, 100));
        storage.addProduct(new Product(storage.getUniqueNumber(), "avocado", "green", 98, 100));
        storage.addProduct(new Product(storage.getUniqueNumber(), "plum", "blue", 49, 100));
        storage.addProduct(new Product(storage.getUniqueNumber(), "plumBlack", "black", 49, 100));
        storage.addProduct(new Product(storage.getUniqueNumber(), "apple", "red", 45, 100));
        storage.addProduct(new Product(storage.getUniqueNumber(), "banana", "yellow", 50, 100));
    
        System.out.println("--------Список товараров на складе--------");
        Iterator <Product> itrP = storage.getStorage().iterator();
        while(itrP.hasNext())System.out.println(itrP.next());
        
        System.out.println("--------Заказы--------");
        orderList.addOrder(new Order(orderList.getUniqueNumber(), "Anna", "+79218594578", "Просвещения 49", 10));
        orderList.findOrder(1).addProductToList(2, storage, 5);
        orderList.findOrder(1).addProductToList(5, storage, 10);
        
        
        orderList.addOrder(new Order(orderList.getUniqueNumber(), "Olga", "+79215123262", "Фучика 4"));
        orderList.findOrder(2).addProductToList(2, storage, 5);
        orderList.findOrder(2).addProductToList(5, storage, 10);
        Iterator itrO = orderList.getOrderList().iterator();
        while (itrO.hasNext()) System.out.println(itrO.next());
    }
}
