/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.j12.labs.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import static ru.avalon.java.dev.j12.labs.Application.orderListObject;
import ru.avalon.java.dev.j12.labs.list.OrderList;
import ru.avalon.java.dev.j12.labs.list.ProductList;
import ru.avalon.java.dev.j12.labs.models.Order;

/**
 *
 * @author denis
 */
public class OrderListForm extends JFrame implements TableModel{
    private final String[] columnNames = {"ID", "Дата", "Имя", "телефон", "адрес", "скидка"};
    private final Class<?>[] columnTypes = {
        Integer.class,
        Date.class,
        String.class,
        String.class,
        String.class,
        Integer.class,
    };
    private ArrayList <Order> list;
    private List<TableModelListener> listeners = new ArrayList<>();
    
    public OrderListForm(ArrayList list) {
        this.list = list;
        
    }

    @Override
    public int getRowCount() {return list.size();}

    @Override
    public int getColumnCount() {return 6;}

    @Override
    public String getColumnName(int columnIndex) {return columnNames[columnIndex];}

    @Override
    public Class<?> getColumnClass(int columnIndex) {return columnTypes[columnIndex];}

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {return false;}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order o = list.get(rowIndex);
        switch(columnIndex){
            case 0: return o.getID();
            case 1: return o.getDate();
            case 2: return o.getContactName();
            case 3: return o.getContactTel();
            case 4: return o.getAddress();
            case 5: return o.getDiscount();
            default: throw new Error("Unreachable place");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}

    @Override
    public void addTableModelListener(TableModelListener l) {listeners.add(l);}

    @Override
    public void removeTableModelListener(TableModelListener l) {listeners.remove(l);}
    
    public void buttonAdd () {
        Order order = new Order(OrderList.orderListObject.getUniqueID(), "Fedya", "+79218594578", "Наличная 3");
        OrderList.orderListObject.addOrder(order);
        
        TableModelEvent e = new TableModelEvent(this, OrderList.orderListObject.getList().size()-1,
                OrderList.orderListObject.getList().size()-1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        for (TableModelListener l : listeners) l.tableChanged(e);
    }
    
    public boolean buttonDelete (int index) {
        if (OrderList.orderListObject.getList().isEmpty()) return false;
        OrderList.orderListObject.getList().remove(index);
        TableModelEvent e = new TableModelEvent(this, index, index, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
        for (TableModelListener l : listeners) l.tableChanged(e);
        return true;
    }
    
    public void buttonEdit () {
        
    }
}
