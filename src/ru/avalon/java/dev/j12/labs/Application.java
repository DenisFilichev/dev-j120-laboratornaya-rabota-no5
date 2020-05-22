package ru.avalon.java.dev.j12.labs;

import java.io.IOException;
import ru.avalon.java.dev.j12.labs.list.ProductList;
import ru.avalon.java.dev.j12.labs.list.OrderList;
import ru.avalon.java.dev.j12.labs.models.Order;
import ru.avalon.java.dev.j12.labs.models.Product;
import ru.avalon.java.dev.j12.labs.forms.MainForm;
import ru.avalon.java.dev.j12.labs.controlers.*;

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
    public static ProductList productListObject = new ProductList();//.productListObject; // Создаем объект для хранения товаров
    public static OrderList orderListObject = new  OrderList();//.orderListObject; // Создаем объект для хранения заказов
    public static Application app;
    
    public static ProductList getProductList() {
        return productListObject;
    }

    public static void main(String[] args) {
        
        app = new Application();
        
        // Считываем список продуктов из файла
        try{
            productListObject = FileListProducts.fileRead();
        }catch (IOException | ClassNotFoundException e){
        }catch (NullPointerException r){System.out.println("NullPointerException");}
        
        // Считываем список заказов из файла
        try{
            orderListObject = FileListOrders.fileRead();
        }catch (IOException | ClassNotFoundException e){System.out.println("IOException");
        }catch (NullPointerException r){System.out.println("NullPointerException");}
        
        /*if (productListObject==null){productListObject=new ProductList();}
        if (orderListObject==null){orderListObject=new OrderList();}
        
        //Создаем единицы товаров и помещаем их на склад
        if (productListObject!=null){ //Проверка на NullPointerException
            Product prod = new Product(productListObject.getUniqueID(), "banana", "yellow");
            if (!prod.setPrice(56)) System.out.println("Стоимость не может быть ниже или равной нулю");
            if (!prod.setBalance(100)) System.out.println("Количество не может быть ниже нуля");
            productListObject.addProduct(prod);
            
            prod = new Product(productListObject.getUniqueID(), "apple", "green");
            if (!prod.setPrice(48))  System.out.println("Стоимость не может быть ниже или равной нулю");
            if (!prod.setBalance(200))  System.out.println("Стоимость не может быть ниже или равной нулю");
            productListObject.addProduct(prod);
            
        } else System.out.println("ProductListObject == null");

        //Создаем новый заказ
        Order order;
        order = new Order(orderListObject.getUniqueID(), "Anna", "+79218594578", "Просвещения 49");
        order.addProductToOrderList(1, 5);
        orderListObject.addOrder(order);
        
        order = new Order(orderListObject.getUniqueID(), "Olga", "+79215123262", "Фучика 4");
        order.addProductToOrderList(2, 1);
        orderListObject.addOrder(order);*/
        
        MainForm mfm = new MainForm(orderListObject.getList());
        mfm.setVisible(true); 
    }
    
    public void saveFile (){
        //Записываем файл со списком товаров
        try {FileListOrders.fileWrite(orderListObject);
        } catch (IOException e) {}
        
        //Записываем файл со списком заказов
        try {FileListProducts.fileWrite(productListObject);
        } catch (IOException e) {}
    }
}
