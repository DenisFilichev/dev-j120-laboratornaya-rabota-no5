/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.controlers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import ru.avalon.java.dev.j12.labs.Storage;
import ru.avalon.java.dev.j12.labs.orders.OrderList;

/**
 *
 * @author denis
 */
public class FileListReader {
    Storage storageObject;
    OrderList orderListObject;
    
    public void fileListReader () throws FileNotFoundException, IOException, ClassNotFoundException, NullPointerException{
        
        FileInputStream fis = new FileInputStream("list.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        storageObject = (Storage)ois.readObject();
        orderListObject = (OrderList)ois.readObject();
    }
    
    public Storage getStorage(){return storageObject;}
    public OrderList getOrderList(){return orderListObject;}
}
