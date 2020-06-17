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
            
        try {
            //Считываем данные из db
            productListObject = new DBProduct().dbRead();
            orderListObject = new DBOrders().dbRead();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "База данных недоступна.\nДанные будут взяты из резервной копии.", "Error", JOptionPane.ERROR_MESSAGE);
            
            // Считываем список продуктов из файла, в случае не доступности derby
            try{
            productListObject = new FileListProducts().fileRead();
            }catch (IOException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(new JFrame(), "Не доступны исходные данные продуктов", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            }catch (NullPointerException r){
            JOptionPane.showMessageDialog(new JFrame(), "Не удалось прочитать исходные данные продуктов", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            }
            
            // Считываем список заказов из файла, в случае не доступности derby
            try{
            orderListObject = new FileListOrders().fileRead();
            }catch (IOException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(new JFrame(), "Не доступны исходные данные Заказов", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            }catch (NullPointerException r){
            JOptionPane.showMessageDialog(new JFrame(), "Не удалось прочитать исходные данные заказов", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            }
        }
        
        MainForm mfm = new MainForm(orderListObject.getList());
        mfm.setVisible(true); 
    }
    
    public void saveFile (){
        //Записываем резервный файл со списком товаров
        try {new FileListOrders().fileWrite(orderListObject);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Изменения не могут быть сохранены.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        //Записываем резервный файл со списком заказов
        try {new FileListProducts().fileWrite(productListObject);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Изменения не могут быть сохранены.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
