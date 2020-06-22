package ru.avalon.java.dev.j12.labs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

    public static void main(String[] args) throws SQLException {
        
        app = new Application();  
        
        Config config = new Config();
        if (config.getWorkingData()==0){
            try {
                //Считываем данные из db
                System.out.println("new Config().getWorkingData()=" + config.getWorkingData());
                productListObject = new DBProduct().dbRead();
                orderListObject = new DBOrders().readOrders();
                System.out.println("new Config().getWorkingData()=" + config.getWorkingData());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(new JFrame(), "База данных недоступна.", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }if (config.getWorkingData()==1) {
            // Считываем список продуктов и заказов из файла
            try{
            productListObject = new FileListProducts().fileRead();
            orderListObject = new FileListOrders().readOrders();
            }catch (Exception e){
            JOptionPane.showMessageDialog(new JFrame(), "Файл данных недоступен.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            }
        }
        
        MainForm mfm = new MainForm(orderListObject.getList());
        mfm.setVisible(true); 
    }
    
    public void saveFile (){
        //Записываем резервный файл со списком товаров
        try {new FileListOrders().writeOrders(orderListObject);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Изменения не могут быть сохранены.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        //Записываем резервный файл со списком заказов
        try {new FileListProducts().fileWrite(productListObject);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Изменения не могут быть сохранены.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
