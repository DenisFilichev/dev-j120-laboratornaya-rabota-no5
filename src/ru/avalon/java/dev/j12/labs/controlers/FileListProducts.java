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
import ru.avalon.java.dev.j12.labs.list.ProductList;


/**
 *
 * @author denis
 */
public class FileListProducts {
    
    public static ProductList fileRead () throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("listproduct.dat"));
        ProductList obj = (ProductList)ois.readObject();
        ois.close();
        return obj;
    }
    
    public static void fileWrite (ProductList obj) throws FileNotFoundException, IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("listproduct.dat"));
        oos.writeObject(obj);
        oos.close();
    }
}
