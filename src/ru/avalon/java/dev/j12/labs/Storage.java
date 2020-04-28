/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs;

import java.util.Comparator;
import java.util.TreeSet;

/**
 *
 * @author denis
 */
public class Storage {
    TreeSet<Product> storage = new TreeSet<>(new MyComp());
    public void addProduct(Product product) {
        storage.add(product);
        //Collections.sort(storage, new MyComp());
    }
    
    public TreeSet getStorage() {
        return storage;
    }
    

    //Метод для получения уникального артикула
    public Integer getUniqueNumber(){
        if (storage == null) return 0;
        if(storage.isEmpty()) return 1;
        Integer num=1;
        for (Object obj : storage){
            if (((Product)obj).getArticle()>num) num = ((Product)obj).getArticle();
        }
        return num+1;
    }
    
        @Override
    public String toString() {
        return "Storage{" + "storage=" + storage + '}';
    }
}

class MyComp implements Comparator<Product>{

    @Override
    public int compare(Product product1, Product product2) {
        //Сортировка по наименованию, цвету и цене
        /*String str1 = product1.getName()+product1.getColor()+product1.getPrice();
        String str2 = product2.getName()+product2.getColor()+product2.getPrice();
        return str1.compareTo(str2);*/
        //Сортировка по артикулу
        return product1.getArticle().compareTo(product2.getArticle());
    }
}
