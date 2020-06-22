/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.controlers;

import ru.avalon.java.dev.j12.labs.list.ProductList;

/**
 *
 * @author denis
 */
public abstract class InitialProducts {
    
    public abstract ProductList readProduct();
    public abstract void writeProduct();
}
