/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.controlers;

import ru.avalon.java.dev.j12.labs.list.OrderList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ru.avalon.java.dev.j12.labs.models.Order;
import ru.avalon.java.dev.j12.labs.models.Product;
//import ru.avalon.java.dev.j12.labs.models.OrderStatus;


/**
 *
 * @author denis
 */
public class DBOrders {
    OrderList ordlist = new OrderList();
    ArrayList <Product> prodlist = new ArrayList();
    Order order;
    private final String SHOP = "jdbc:derby:D:\\MyJavaDB\\shop";
    private final String USER = "root";
    private final String PAS = "root";
    private final String SELECT_ORDERS = "SELECT * FROM ORDERS";
    private final String SELECT_SOLD_PRODUCTS = "SELECT * FROM SOLD_PRODUCTS";
    private final String INSERT =
            "insert into ORDERS (DATE, CONTACTNAME, CONTACTTEL, ADDRESS, DISCOUNT)"
            + "values ('%s', '%s', '%s', '%s', %s)";
    private final String INSERT_SOLD_PRODUCTS =
            "insert into SOLD_PRODUCTS (ID_PRODUCTS, ID_ORDERS, NAME, COLOR, PRICE, AMOUNT)"
            + "values (%s, %s, '%s', '%s', %s, %s)";
    private final String UPDATE_ADD_PRODUCT = "update PRODUCTS set balance=balance-%s where id=%s";
    private final String DELETE_ORDER = "delete from ORDERS where ID=%s";
    private final String DELETE_PRODUCT_TO_ORDER = "delete from SOLD_PRODUCTS where ID_PRODUCTS=%s AND ID_ORDERS=%s";
    
    public OrderList dbRead () throws SQLException{
        try (Connection connection = DriverManager.getConnection(SHOP, USER, PAS);
            Statement statement = connection.createStatement();
            Statement statement2 = connection.createStatement();
            ResultSet  resultset = statement.executeQuery(SELECT_ORDERS);
            ResultSet  resultset2 = statement2.executeQuery(SELECT_SOLD_PRODUCTS))
        {
            
            while (resultset.next()){
                order = new Order(resultset.getDate("DATE"),
                        resultset.getString("CONTACTNAME"), resultset.getString("CONTACTTEL"),
                        resultset.getString("ADDRESS"), resultset.getInt("DISCOUNT"));
                order.setID(resultset.getInt("ID"));
                ordlist.addOrder(order);
            }
            while (resultset2.next()){
                for (Order ord : ordlist.getList()){
                    if (resultset2.getInt("ID_ORDERS") == ord.getID()){
                        Product prod = new Product(resultset2.getString("NAME"), resultset2.getString("COLOR"));
                        prod.setID(resultset2.getInt("ID_PRODUCTS"));
                        prod.setPrice(resultset2.getInt("PRICE"));
                        prod.setBalance(resultset2.getInt("AMOUNT"));
                        ord.addProductToOrder(prod);
                    }
                }
            }
        }
        return ordlist;
    }
    
    public Order addOrder (Order order) throws SQLException{
        try (Connection connection = DriverManager.getConnection(SHOP, USER, PAS);
                Statement statement = connection.createStatement();
                ResultSet resultset = connection.createStatement().executeQuery(SELECT_ORDERS)){
            statement.executeUpdate(String.format(INSERT, order.getDate(),
                    order.getContactName(), order.getContactTel(),
                    order.getAddress(), order.getDiscount()));
            int ID = 0;
            while (resultset.next()){
                if (ID < resultset.getInt("ID")){ID = resultset.getInt("ID");}
            }
            order.setID(ID);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(new JFrame(), "База данных не доступна,"
                    + "\nизменения будут сохранены в резервную копию.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return order;
    }
    
    public void addProductToOrder (Product prod, Order order) throws SQLException{
        try (Connection connection = DriverManager.getConnection(SHOP, USER, PAS);
                Statement statement = connection.createStatement()){
            //Добавляем товар в заказ
            statement.executeUpdate(String.format(INSERT_SOLD_PRODUCTS, prod.getID(),
                    order.getID(), prod.getName(), prod.getColor(), prod.getPrice(),
                    prod.getBalance()));
            // Корректируем количество товара на складе
            statement.executeUpdate(String.format(UPDATE_ADD_PRODUCT, prod.getBalance(), prod.getID()));
        }
    }
    
    public void delOrder (Order  order) throws SQLException{
        try (Connection connection = DriverManager.getConnection(SHOP, USER, PAS);
                Statement statement = connection.createStatement()){
            statement.executeUpdate(String.format(DELETE_ORDER, order.getID()));
        }
    }
    
    public void delProductToOrder (Product prod, Order  order) throws SQLException{
        try (Connection connection = DriverManager.getConnection(SHOP, USER, PAS);
                Statement statement = connection.createStatement()){
            //Удаляем продукт заказа
            statement.executeUpdate(String.format(DELETE_PRODUCT_TO_ORDER, prod.getID(), order.getID()));
            statement.executeUpdate(String.format(UPDATE_ADD_PRODUCT, prod.getBalance(), prod.getID()));
        }
    }
}
