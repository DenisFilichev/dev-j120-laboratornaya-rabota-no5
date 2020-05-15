package ru.avalon.java.dev.j12.labs;

import ru.avalon.java.dev.j12.labs.list.ProductList;
import ru.avalon.java.dev.j12.labs.list.OrderList;
import ru.avalon.java.dev.j12.labs.models.Order;
import ru.avalon.java.dev.j12.labs.models.Product;
import ru.avalon.java.dev.j12.labs.controlers.FileListProducts;
import ru.avalon.java.dev.j12.labs.controlers.FileListOrders;
import ru.avalon.java.dev.j12.labs.forms.MainForm;

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
    private static ProductList productListObject = ProductList.productListObject; // Создаем объект для хранения товаров
    public static OrderList orderListObject = OrderList.orderListObject; // Создаем объект для хранения заказов
    
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
            Product prod = new Product(productListObject.getUniqueID(), "banana", "yellow");
            if (prod.setPrice(56)!=1) System.out.println("Стоимость не может быть ниже или равной нулю");
            if (prod.setBalance(10)!=1) System.out.println("Количество не может быть ниже нуля");
            productListObject.addProduct(prod);
            
            prod = new Product(productListObject.getUniqueID(), "apple", "green");
            if (prod.setPrice(48)!=1)  System.out.println("Стоимость не может быть ниже или равной нулю");
            if (prod.setBalance(20)!=1)  System.out.println("Стоимость не может быть ниже или равной нулю");
            productListObject.addProduct(prod);
            
        } else System.out.println("ProductListObject == null");

        //Создаем новый заказ
        Order order;
        order = new Order(orderListObject.getUniqueID(), "Anna", "+79218594578", "Просвещения 49");
        order.addProductToOrderList(1, productListObject, 5);
        orderListObject.addOrder(order);
        
        order = new Order(orderListObject.getUniqueID(), "Olga", "+79215123262", "Фучика 4");
        order.addProductToOrderList(2, productListObject, 1);
        orderListObject.addOrder(order);
        
        //Выводим на печать заказы
        /*System.out.println("--------Список заказов--------");
        for (Order order : orderListObject.getList()){
            System.out.println(order);
            for (Product prod : order.getList()) {System.out.println(prod);}
        }
        
        //Записываем файл со списком товаров
        try {FileListOrders.fileWrite(orderListObject);
        } catch (IOException e) {}
        
        //Записываем файл со списком заказов
        try {FileListProducts.fileWrite(productListObject);
        } catch (IOException e) {}*/
        
        MainForm mfm = new MainForm(orderListObject.getList());
        mfm.setVisible(true);
    }
}
