/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.forms;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
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
    
    public ProductListForm (ArrayList <Product> list){
        this.list = list;
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
    
}
