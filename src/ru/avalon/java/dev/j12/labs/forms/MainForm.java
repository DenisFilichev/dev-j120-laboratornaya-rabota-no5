/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.forms;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import ru.avalon.java.dev.j12.labs.list.OrderList;
import ru.avalon.java.dev.j12.labs.list.ProductList;

/**
 *
 * @author denis
 */
public class MainForm extends JFrame{
    
    OrderListForm orderListForm;
    ProductListForm productListForm;
    
    public MainForm(ArrayList list){
        super("Работа с заказами");
        orderListForm = new OrderListForm(list);
        productListForm = new ProductListForm(ProductList.productListObject.getList());
        
        setBounds(300, 200, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JTable tblor = new JTable(orderListForm);
        JTable tblpr = new JTable(productListForm);
        
        JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(tblor),
                new JScrollPane(tblpr));
        splitpane.setDividerLocation(300);
        add(splitpane);
        
        tblor.getSelectionModel().addListSelectionListener(e -> {
            int i = tblor.getSelectedRow();
            productListForm.setList(OrderList.orderListObject.getList().get(i).getList());
        });
        
        JToolBar toolBar1 = new JToolBar();
        add(toolBar1, BorderLayout.NORTH);
        
        JButton addOrder = new JButton("добавить заказ");
        toolBar1.add(addOrder);
        addOrder.addActionListener(e -> orderListForm.buttonAdd());
        
        JButton delete = new JButton("удалить заказ");
        toolBar1.add(delete);
        
        delete.addActionListener(e -> {
            int [] rows = tblor.getSelectedRows();
            Arrays.sort(rows);
            for (int i = rows.length-1 ; i>=0 ; i--){
                orderListForm.buttonDelete(rows[i]);
            }
            
            //if(!ord.buttonDelete())
            //    JOptionPane.showMessageDialog(this, "Удаление не получилось", "Error", JOptionPane.ERROR_MESSAGE);
        });
        
        JButton edit = new JButton("редактировать заказ");
        toolBar1.add(edit);
        
        JToolBar toolBar2 = new JToolBar();
        add(toolBar2, BorderLayout.SOUTH);
        
        JButton addProd = new JButton("добавить товар");
        toolBar2.add(addProd);
        
        JButton deleteProd = new JButton("Удалить товар");
        toolBar2.add(deleteProd);
    }
}
