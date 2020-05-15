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
import ru.avalon.java.dev.j12.labs.list.ProductList;

/**
 *
 * @author denis
 */
public class MainForm extends JFrame{
    
    OrderListForm orderListForm;
    ProductListForm productListForm;
    private JTextArea content;
    
    public MainForm(ArrayList list){
        super("Работа с заказами");
        orderListForm = new OrderListForm(list);
        productListForm = new ProductListForm(ProductList.productListObject.getList());
        
        setBounds(300, 200, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //JPanel tablePane = new JPanel(new BorderLayout());
        JTable tblor = new JTable(orderListForm);
        JTable tblpr = new JTable(productListForm);
        
        content = new JTextArea();
        content.setEditable(false);
        
        JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(tblor),
                new JScrollPane(tblpr));
        splitpane.setDividerLocation(300);
        add(splitpane);
        
        //tablePane.add(new JScrollPane(tbl), BorderLayout.CENTER);
        //tablePane.add(tbl.getTableHeader(), BorderLayout.NORTH);
        //add(tablePane, BorderLayout.CENTER);
        
        
        
        JToolBar toolBar = new JToolBar();
        add(toolBar, BorderLayout.SOUTH);
        
        JButton add = new JButton("add");
        toolBar.add(add);
        add.addActionListener(e -> orderListForm.buttonAdd());
        
        JButton delete = new JButton("delete");
        toolBar.add(delete);
        
        delete.addActionListener(e -> {
            int [] rows = tblor.getSelectedRows();
            Arrays.sort(rows);
            for (int i = rows.length-1 ; i>=0 ; i--){
                orderListForm.buttonDelete(rows[i]);
            }
            
            //if(!ord.buttonDelete())
            //    JOptionPane.showMessageDialog(this, "Удаление не получилось", "Error", JOptionPane.ERROR_MESSAGE);
        });
        
        JButton edit = new JButton("edit");
        toolBar.add(edit);
    }
}
