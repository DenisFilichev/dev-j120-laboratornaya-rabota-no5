package ru.avalon.java.dev.j12.labs;

import ru.avalon.java.dev.j12.labs.list.ProductList;
import ru.avalon.java.dev.j12.labs.list.OrderList;
import ru.avalon.java.dev.j12.labs.models.Order;
import ru.avalon.java.dev.j12.labs.models.Product;
import java.io.IOException;
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
    private static ProductList productListObject = new ProductList(); // Создаем объект для хранения товаров
    public static OrderList orderListObject = new OrderList(); // Создаем объект для хранения заказов
    
    public static ProductList getProductList() {
        return productListObject;
    }

    public static void main(String[] args) {
        
        // Считываем список продуктов из файла
        /*try{
                productListObject = FileListProducts.fileRead();
        }catch (IOException | ClassNotFoundException e){
        }catch (NullPointerException r){System.out.println("NullPointerException");}
        
        // Считываем список заказов из файла
        try{

                orderListObject = FileListOrders.fileRead();

        }catch (IOException | ClassNotFoundException e){System.out.println("IOException");
        }catch (NullPointerException r){System.out.println("NullPointerException");}*/
        
        if (productListObject==null){productListObject=new ProductList();}
        if (orderListObject==null){orderListObject=new OrderList();}
        
        //Создаем единицы товаров и помещаем их на склад
        if (productListObject!=null){ //Проверка на NullPointerException
            productListObject.addProduct(new Product(productListObject.getUniqueID(), "banana", "yellow"));
            System.out.println((Product)productListObject.getList().get(productListObject.getUniqueID()));
            if (((Product)productListObject.getList().get(productListObject.getUniqueID()-2)).setPrice(56)==1){
                ((Product)productListObject.getList().get(productListObject.getUniqueID()-2)).setPrice(56);
            }else System.out.println("Стоимость не может быть ниже или равной нулю");
            if (((Product)productListObject.getList().get(productListObject.getUniqueID()-2)).setBalance(10)==1){
                ((Product)productListObject.getList().get(productListObject.getUniqueID()-2)).setBalance(10);
            }else System.out.println("Количество не может быть ниже нуля");
            
            productListObject.addProduct(new Product(productListObject.getUniqueID(), "apple", "green"));
            if (((Product)productListObject.getList().get(productListObject.getUniqueID()-2)).setPrice(48)==1){
                ((Product)productListObject.getList().get(productListObject.getUniqueID()-2)).setPrice(48);
            }else System.out.println("Стоимость не может быть ниже или равной нулю");
            if (((Product)productListObject.getList().get(productListObject.getUniqueID()-2)).setBalance(20)==1){
                ((Product)productListObject.getList().get(productListObject.getUniqueID()-2)).setBalance(20);
            }else System.out.println("Количество не может быть ниже нуля");
            
        } else System.out.println("ProductListObject == null");
    
        System.out.println("--------Список товараров на складе--------");
            for (Object obj : productListObject.getList()){
            System.out.println(obj);
        }
        

        //Создаем новый заказ
        if (orderListObject!=null){ //Проверка на NullPointerException
            orderListObject.addOrder(new Order(orderListObject.getUniqueID(), "Anna", "+79218594578", "Просвещения 49"));
            // Добавляем к заказу товары
            if (orderListObject.findOrder(1)!=null) orderListObject.findOrder(1).addProductToOrderList(1, productListObject, 5);
            else System.out.println("Введен не верный номер заказа");
            if (orderListObject.findOrder(1)!=null) orderListObject.findOrder(1).addProductToOrderList(1, productListObject, 4);
            else System.out.println("Введен не верный номер заказа");
        
            orderListObject.addOrder(new Order(orderListObject.getUniqueID(), "Olga", "+79215123262", "Фучика 4"));
            if (orderListObject.findOrder(2)!=null) orderListObject.findOrder(2).addProductToOrderList(2, productListObject, 1);
            else System.out.println("Введен не верный номер заказа");
            if (orderListObject.findOrder(2)!=null) orderListObject.findOrder(2).addProductToOrderList(3, productListObject, 1);
            else System.out.println("Введен не верный номер заказа");
        }else System.out.println("orderListObject == null");
        
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
        try {FileListProducts.fileWrite(productListObject);
        } catch (IOException e) {}
    }
}
