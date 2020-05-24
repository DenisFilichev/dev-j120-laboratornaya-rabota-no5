/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.forms;

import java.awt.FlowLayout;
import java.awt.Frame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static ru.avalon.java.dev.j12.labs.Application.orderListObject;
import ru.avalon.java.dev.j12.labs.models.Order;

/**
 *
 * @author denis
 */
public class OrderDialogForm extends DialogForm{
    private JTextField contactName;
    private JTextField contactTel;
    private JTextField address;
    
    public OrderDialogForm(Frame owner) {
        super(owner, "Новый заказ");
        
        JPanel controlsPane = getControlsPane();
    
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contactName = new JTextField(41);
        JLabel jbl = new JLabel("Имя");
        jbl.setLabelFor(contactName);
        p.add(jbl);
        p.add(contactName);
        controlsPane.add(p);
        
        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contactTel = new JTextField(41);
        jbl = new JLabel("Тел.");
        jbl.setLabelFor(contactTel);
        p.add(jbl);
        p.add(contactTel);
        controlsPane.add(p);
    
        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        address = new JTextField(40);
        jbl = new JLabel("Адрес");
        jbl.setLabelFor(address);
        p.add(jbl);
        p.add(address);
        controlsPane.add(p);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    public Order getOrder (){
        String name = contactName.getText();
        String tel = contactTel.getText();
        String ad = address.getText();
        Order order = new Order(orderListObject.getUniqueID(), name, tel, ad);
        return order;
    }
}
