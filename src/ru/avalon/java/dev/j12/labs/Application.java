package ru.avalon.java.dev.j12.labs;

import ru.avalon.java.dev.j12.labs.list.ProductList;
import ru.avalon.java.dev.j12.labs.list.OrderList;
import ru.avalon.java.dev.j12.labs.models.Order;
import ru.avalon.java.dev.j12.labs.models.Product;
import java.io.IOException;
import java.util.Iterator;
import ru.avalon.java.dev.j12.labs.controlers.FileListProducts;
import ru.avalon.java.dev.j12.labs.controlers.FileListOrders;

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
    private static ProductList ProductListObject;// = new ProductList(); // Создаем объект для хранения товаров
    public static OrderList orderListObject;// = new OrderList(); // Создаем объект для хранения заказов
    
    public static ProductList getProductList() {
        return ProductListObject;
    }

    public static void main(String[] args) {
        
        // Считываем список продуктов из файла
        try{
            if (FileListProducts.fileRead()!=null){
                ProductListObject = FileListProducts.fileRead();
            } else System.out.println("Не удалось считать список товаров");
        }catch (IOException | ClassNotFoundException e){e.printStackTrace();}
        
        // Считываем список заказов из файла
        try{
            if (FileListOrders.fileRead()!=null){
                orderListObject = FileListOrders.fileRead();
            } else System.out.println("Не удалось считать список заказов");
        }catch (IOException | ClassNotFoundException e){e.printStackTrace();}
        
        if (ProductListObject==null){ProductListObject=new ProductList();}
        if (orderListObject==null){orderListObject=new OrderList();}
        
        //Создаем единицы товаров и помещаем их на склад
        /*if (ProductListObject!=null){ //Проверка на NullPointerException
            ProductListObject.addProduct(new Product(ProductListObject.getUniqueID(), "banana", "yellow", 56, 10));
            ProductListObject.addProduct(new Product(ProductListObject.getUniqueID(), "apple", "green", 48, 10));
            ProductListObject.addProduct(new Product(ProductListObject.getUniqueID(), "grape", "red", 67, 100));
            ProductListObject.addProduct(new Product(ProductListObject.getUniqueID(), "orange", "orange", 65, 100));
            ProductListObject.addProduct(new Product(ProductListObject.getUniqueID(), "pear", "green", 78, 100));
            ProductListObject.addProduct(new Product(ProductListObject.getUniqueID(), "strawberries", "red", 63, 100));
            ProductListObject.addProduct(new Product(ProductListObject.getUniqueID(), "lemon", "yellow", 44, 100));
            ProductListObject.addProduct(new Product(ProductListObject.getUniqueID(), "avocado", "green", 98, 100));
            ProductListObject.addProduct(new Product(ProductListObject.getUniqueID(), "plum", "blue", 49, 100));
            ProductListObject.addProduct(new Product(ProductListObject.getUniqueID(), "plumBlack", "black", 49, 100));
            ProductListObject.addProduct(new Product(ProductListObject.getUniqueID(), "apple", "red", 45, 100));
            ProductListObject.addProduct(new Product(ProductListObject.getUniqueID(), "banana", "yellow", 50, 100));
        } else System.out.println("ProductListObject == null");*/
    
        System.out.println("--------Список товараров на складе--------");
            for (Object obj : ProductListObject.getList()){
            System.out.println(obj);
        }
        

        //Создаем новый заказ
        /*if (orderListObject!=null){ //Проверка на NullPointerException
            orderListObject.addOrder(new Order(orderListObject.getUniqueID(), "Anna", "+79218594578", "Просвещения 49", 10));
            // Добавляем к заказу товары
            if (orderListObject.findOrder(1)!=null) orderListObject.findOrder(1).addProductToOrderList(1, ProductListObject, 5);
            else System.out.println("Введен не верный номер заказа");
            if (orderListObject.findOrder(1)!=null) orderListObject.findOrder(1).addProductToOrderList(1, ProductListObject, 4);
            else System.out.println("Введен не верный номер заказа");
        
            orderListObject.addOrder(new Order(orderListObject.getUniqueID(), "Olga", "+79215123262", "Фучика 4"));
            if (orderListObject.findOrder(2)!=null) orderListObject.findOrder(2).addProductToOrderList(2, ProductListObject, 1);
            else System.out.println("Введен не верный номер заказа");
            if (orderListObject.findOrder(2)!=null) orderListObject.findOrder(2).addProductToOrderList(3, ProductListObject, 1);
            else System.out.println("Введен не верный номер заказа");
        }else System.out.println("orderListObject == null");*/
        
        //Выводим на печать заказы
        System.out.println("--------Список заказов--------");
        for (Order order : orderListObject.getList()){
            System.out.println(order);
            for (Product prod : order.getList()) {System.out.println(prod);}
        }
        
        //Записываем файл со списком товаров
        try {FileListOrders.fileWrite(orderListObject);
        } catch (IOException e) {}
        
        //Записываем файл со списком заказов
        try {FileListProducts.fileWrite(ProductListObject);
        } catch (IOException e) {}
    }
}
