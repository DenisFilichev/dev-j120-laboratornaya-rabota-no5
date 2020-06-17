/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.controlers;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author denis
 */
public class Sync {
    
    private final String SHOP = "jdbc:derby:D:\\MyJavaDB\\shop";
    private final String USER = "root";
    private final String PAS = "root";
    private final String SELECT_ORDERS = "SELECT * FROM ORDERS";
    private final String SELECT_SOLD_PRODUCTS = "SELECT * FROM SOLD_PRODUCTS";
    private final String INSERT = "insert into ORDERS (DATE, CONTACTNAME, CONTACTTEL, ADDRESS, DISCOUNT) values ('%s', '%s', '%s', '%s', %s)";
    
    public void findInstance () throws SQLException{
        try (Connection connection = DriverManager.getConnection(SHOP, USER, PAS);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery(SHOP)){
            
        }
    }
}
