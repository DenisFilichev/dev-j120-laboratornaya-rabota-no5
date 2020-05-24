/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author denis
 */
public class DialogForm extends JDialog{
    private JPanel controlsPane = new JPanel();
    private boolean okPressed = false;
    private boolean buttonVisible = false;
    JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton btnOk = new JButton("Добавить");
    JButton btnEdit = new JButton("Редактировать товар");
    JButton btnCancel = new JButton("Закрыть");
    
    public DialogForm (Frame owner, String title){
        super(owner, title, true);
        
        controlsPane.setLayout(new BoxLayout(controlsPane, BoxLayout.Y_AXIS));
        add(controlsPane, BorderLayout.CENTER);
        setVisible(okPressed);
        
        add(bottom, BorderLayout.SOUTH);
        
        bottom.add(btnOk);
        btnOk.addActionListener(e -> {
            okPressed = true;
            setVisible(false);
        });
            
        bottom.add(btnEdit);
        btnEdit.setVisible(false);
        btnEdit.addActionListener(e -> {
            setVisible(false);
        });
        
        bottom.add(btnCancel);
        btnCancel.addActionListener(e -> {
            okPressed = false;
            setVisible(false);
         });
    }
    
    protected JPanel getControlsPane() {
        return controlsPane;
    }
    
    public boolean isSucccess(){
        return okPressed;
    }
    
    public void btnEditVisible(boolean buttonVisible){
        btnEdit.setVisible(true);
    }
}
