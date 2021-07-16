package Presentation;

import Business.BaseProduct;
import Business.DeliveryService;
import Business.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class Client extends JFrame {

    DeliveryService delivery;
    DefaultTableModel modelTable;
    final JTable table;
    JScrollPane meniu;
    JLabel clientLabel;
    JPanel clientPanel;
    JLabel label;
    JTextField search;
    JButton searchBtn;
    JButton newOrder;
    JLabel order;
    JLabel clientId;
    JTextField clientIdField;
    JButton addProduct;
    JButton generateBill;
    JButton price;
    JLabel priceLabel;

    public Client(DeliveryService delivery){

        super("Client");
        this.delivery = delivery;
        setVisible(true);
        setSize(750,600);
        setLocation(500,280);

        String[] collumnNames ={"Title", "Rating", "Calories", "Proteins", "Fats", "Sodium", "Price"};
        int nr = delivery.getMeniu().size();
        Object[][] data = new Object[nr][7];
        String[] fullMenu = new String[nr];
        int i=0;

        for(Map.Entry<String, MenuItem> bp:delivery.getMeniu().entrySet()){
            if(bp.getValue() instanceof BaseProduct){
                fullMenu[i] = bp.getValue().getTitle();
                fullMenu[i] = fullMenu[i].replace("\"","");
                data[i][0] = bp.getValue().getTitle();
                data[i][1] = ((BaseProduct) bp.getValue()).getRating();
                data[i][2]= ((BaseProduct) bp.getValue()).getCalories();
                data[i][3]= ((BaseProduct) bp.getValue()).getProteins();
                data[i][4]= ((BaseProduct) bp.getValue()).getFats();
                data[i][5]= ((BaseProduct) bp.getValue()).getSodium();
                data[i][6]= ((BaseProduct) bp.getValue()).getPrice();
                i++;
            }
            else
            {
                fullMenu[i] = bp.getValue().getTitle();
                fullMenu[i] = fullMenu[i].replace("\"","");
                data[i][0] = bp.getValue().getTitle();
                data[i][1] = " ";
                data[i][2]= " ";
                data[i][3]= " ";
                data[i][4]= " ";
                data[i][5]= " ";
                data[i][6]= bp.getValue().computePrice();
                i++;
            }

        }

        clientPanel=new JPanel();
        clientPanel.setLayout(null);

        modelTable = new DefaultTableModel(data, collumnNames){
            public Class getColumns(int column){
                Class value;
                if((column>0) && (column < getColumnCount())){
                    value = getValueAt(0,column).getClass();
                }
                else{
                    value = Object.class;
                }
                return value;

            }
        };

        table = new JTable(modelTable);
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelTable);
        table.setRowSorter(sorter);
        meniu = new JScrollPane(table);
        label = new JLabel("Search: ");
        clientLabel=new JLabel("------Meniu--------");
        order = new JLabel("--------Order---------");

        clientLabel.setBounds(20,10,500,20);
        meniu.setBounds(20,40,600,200);
        label.setBounds(20,250,100,20);
        search = new JTextField("keyword");
        search.setBounds(80,250,200,20);
        searchBtn = new JButton("Search");
        searchBtn.setBounds(290,250,100,20);
        newOrder = new JButton("New Order");
        newOrder.setBounds(20,300,100,20);
        order.setBounds(20,280,500,20);
        clientId = new JLabel("ClientId: ");
        clientId.setBounds(20,320,100,20);
        clientIdField = new JTextField(40);
        clientIdField.setBounds(120,320,200,20);
        addProduct = new JButton("Add");
        addProduct.setBounds(20,360,100,20);
        generateBill = new JButton("Bill");
        generateBill.setBounds(120,360,100,20);
        price = new JButton("Price");
        price.setBounds(20, 380,100,20);
        priceLabel = new JLabel("Your price: " + delivery.totalOrderPrice(delivery.getOrderID()));
        priceLabel.setBounds(120,380,300,20);
        clientPanel.add(price);
        clientPanel.add(priceLabel);
        clientPanel.add(generateBill);
        clientPanel.add(addProduct);
        clientPanel.add(clientId);
        clientPanel.add(clientIdField);
        clientPanel.add(newOrder);
        clientPanel.add(order);
        clientPanel.add(clientLabel);
        clientPanel.add(meniu);
        clientPanel.add(label);
        clientPanel.add(search);
        clientPanel.add(searchBtn);
        getContentPane().add(clientPanel);


        searchBtn.addActionListener(e -> {
            String text = search.getText();
            if(text.length() == 0){
                sorter.setRowFilter(null);
            }
            else{
                sorter.setRowFilter(RowFilter.regexFilter(text));
            }
        });

        newOrder.addActionListener(e -> {
            String newOrderId = clientIdField.getText();
            delivery.createNewOrder(Integer.parseInt(newOrderId), new Date());
        });

        addProduct.addActionListener(e -> {
            int orderId = delivery.getOrderID();
            orderId--;
            String menuItem = (String) table.getValueAt(table.getSelectedRow(), 0);
            delivery.addOrderMenuItem(orderId,menuItem);
        });

        price.addActionListener(e -> {
            int orderId = delivery.getOrderID();
            orderId--;
            delivery.totalOrderPrice(orderId);
            priceLabel.setText("Your price: " + delivery.totalOrderPrice(orderId));
        });
        generateBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int orderId = delivery.getOrderID();
                orderId--;
                try{
                    delivery.generateBill(orderId);

                }catch (IOException ee){
                    ee.printStackTrace();

                }
            }
        });


    }
}
