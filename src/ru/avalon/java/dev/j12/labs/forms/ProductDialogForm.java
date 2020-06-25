/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.forms;

import java.awt.Frame;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static ru.avalon.java.dev.j12.labs.Application.productListObject;
import ru.avalon.java.dev.j12.labs.list.ProductList;
import ru.avalon.java.dev.j12.labs.models.Product;

/**
 *
 * @author denis
 */
public class ProductDialogForm extends DialogForm{
    
    Product product;
    
    public ProductDialogForm(Frame owner, ArrayList <Product> list) {
        super(owner, "Товары");
        
        if (productListObject.getList().equals(list)){
            btnEditVisible(true);
            btnOk.addActionListener(e -> {
                try {
                    new ProductListForm(list).addProduct();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDialogForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        }
        
        ProductListForm productListForm = new ProductListForm(productListObject.getList());
        
        JPanel controlsPane = getControlsPane();
        
        JTable tbl = new JTable(productListForm);
        controlsPane.add(new JScrollPane(tbl));
        
        tbl.getSelectionModel().addListSelectionListener(e -> {
            int index = tbl.getSelectedRow();
            product = (Product)productListObject.getList().get(index);
        });
        setSize(900, 600);
        setLocationRelativeTo(null);
    }
    
    public Product getProduct (){
        /*String name = contactName.getText();
        String tel = contactTel.getText();
        String ad = address.getText();
        Order order = new Order(orderListObject.getUniqueID(), name, tel, ad);*/
        return product;
    }
}
