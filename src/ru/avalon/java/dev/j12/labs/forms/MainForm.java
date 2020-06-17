/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.forms;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import ru.avalon.java.dev.j12.labs.list.*;
import ru.avalon.java.dev.j12.labs.models.*;
import ru.avalon.java.dev.j12.labs.*;
import static ru.avalon.java.dev.j12.labs.Application.app;
import static ru.avalon.java.dev.j12.labs.Application.orderListObject;
import static ru.avalon.java.dev.j12.labs.Application.productListObject;

/**
 *
 * @author denis
 */
public class MainForm extends JFrame{
    
    OrderListForm orderListForm;
    ProductListForm productListForm;
    Order order;
    
    JMenuBar jmb = new JMenuBar();
    JMenu jmOrder = new JMenu("Заказы");
    JMenuItem jmiAddOrder = new JMenuItem("Добавить заказ");
    JMenuItem jmiDelOrder = new JMenuItem("Удалить заказ");
    JMenu jmProduct = new JMenu("Склад");
    JMenuItem jmiProductList = new JMenuItem("Посмотреть склад");
    JMenuItem jmiAddProduct = new JMenuItem("Новый продукт");
    
    JTable tblor;
    JTable tblpr;
    JSplitPane splitpane;
    
    JPanel panel = new JPanel();
    JButton addOrder = new JButton("добавить заказ...");
    JButton delOrder = new JButton("удалить заказ");
    JButton addProd = new JButton("добавить товар в заказ");
    JButton deleteProd = new JButton("Удалить товар из заказа");
    
    public MainForm(ArrayList list){
        super("Работа с заказами");
        orderListForm = new OrderListForm(list);
        productListForm = new ProductListForm(new ArrayList<Product>());
        
        setSize(900, 600);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                app.saveFile();
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
            
        });
        /*addWindowStateListener(e -> {
            if (e.getNewState() == WindowEvent.WINDOW_CLOSING){
                System.out.println("Вызываем метод saveFile()");
                app.saveFile();
                dispose();
                return;
            }
            if (e.getNewState() == WindowEvent.WINDOW_CLOSED) {
                System.out.println("Вызываем метод");
                System.exit(0);
            }
        });*/
        
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //---------------------------JMenuBar-----------------------
        add(jmb, BorderLayout.NORTH);
        jmb.add(jmOrder);
        jmOrder.add(jmiAddOrder);
        jmiAddOrder.addActionListener(e -> {
            try {
                orderListForm.addOrder();
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        jmiDelOrder.setEnabled(false);
        jmOrder.add(jmiDelOrder);
        jmiDelOrder.addActionListener(e -> {
            try {
                orderListForm.delOrder(tblor);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            jmiDelOrder.setEnabled(false);
            delOrder.setEnabled(false);
            deleteProd.setEnabled(false);
            addProd.setEnabled(false);
            });
        jmb.add(jmProduct);
        jmProduct.add(jmiProductList);
        jmiProductList.addActionListener (e -> {
            ProductDialogForm pdf = new ProductDialogForm(this, productListObject.getList());
            pdf.setVisible(true);
        });
        jmProduct.add(jmiAddProduct);
        jmiAddProduct.addActionListener(e -> {
            try {
                productListForm.addProduct();
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        //------------------------JSplitPane---------------------------
        
        tblor = new JTable(orderListForm);
        tblpr = new JTable(productListForm);
        splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(tblor),
                new JScrollPane(tblpr));
        splitpane.setDividerLocation(300);
        add(splitpane);
        
        // --------------------------Кнопки-----------------------------------
        
        add(panel, BorderLayout.SOUTH);
        
        //Кнопка "добавить заказ"
        panel.add(addOrder);
        addOrder.addActionListener(e -> {
            try {
                orderListForm.addOrder();
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        //Кнопка "удалить заказ"
        panel.add(delOrder);
        delOrder.setEnabled(false);
        delOrder.addActionListener(e -> {
            try {
                orderListForm.delOrder(tblor);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            jmiDelOrder.setEnabled(false);
            delOrder.setEnabled(false);
            deleteProd.setEnabled(false);
            addProd.setEnabled(false);
            //if(!ord.buttonDelete())
            //    JOptionPane.showMessageDialog(this, "Удаление не получилось", "Error", JOptionPane.ERROR_MESSAGE);
        });
        
        //Кнопка "добавить товар в заказ"
        panel.add(addProd);
        addProd.setEnabled(false);
        addProd.addActionListener(e -> {
            try {
                productListForm.addProductToOrder();
                /*ProductDialogForm pdf = new ProductDialogForm(this);
                pdf.setVisible(true);*/
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        //Кнопка "Удалить товар из заказа"
        panel.add(deleteProd);
        deleteProd.setEnabled(false);
        deleteProd.addActionListener(e -> {
            int [] rows = tblpr.getSelectedRows();
            Arrays.sort(rows);
            for (int i=rows.length-1; i>=0; i--) {
                try {
                    productListForm.buttonDelete(rows[i]);
                } catch (SQLException ex) {
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            deleteProd.setEnabled(false);
        });
        
        //----------------------Слушатели полей JSplitPane---------------------
        //Слушатель верхнего поля JSplitPane и вывод товаров заказа в нижнем поле
        tblor.getSelectionModel().addListSelectionListener(e -> {
            if (tblor.getSelectedRow()!=-1){
                int i = tblor.getSelectedRow();
                order = orderListObject.getList().get(i);
                productListForm.setList(order.getList(), order);
                jmiDelOrder.setEnabled(true);
                delOrder.setEnabled(true);
                addProd.setEnabled(true);
            } else productListForm.setList(new ArrayList <Product>(), null);
        });
            
        // Слушатель нижнего поля JSplitPane
        tblpr.getSelectionModel().addListSelectionListener(e -> {
            if (tblpr.getSelectedRow()!=-1){
                deleteProd.setEnabled(true);
            }
        });
    }
}
