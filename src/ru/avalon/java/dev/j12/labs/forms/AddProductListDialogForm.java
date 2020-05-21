/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.forms;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static ru.avalon.java.dev.j12.labs.list.ProductList.productListObject;
import ru.avalon.java.dev.j12.labs.models.Product;

/**
 *
 * @author denis
 */
public class AddProductListDialogForm extends DialogForm{
    
    private JTextField jname;
    private JTextField jcolor;
    private JTextField jprice;
    private JTextField jbalance;
    
    public AddProductListDialogForm(Frame owner) {
        super(owner, "Новый продукт");
        
        JPanel controlsPane = getControlsPane();
    
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jname = new JTextField(41);
        JLabel jbl = new JLabel("Наименование");
        jbl.setLabelFor(jname);
        p.add(jbl);
        p.add(jname);
        controlsPane.add(p);
        
        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jcolor = new JTextField(46);
        jbl = new JLabel("Цвет");
        jbl.setLabelFor(jcolor);
        p.add(jbl);
        p.add(jcolor);
        controlsPane.add(p);
    
        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jprice = new JFormattedTextField(NumberFormat.getIntegerInstance());
        jprice.setColumns(46);
        jbl = new JLabel("Цена");
        jbl.setLabelFor(jprice);
        p.add(jbl);
        p.add(jprice);
        controlsPane.add(p);
        
        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jbalance = new JFormattedTextField(NumberFormat.getIntegerInstance());
        jbalance.setColumns(42);
        jbl = new JLabel("Количество");
        jbl.setLabelFor(jbalance);
        p.add(jbl);
        p.add(jbalance);
        controlsPane.add(p);
        
        pack();
        setLocationRelativeTo(null);
    }

    public Product getProduct (){
        String name, color;
        int price, balance;
        try {
            name = jname.getText();
            color = jcolor.getText();
            price = Integer.parseInt(jprice.getText());
            balance = Integer.parseInt(jbalance.getText());
            Product prod = new Product(productListObject.getUniqueID(), name, color);
            if (prod.setPrice(price)){} else throw new NumberFormatException();
            if (prod.setBalance(balance)){} else throw new NumberFormatException();
            return prod;
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Ошибка! Не верные параметры полей.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
}
