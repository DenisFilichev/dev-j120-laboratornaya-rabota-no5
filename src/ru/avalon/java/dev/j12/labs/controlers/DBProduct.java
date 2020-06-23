/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.controlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import ru.avalon.java.dev.j12.labs.list.ProductList;
import ru.avalon.java.dev.j12.labs.models.Product;

/**
 *
 * @author denis
 */
public class DBProduct extends InitialProducts{
    ProductList prodlist = new ProductList();
    Product prod;
    private final String SHOP = "jdbc:derby:D:\\MyJavaDB\\shop";
    private final String USER = "root";
    private final String PAS = "root";
    private final String SELECT = "SELECT * FROM PRODUCTS";
    private final String INSERT = "insert into PRODUCTS (name, color, price, balance) values (?, ?, ?, ?)";
    
    @Override
    public ProductList readProduct() {
        try (Connection connection = DriverManager.getConnection(SHOP, USER, PAS);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery(SELECT)){
            while (resultset.next()){
                prod = new Product(resultset.getString("NAME"), resultset.getString("COLOR"));
                prod.setID(resultset.getInt("ID"));
                prod.setPrice(resultset.getInt("PRICE"));
                prod.setBalance(resultset.getInt("BALANCE"));
                prodlist.addProduct(prod);
            }
        } catch (SQLException ex) {}
        return prodlist;
    }

    @Override
    public void writeProduct(ProductList obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*public ProductList dbRead () throws SQLException {
        
        try (Connection connection = DriverManager.getConnection(SHOP, USER, PAS);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery(SELECT)){
            while (resultset.next()){
                prod = new Product(resultset.getString("NAME"), resultset.getString("COLOR"));
                prod.setID(resultset.getInt("ID"));
                prod.setPrice(resultset.getInt("PRICE"));
                prod.setBalance(resultset.getInt("BALANCE"));
                prodlist.addProduct(prod);
            }
        }
        return prodlist;
    }*/
    
    public Product addProduct (Product prod) throws SQLException{
        
        try (Connection connection = DriverManager.getConnection(SHOP, USER, PAS);
                ResultSet resultset = connection.createStatement().executeQuery(SELECT)){
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, prod.getName());
            preparedStatement.setString(2, prod.getColor());
            preparedStatement.setInt(3, prod.getPrice());
            preparedStatement.setInt(4, prod.getBalance());
            preparedStatement.executeUpdate();
            int ID = 0;
            while (resultset.next()){
                if (ID < resultset.getInt("ID")){ID = resultset.getInt("ID");}
            }
            prod.setID(ID);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(new JFrame(), "База данных не доступна,\nизменения будут сохранены в резервную копию.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return prod;
    }

    
}
