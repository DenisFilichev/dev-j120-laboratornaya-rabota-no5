/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author denis
 */
public class DefaultDB {
    
    Config DBproperties = new Config();
    private final String SHOP = DBproperties.getDBhost();
    private final String USER = DBproperties.getDBlogin();
    private final String PAS = DBproperties.getDBpassword();
    
    public void createTable() throws SQLException{
        try (Connection connection = DriverManager.getConnection(SHOP, USER, PAS)){
                PreparedStatement ps = connection.prepareStatement("create table PRODUCTS (\n" +
        "    ID integer primary key generated ALWAYS as identity (start with 1, increment by 1),\n" +
        "    NAME varchar(50) not null,\n" +
        "    COLOR varchar(10),\n" +
        "    PRICE integer not null default 0,\n" +
        "    BALANCE integer not null default 0)");
                ps.executeUpdate();
                
                ps = connection.prepareStatement("create table ORDERS (\n" +
        "    ID integer primary key generated ALWAYS as identity (start with 1, increment by 1),\n" +
        "    DATE date not null,\n" +
        "    CONTACTNAME varchar(50) not null,\n" +
        "    CONTACTTEL varchar(11) not null,\n" +
        "    ADDRESS varchar(50),\n" +
        "    DISCOUNT integer not null default 0)");
                ps.executeUpdate();
                
                ps = connection.prepareStatement("create table SOLD_PRODUCTS (\n" +
        "    ID_PRODUCTS integer references PRODUCTS(ID),\n" +
        "    ID_ORDERS integer references ORDERS(ID),\n" +
        "    NAME varchar(50) not null,\n" +
        "    COLOR varchar(10),\n" +
        "    PRICE integer not null default 0,\n" +
        "    AMOUNT integer not null default 0)");
                ps.executeUpdate();
                
                ps = connection.prepareStatement("insert into PRODUCTS (name, color, price, balance)\n" +
        "    values ('banana', 'yellow', 56, 496),\n" +
        "    ('ananas', 'brown', 120, 196),\n" +
        "    ('potatoes', 'brown', 45, 985)");
                ps.executeUpdate();
                
                ps = connection.prepareStatement("insert into ORDERS (DATE, CONTACTNAME, CONTACTTEL, ADDRESS)\n" +
        "    values ('2020-06-12', 'Olga', '9115622345', 'Moskovskiy, 10'),\n" +
        "    ('2020-06-13', 'Misha', '9211224545', 'Nalichnaya, 55'),\n" +
        "    ('2020-06-14', 'Vasiliy', '9313268952', 'Veteranov, 122')");
                ps.executeUpdate();
                
                ps = connection.prepareStatement("insert into SOLD_PRODUCTS (ID_PRODUCTS, ID_ORDERS, NAME, COLOR, PRICE, AMOUNT)\n" +
        "    values (1, 1, 'banana', 'yellow', 56, 3),\n" +
        "    (2, 1, 'ananas', 'brown', 120, 2),\n" +
        "    (3, 1, 'potatoes', 'brown', 45, 5),\n" +
        "    (1, 2, 'banana', 'yellow', 56, 1),\n" +
        "    (2, 2, 'ananas', 'brown', 120, 1),\n" +
        "    (2, 3, 'ananas', 'brown', 120, 1),\n" +
        "    (3, 3, 'potatoes', 'brown', 45, 10)");
                ps.executeUpdate();
        }
    }
}