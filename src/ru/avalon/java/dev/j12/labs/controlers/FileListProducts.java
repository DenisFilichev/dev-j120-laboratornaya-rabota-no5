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
import ru.avalon.java.dev.j12.labs.list.ProductList;


/**
 *
 * @author denis
 */
public class FileListProducts extends InitialProducts{
    
    /*public ProductList fileRead () throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("listproduct.dat"));
        ProductList obj = (ProductList)ois.readObject();
        ois.close();
        return obj;
    }
    
    public void fileWrite (ProductList obj) throws FileNotFoundException, IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("listproduct.dat"));
        oos.writeObject(obj);
        oos.close();
    }*/

    @Override
    public ProductList readProduct() {
        ProductList obj;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("listproduct.dat"))) {
            obj = (ProductList)ois.readObject();
            return obj;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileListProducts.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileListProducts.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileListProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void writeProduct(ProductList obj) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("listproduct.dat"))) {
            oos.writeObject(obj);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileListProducts.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileListProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
