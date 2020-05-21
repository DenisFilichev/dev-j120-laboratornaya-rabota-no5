/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.forms;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import ru.avalon.java.dev.j12.labs.list.ProductList;
import ru.avalon.java.dev.j12.labs.models.Order;
import ru.avalon.java.dev.j12.labs.models.Product;

/**
 *
 * @author denis
 */
public class ProductListForm extends JFrame implements TableModel {
    
    private final String[] columnNames = {"ID", "наименование", "Цвет", "цена", "количество"};
    private final Class<?>[] columnTypes = {
        Integer.class,
        String.class,
        String.class,
        Integer.class,
        Integer.class,
    };
    private ArrayList <Product> list;
    private List<TableModelListener> listeners = new ArrayList<>();
    private Order order;
    
    public ProductListForm (ArrayList <Product> list){
        this.list = list;
    }

    public void setList(ArrayList<Product> list, Order order) {
        this.list = list;
        this.order = order;
        update();
        /*TableModelEvent e = new TableModelEvent(this);
        for (TableModelListener l : listeners) l.tableChanged(e);*/
    }
    
    public void update(){
        TableModelEvent e = new TableModelEvent(this);
        for (TableModelListener l : listeners) l.tableChanged(e);
    }
    
    @Override
    public int getRowCount() {return list.size();}

    @Override
    public int getColumnCount() {return 5;}

    @Override
    public String getColumnName(int columnIndex) {return columnNames[columnIndex];}

    @Override
    public Class<?> getColumnClass(int columnIndex) {return columnTypes[columnIndex];}

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {return false;}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product prod = list.get(rowIndex);
        switch(columnIndex){
            case 0: return prod.getID();
            case 1: return prod.getName();
            case 2: return prod.getColor();
            case 3: return prod.getPrice();
            case 4: return prod.getBalance();
            default: throw new Error("Unreachable plase");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}

    @Override
    public void addTableModelListener(TableModelListener l) {listeners.add(l);}

    @Override
    public void removeTableModelListener(TableModelListener l) {listeners.remove(l);}
    
    public boolean buttonDelete (int index){
        if (list.isEmpty()) return false;
        int ID = order.getList().get(index).getID();
        order.delProduct(ID, ProductList.productListObject);
        update();
        return  true;
    }

    public void setOrder(Order order) {
        this.order = order;
        update();
    }
    
    public void addProduct (){
        AddProductListDialogForm dlf = new AddProductListDialogForm(this);
        dlf.setVisible(true);
        if(dlf.isSucccess()){
            Product prod = dlf.getProduct();
            if (prod != null){
                ProductList.productListObject.getList().add(prod);
                JOptionPane.showMessageDialog(this, "Новы товар добавлен, ID=" + prod.getID(), "Error", JOptionPane.INFORMATION_MESSAGE);
            } else addProduct();
        }
        update();
        /*TableModelEvent e = new TableModelEvent(this, ProductList.productListObject.getList().size()-1,
                ProductList.productListObject.getList().size()-1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        for (TableModelListener l : listeners) l.tableChanged(e);*/
    }
    
    public void addProductToOrder (){
        ProductDialogForm pdf = new ProductDialogForm(this, list);
        pdf.setVisible(true);
        if (pdf.isSucccess()){
            Product product = pdf.getProduct();
            int ID = product.getID();
            int quantity = 0;
            Query query = new Query(this);
            query.setVisible(true);
            if (query.isSucccess()){
                quantity = query.getQuantity();
            }
            if (order.addProductToOrderList(ID, quantity)){update();}
            else JOptionPane.showMessageDialog(this, "Ошибка! Возможно введенное количество товара больше остатка на складе или меньше 1.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
