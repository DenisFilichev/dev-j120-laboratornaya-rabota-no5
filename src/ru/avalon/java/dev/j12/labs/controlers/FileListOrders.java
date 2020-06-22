/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.controlers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ru.avalon.java.dev.j12.labs.list.OrderList;
import ru.avalon.java.dev.j12.labs.list.ProductList;


/**
 *
 * @author denis
 */
public class FileListOrders extends InitialOrders{
    
    /*public OrderList fileRead () throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("listorder.dat"));
        OrderList obj = (OrderList)ois.readObject();
        ois.close();
        return obj;
    }
    
    public void fileWrite(OrderList obj) throws FileNotFoundException, IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("listorder.dat"));
        oos.writeObject(obj);
        oos.close();
    }*/

    @Override
    public OrderList readOrders() {
        OrderList obj = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("listorder.dat"))) {
            obj = (OrderList)ois.readObject();
        } catch (Exception ex) {
        } 
        return obj;
    }

    @Override
    public void writeOrders (OrderList orderList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("listorder.dat"))) {
            oos.writeObject(orderList);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileListOrders.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileListOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

