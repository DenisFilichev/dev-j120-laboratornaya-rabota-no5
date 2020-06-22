/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.controlers;

import ru.avalon.java.dev.j12.labs.list.OrderList;

/**
 *
 * @author denis
 */
public abstract class InitialOrders {
    
    public abstract OrderList readOrders ();
    public abstract void writeOrders (OrderList orderList);
}
