/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.controlers;

import java.util.ArrayList;

/**
 *
 * @author denis
 */

/*
Статический метод для поиска в списке требуемого объекта по его ID.
В качестве аргументов принимает ID искомого объекта и список, в котором надо искать.
Возвращает индекс списка. Если возвращат -1, то или список пустой, или такого ID в списке нет.
*/
public class SearchByID {
    public static <T extends IDSearch>int objectSearch (int ID, ArrayList <T> list){
        int i = -1;
        if (list.isEmpty()) return i;
        for (Object obj : list){
            if (((T)obj).getID()==ID){
                i = list.indexOf(obj);
            }
        }
        return i;
    }
    
}
