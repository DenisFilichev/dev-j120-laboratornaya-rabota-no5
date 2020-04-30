/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.controlers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import ru.avalon.java.dev.j12.labs.Storage;
import ru.avalon.java.dev.j12.labs.orders.OrderList;

/**
 *
 * @author denis
 */
public class FileListWriter {
    public static void listWrite(Storage storageObject, OrderList orderListObject) throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream("list.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(storageObject);
        oos.writeObject(orderListObject);
        oos.close();
    }
}
