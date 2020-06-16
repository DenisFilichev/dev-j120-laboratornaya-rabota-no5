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
    private final String INSERT = "insert into ORDERS (DATE, CONTACTNAME, CONTACTTEL, ADDRESS, DISCOUNT) values ('%s', '%s', '%s', '%s', %s)";
    
    public OrderList dbRead () throws SQLException{
        try (Connection connection = DriverManager.getConnection(SHOP, USER, PAS);
            Statement statement = connection.createStatement();
            Statement statement2 = connection.createStatement();
            ResultSet  resultset = statement.executeQuery(SELECT_ORDERS);
            ResultSet  resultset2 = statement2.executeQuery(SELECT_SOLD_PRODUCTS))
        {
            
            while (resultset.next()){
                order = new Order(resultset.getInt("ID"), resultset.getDate("DATE"),
                        resultset.getString("CONTACTNAME"), resultset.getString("CONTACTTEL"),
                        resultset.getString("ADDRESS"), resultset.getInt("DISCOUNT"));
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
                Statement statement = connection.createStatement()){
            statement.executeUpdate(String.format(INSERT, order.getDate(), order.getContactName(), order.getContactTel(), order.getAddress(), order.getDiscount()));
        }catch (SQLException e){
            JOptionPane.showMessageDialog(new JFrame(), "База данных не доступна,\nизменения будут сохранены в резервную копию.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return order;
    }
}
