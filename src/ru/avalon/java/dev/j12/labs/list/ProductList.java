/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import ru.avalon.java.dev.j12.labs.models.Product;
import java.util.Comparator;

/**
 *
 * @author denis
 */
public class ProductList implements Serializable{
    ArrayList<Product> list = new ArrayList<>();
    
    public void addProduct(Product product) {
        list.add(product);
        Collections.sort(list, new MyComp());
    }
    
    public ArrayList getList() {
        return list;
    }

    //Метод для получения уникального артикула
    public Integer getUniqueID(){
        if (list == null) return 0;
        if(list.isEmpty()) return 1;
        Integer num=1;
        for (Object obj : list){
            if (((Product)obj).getID()>num) num = ((Product)obj).getID();
        }
        return num+1;
    }
    
        @Override
    public String toString() {
        return "Storage{" + "storage=" + list + '}';
    }
}

class MyComp implements Comparator<Product>, Serializable{

    @Override
    public int compare(Product product1, Product product2) {
        //Сортировка по наименованию, цвету и цене
        String str1 = product1.getName()+product1.getColor()+product1.getPrice();
        String str2 = product2.getName()+product2.getColor()+product2.getPrice();
        return str1.compareTo(str2);
        //Сортировка по артикулу
        //return product1.getId().compareTo(product2.getId());
    }
}
