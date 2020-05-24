/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.text.NumberFormat;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author denis
 */
public class Query extends JDialog{
    
    private JPanel controlsPane;
    private boolean okPressed;
    private JTextField number;
    
    public Query (Frame owner){
        super(owner, "Количество", true);
        
        controlsPane = new JPanel();
        controlsPane.setLayout(new BoxLayout(controlsPane, BoxLayout.Y_AXIS));
        add(controlsPane, BorderLayout.CENTER);
        setVisible(okPressed);
        
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        number = new JFormattedTextField(NumberFormat.getIntegerInstance());
        number.setColumns(15);
        JLabel jbl = new JLabel("Количество");
        jbl.setLabelFor(number);
        p.add(jbl);
        p.add(number);
        controlsPane.add(p);
        
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(bottom, BorderLayout.SOUTH);
        
        JButton btnOk = new JButton("Добавить");
        bottom.add(btnOk);
        btnOk.addActionListener(e -> {
            okPressed = true;
            setVisible(false);
        });
        
        JButton btnCancel = new JButton("Отмена");
        bottom.add(btnCancel);
        btnCancel.addActionListener(e -> {
            okPressed = false;
            setVisible(false);
        });
        
        pack();
        setLocationRelativeTo(null);
    }
    
    public boolean isSucccess(){
        return okPressed;
    }
    
    public int getQuantity (){
        Integer quantity = Integer.parseInt(number.getText());
        return quantity;
    }
}
