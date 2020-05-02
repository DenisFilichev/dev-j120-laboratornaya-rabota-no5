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
import ru.avalon.java.dev.j12.labs.list.OrderList;

/**
 *
 * @author denis
 */
public class FileListOrders {
    
    public static OrderList fileRead () throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("listorder.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (OrderList)ois.readObject();
    }
    
    public static void fileWrite(OrderList orderListObject) throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream("listorder.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(orderListObject);
        oos.close();
    }
}
