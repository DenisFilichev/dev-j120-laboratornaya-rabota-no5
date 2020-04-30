package ru.avalon.java.dev.j12.labs;

import java.io.IOException;
import java.util.Iterator;
import ru.avalon.java.dev.j12.labs.controlers.FileListReader;
import ru.avalon.java.dev.j12.labs.controlers.FileListWriter;
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
    private static Storage storageObject;// = new Storage(); // Создаем переменную Storage для хранения товаров
    public static OrderList orderListObject;// = new OrderList(); // Создаем переменную OrderList для хранения заказов
    //новые объекты созданы для избежания появления ошибки NullPointerException

    
    public static Storage getStorage() {
        return storageObject;
    }

    public static void main(String[] args) {
        
        //Создаем объекты из файлов
        FileListReader flr = new FileListReader();
        try{
            flr.fileListReader();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        if (flr.getStorage()!=null && flr.getOrderList()!=null) {
            storageObject = flr.getStorage();
            orderListObject = flr.getOrderList();
        } else System.out.println("Списки товаров и заказов пусты");
        
        //Создаем единицы товаров и помещаем их на склад
        /*storageObject = new Storage(); // Создаем экземпляр Storage для хранения товаров
        orderListObject = new OrderList(); // Создаем экземпляр OrderList для хранения заказов
        storageObject.addProduct(new Product(storageObject.getUniqueNumber(), "banana", "yellow", 56, 100));
        storageObject.addProduct(new Product(storageObject.getUniqueNumber(), "apple", "green", 48, 100));
        storageObject.addProduct(new Product(storageObject.getUniqueNumber(), "grape", "red", 67, 100));
        storageObject.addProduct(new Product(storageObject.getUniqueNumber(), "orange", "orange", 65, 100));
        storageObject.addProduct(new Product(storageObject.getUniqueNumber(), "pear", "green", 78, 100));
        storageObject.addProduct(new Product(storageObject.getUniqueNumber(), "strawberries", "red", 63, 100));
        storageObject.addProduct(new Product(storageObject.getUniqueNumber(), "lemon", "yellow", 44, 100));
        storageObject.addProduct(new Product(storageObject.getUniqueNumber(), "avocado", "green", 98, 100));
        storageObject.addProduct(new Product(storageObject.getUniqueNumber(), "plum", "blue", 49, 100));
        storageObject.addProduct(new Product(storageObject.getUniqueNumber(), "plumBlack", "black", 49, 100));
        storageObject.addProduct(new Product(storageObject.getUniqueNumber(), "apple", "red", 45, 100));
        storageObject.addProduct(new Product(storageObject.getUniqueNumber(), "banana", "yellow", 50, 100));*/
    
        System.out.println("--------Список товараров на складе--------");
            Iterator <Product> itrP = storageObject.getStorage().iterator();
            while(itrP.hasNext())System.out.println(itrP.next());
        
        /*System.out.println("--------Заказы--------");
        //Создаем новый заказ
        orderListObject.addOrder(new Order(orderListObject.getUniqueNumber(), "Anna", "+79218594578", "Просвещения 49", 10));
        // Добавляем к заказу товары
        if (orderListObject.findOrder(1)!=null) orderListObject.findOrder(1).addProductToList(2, storageObject, 5);
        else System.out.println("Введен не верный номер заказа");
        if (orderListObject.findOrder(1)!=null) orderListObject.findOrder(1).addProductToList(5, storageObject, 10);
        else System.out.println("Введен не верный номер заказа");
        
        orderListObject.addOrder(new Order(orderListObject.getUniqueNumber(), "Olga", "+79215123262", "Фучика 4"));
        if (orderListObject.findOrder(2)!=null) orderListObject.findOrder(2).addProductToList(2, storageObject, 5);
        else System.out.println("Введен не верный номер заказа");
        if (orderListObject.findOrder(2)!=null) orderListObject.findOrder(2).addProductToList(5, storageObject, 10);
        else System.out.println("Введен не верный номер заказа");*/
        
        //Выводим на печать заказы
        System.out.println("\"--------Список заказов--------\"");
        for (Order ord : orderListObject.getOrderList()){
            System.out.println(ord);
            for (Product prod : ord.getProductList()){System.out.println(prod);}
        }
        
        //Записываем файл со списками товаров и заказов
        try {
            FileListWriter.listWrite(storageObject, orderListObject);
        } catch (IOException e) {
        }
    }
}
