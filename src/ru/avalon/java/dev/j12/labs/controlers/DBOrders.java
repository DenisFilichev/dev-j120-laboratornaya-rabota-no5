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
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ru.avalon.java.dev.j12.labs.Config;
import ru.avalon.java.dev.j12.labs.models.Order;
import ru.avalon.java.dev.j12.labs.models.Product;
//import ru.avalon.java.dev.j12.labs.models.OrderStatus;


/**
 *
 * @author denis
 */
public class DBOrders extends InitialOrders{
    OrderList ordlist = new OrderList();
    ArrayList <Product> prodlist = new ArrayList();
    Order order;
    Config DBproperties = new Config();
    private final String SHOP = DBproperties.getDBhost();
    private final String USER = DBproperties.getDBlogin();
    private final String PAS = DBproperties.getDBpassword();
    private final String SELECT_ORDERS = "SELECT * FROM ORDERS";
    private final String SELECT_SOLD_PRODUCTS = "SELECT * FROM SOLD_PRODUCTS";
    private final String INSERT =
            "insert into ORDERS (DATE, CONTACTNAME, CONTACTTEL, ADDRESS, DISCOUNT)"
            + "values (?, ?, ?, ?, ?)";
    private final String INSERT_SOLD_PRODUCTS =
            "insert into SOLD_PRODUCTS (ID_PRODUCTS, ID_ORDERS, NAME, COLOR, PRICE, AMOUNT)"
            + "values (?, ?, ?, ?, ?, ?)";
    private final String UPDATE_ADD_PRODUCT = "update PRODUCTS set balance=balance-? where id=?";
    private final String DELETE_ORDER = "delete from ORDERS where ID=?";
    private final String DELETE_PRODUCT_TO_ORDER = "delete from SOLD_PRODUCTS where ID_PRODUCTS=? AND ID_ORDERS=?";
    
    
    @Override
    public OrderList readOrders() {
        System.out.println("SHOP =" + DBproperties.getDBhost());
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
        } catch (SQLException ex) {}
        return ordlist;
    }
    
    /*public OrderList dbRead () throws SQLException{
        System.out.println("SHOP =" + DBproperties.getDBhost());
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
    }*/
    
        @Override
    public void writeOrders(OrderList orderList) {}
    
    public Order addOrder (Order order) throws SQLException{
        try (Connection connection = DriverManager.getConnection(SHOP, USER, PAS);
                ResultSet resultset = connection.createStatement().executeQuery(SELECT_ORDERS)){
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setDate(1, order.getDate());
            preparedStatement.setString(2, order.getContactName());
            preparedStatement.setString(3, order.getContactTel());
            preparedStatement.setString(4, order.getAddress());
            preparedStatement.setInt(5, order.getDiscount());
            preparedStatement.executeUpdate();
            int ID = 0;
            while (resultset.next()){
                if (ID < resultset.getInt("ID")){ID = resultset.getInt("ID");}
            }
            order.setID(ID);
        }catch (SQLException e){
            //JOptionPane.showMessageDialog(new JFrame(), "База данных не доступна,"
            //       + "\nизменения будут сохранены в резервную копию.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return order;
    }
    
    public void addProductToOrder (Product prod, Order order) throws SQLException{
        try (Connection connection = DriverManager.getConnection(SHOP, USER, PAS)){
            //Добавляем товар в заказ
            PreparedStatement pst = connection.prepareStatement(INSERT_SOLD_PRODUCTS);
            pst.setInt(1, prod.getID());
            pst.setInt(2, order.getID());
            pst.setString(3, prod.getName());
            pst.setString(4, prod.getColor());
            pst.setInt(5, prod.getPrice());
            pst.setInt(6, prod.getBalance());
            pst.executeUpdate();

            // Корректируем количество товара на складе
            pst = connection.prepareStatement(UPDATE_ADD_PRODUCT);
            pst.setInt(1, prod.getBalance());
            pst.setInt(2, prod.getID());
            pst.executeUpdate();
            //statement.executeUpdate(String.format(UPDATE_ADD_PRODUCT, prod.getBalance(), prod.getID()));
        }
    }
    
    public void delOrder (Order  order) throws SQLException{
        try (Connection connection = DriverManager.getConnection(SHOP, USER, PAS)){
            PreparedStatement pst = connection.prepareStatement(DELETE_ORDER);
            pst.setInt(1, order.getID());
        }
    }
    
    public void delProductToOrder (Product prod, Order  order) throws SQLException{
        try (Connection connection = DriverManager.getConnection(SHOP, USER, PAS);
                Statement statement = connection.createStatement()){
            //Удаляем продукт заказа
            PreparedStatement pst = connection.prepareStatement(DELETE_PRODUCT_TO_ORDER);
            pst.setInt(1, prod.getID());
            pst.setInt(2, order.getID());

            pst = connection.prepareStatement(UPDATE_ADD_PRODUCT);
            pst.setInt(1, prod.getBalance());
            pst.setInt(2, prod.getID());
            pst.executeUpdate();
        }
    }
}
