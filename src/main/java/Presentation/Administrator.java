package Presentation;

import Business.BaseProduct;
import Business.CompositeProduct;
import Business.DeliveryService;
import Business.MenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

public class Administrator extends JFrame {

    DeliveryService delivery;
    //create product
    JPanel createPanel;
    JLabel createLabel;
    JButton createBaseProduct;
    JButton createCompositeProduct;
    JLabel titleLabel;
    JLabel ratingLabel;
    JLabel caloriesLabel;
    JLabel proteinsLabel;
    JLabel fatsLabel;
    JLabel sodiumLabel;
    JLabel priceLabel;
    JTextField titleText;
    JTextField ratingText;
    JTextField caloriesText;
    JTextField proteinsText;
    JTextField fatsText;
    JTextField sodiumText;
    JTextField priceText;

    //add
    JLabel addLabel;
    JComboBox compositeProductToAdd;
    JComboBox newItemToProd;
    JButton addComponent;

    //edit base product
    JLabel editLabel;
    JComboBox editBaseProduct;
    JLabel titleLabelEdit;
    JLabel ratingLabelEdit;
    JLabel caloriesLabelEdit;
    JLabel proteinsLabelEdit;
    JLabel fatsLabelEdit;
    JLabel sodiumLabelEdit;
    JLabel priceLabelEdit;
    JTextField titleTextEdit;
    JTextField ratingTextEdit;
    JTextField caloriesTextEdit;
    JTextField proteinsTextEdit;
    JTextField fatsTextEdit;
    JTextField sodiumTextEdit;
    JTextField priceTextEdit;
    JButton editBaseProductBtn;

    //delete base prod
    JButton deleteBaseProduct;

    //edit/delete composite prod
    JLabel deleteLabel;
    JComboBox editCompProd;
    JComboBox removeComp;
    JButton deleteComponent;

    //reports
    JLabel raport1;
    JLabel raport2;
    JLabel raport3;
    JLabel raport4;
    JLabel time1;
    JLabel time2;
    JTextField time1T;
    JTextField time2T;
    JLabel moreThan;
    JTextField moreThan2;
    JLabel noOfOrders;
    JLabel orderValue;
    JTextField noOrders;
    JTextField valueO;
    JLabel day;
    JTextField daYt;
    JButton generateReports;


    public Administrator(DeliveryService delivery){
        super("Administrator");
        this.delivery = delivery;
        setVisible(true);
        setSize(600,900);
        setLocation(550,50);

        createPanel = new JPanel();
        createLabel = new JLabel("---------Create Product---------");
        createBaseProduct = new JButton("Base Product");
        createCompositeProduct = new JButton("Composite Product");
        titleLabel = new JLabel("Title: ");
        ratingLabel = new JLabel("Rating:");
        caloriesLabel = new JLabel("Calories: ");
        proteinsLabel = new JLabel("Proteins: ");
        fatsLabel = new JLabel("Fats: ");
        sodiumLabel = new JLabel("Sodium: ");
        priceLabel = new JLabel("Price: ");
        titleText = new JTextField(40);
        ratingText = new JTextField(10);
        caloriesText = new JTextField(10);
        proteinsText = new JTextField(10);
        fatsText = new JTextField(10);
        sodiumText = new JTextField(10);
        priceText = new JTextField(10);

        createLabel.setBounds(20, 10,200, 20);
        createBaseProduct.setBounds(20,40,150,20);
        createCompositeProduct.setBounds(170,40,150,20);
        titleLabel.setBounds(20,70,100,20);
        titleText.setBounds(120,70,100,20);
        ratingLabel.setBounds(240,70,100,20);
        ratingText.setBounds(300,70,100,20);
        caloriesLabel.setBounds(20,90,100,20);
        caloriesText.setBounds(120,90,100,20);
        proteinsLabel.setBounds(240,90,100,20);
        proteinsText.setBounds(300,90,100,20);
        fatsLabel.setBounds(20,110,100,20);
        fatsText.setBounds(120,110,100,20);
        sodiumLabel.setBounds(240,110,100,20);
        sodiumText.setBounds(300,110,100,20);
        priceLabel.setBounds(20,130,100,20);
        priceText.setBounds(120,130,100,20);


        createPanel.setLayout(null);
        createPanel.add(createLabel);
        createPanel.add(createBaseProduct);
        createPanel.add(createCompositeProduct);
        createPanel.add(titleLabel);
        createPanel.add(titleText);
        createPanel.add(ratingLabel);
        createPanel.add(ratingText);
        createPanel.add(caloriesLabel);
        createPanel.add(caloriesText);
        createPanel.add(proteinsLabel);
        createPanel.add(proteinsText);
        createPanel.add(fatsLabel);
        createPanel.add(fatsText);
        createPanel.add(sodiumLabel);
        createPanel.add(sodiumText);
        createPanel.add(priceLabel);
        createPanel.add(priceText);

        int i=0;
        int j=0;
        for(Map.Entry<String, MenuItem> bp:delivery.getMeniu().entrySet()) {

            if(bp.getValue() instanceof BaseProduct){
                i++;
            }
            else if(bp.getValue() instanceof CompositeProduct){

                j++;
            }

        }
        String[] baseProducts = new String[i];
        String[] compositeProd = new String[j];
        i=0;
        j=0;
        for(Map.Entry<String, MenuItem> bp:delivery.getMeniu().entrySet()) {

            if(bp.getValue() instanceof BaseProduct){
                baseProducts[i] = bp.getValue().getTitle();
                baseProducts[i] = baseProducts[i].replace("\"","");
                i++;
            }
            else if(bp.getValue() instanceof CompositeProduct){
                compositeProd[j] = bp.getValue().getTitle();
                compositeProd[j] = compositeProd[j].replace("\"","");
                j++;
            }

        }

        addLabel = new JLabel("---------Add Base Product to Composite---------");
        compositeProductToAdd = new JComboBox(compositeProd);
        newItemToProd = new JComboBox(baseProducts);
        addComponent = new JButton("Add");
        addLabel.setBounds(20,150,500,20);
        compositeProductToAdd.setBounds(20,180,300,20);
        newItemToProd.setBounds(20,200,300,20);
        addComponent.setBounds(20,220,100,20);

        createPanel.add(addLabel);
        createPanel.add(compositeProductToAdd);
        createPanel.add(newItemToProd);
        createPanel.add(addComponent);


        editLabel = new JLabel("---------Edit Base Product---------");
        editBaseProduct = new JComboBox(baseProducts);
        titleLabelEdit = new JLabel("Title: ");
        ratingLabelEdit = new JLabel("Rating: ");
        caloriesLabelEdit = new JLabel("Calories: ");
        proteinsLabelEdit = new JLabel("Proteins: ");
        fatsLabelEdit = new JLabel("Fats: ");
        sodiumLabelEdit = new JLabel("Sodium: ");
        priceLabelEdit = new JLabel("Price: ");
        titleTextEdit = new JTextField(40);
        ratingTextEdit = new JTextField(10);
        caloriesTextEdit = new JTextField(10);
        proteinsTextEdit = new JTextField(10);
        fatsTextEdit = new JTextField(10);
        sodiumTextEdit = new JTextField(10);
        priceTextEdit = new JTextField(10);
        editBaseProductBtn = new JButton("Edit");
        deleteBaseProduct = new JButton("Delete");

        editLabel.setBounds(20,250,500,20);
        editBaseProduct.setBounds(20,280,300,20);
        titleLabelEdit.setBounds(20,300,300,20);
        titleTextEdit.setBounds(120,300,100,20);
        ratingLabelEdit.setBounds(240,300,300,20);
        ratingTextEdit.setBounds(300,300,100,20);
        caloriesLabelEdit.setBounds(20,320,300,20);
        caloriesTextEdit.setBounds(120,320,100,20);
        proteinsLabelEdit.setBounds(240,320,300,20);
        proteinsTextEdit.setBounds(300,320,100,20);
        fatsLabelEdit.setBounds(20,340,300,20);
        fatsTextEdit.setBounds(120,340,100,20);
        sodiumLabelEdit.setBounds(240,340,300,20);
        sodiumTextEdit.setBounds(300,340,100,20);
        priceLabelEdit.setBounds(20,360,300,20);
        priceTextEdit.setBounds(120,360,100,20);
        editBaseProductBtn.setBounds(20,380,100,20);
        deleteBaseProduct.setBounds(120,380,100,20 );

        createPanel.add(editLabel);
        createPanel.add(editBaseProduct);
        createPanel.add(titleLabelEdit);
        createPanel.add(titleTextEdit);
        createPanel.add(ratingLabelEdit);
        createPanel.add(ratingTextEdit);
        createPanel.add(caloriesLabelEdit);
        createPanel.add(caloriesTextEdit);
        createPanel.add(proteinsLabelEdit);
        createPanel.add(proteinsTextEdit);
        createPanel.add(fatsLabelEdit);
        createPanel.add(fatsTextEdit);
        createPanel.add(sodiumLabelEdit);
        createPanel.add(sodiumTextEdit);
        createPanel.add(priceLabelEdit);
        createPanel.add(priceTextEdit);
        createPanel.add(editBaseProductBtn);
        createPanel.add(deleteBaseProduct);



        deleteLabel = new JLabel("---------Edit Composed Product---------");
        editCompProd = new JComboBox(compositeProd);
        removeComp = new JComboBox();
        editCompProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeComp.removeAllItems();
                String[] selected= comboBox(editCompProd.getSelectedItem().toString());
                for (int jj=0;jj<selected.length;jj++){
                    removeComp.addItem(selected[jj]);
                }
            }
        });

        deleteComponent = new JButton("Delete");

        deleteLabel.setBounds(20,410,500,20);
        editCompProd.setBounds(20,440,300,20);
        removeComp.setBounds(20,460,300,20);
        deleteComponent.setBounds(20,480,100,20);


        createPanel.add(deleteLabel);
        createPanel.add(editCompProd);
        createPanel.add(removeComp);
        createPanel.add(deleteComponent);

        JLabel reports = new JLabel("---------Reports---------");

        generateReports = new JButton("Generate Reports");
        reports.setBounds(20,520,300,20 );
        raport1 = new JLabel("Raport1: ");
        raport1.setBounds(20, 550, 100,20);
        time1 = new JLabel("Time 1: ");
        time1.setBounds(20,580, 100, 20);
        time2 = new JLabel("Time 2:");
        time2.setBounds(20, 610, 100,20);
        time1T = new JTextField("");
        time1T.setBounds(140, 580, 100,20 );
        time2T = new JTextField("");
        time2T.setBounds(140, 610, 100,20);
        raport2 = new JLabel("Raport2: ");
        raport2.setBounds(270, 550, 100,20);
        moreThan = new JLabel("Products ordered more than: ");
        moreThan.setBounds(270, 580, 200,20);
        moreThan2 = new JTextField("");
        moreThan2.setBounds(470, 580, 50,20);
        raport3 = new JLabel("Raport3: ");
        raport3.setBounds(20,640, 100,20);
        noOfOrders = new JLabel("No orders: ");
        noOfOrders.setBounds(20,670,100,20);
        orderValue = new JLabel("Order Value: ");
        orderValue.setBounds(20, 700, 100,20);
        noOrders = new JTextField("");
        noOrders.setBounds(140,670, 100,20);
        valueO = new JTextField("");
        valueO.setBounds(140, 700,100,20);
        raport4 = new JLabel("Raport4: ");
        raport4.setBounds(270,640,100,20);
        day = new JLabel("Day of month");
        day.setBounds(270,670,100,20);
        daYt = new JTextField("");
        daYt.setBounds(400,670,100,20);
        generateReports.setBounds(20,730,200,20);


        createPanel.add(reports);
        createPanel.add(raport1);
        createPanel.add(time1);
        createPanel.add(time2);
        createPanel.add(time1T);
        createPanel.add(time2T);
        createPanel.add(raport2);
        createPanel.add(moreThan);
        createPanel.add(moreThan2);
        createPanel.add(raport3);
        createPanel.add(noOfOrders);
        createPanel.add(orderValue);
        createPanel.add(noOrders);
        createPanel.add(valueO);
        createPanel.add(raport4);
        createPanel.add(day);
        createPanel.add(daYt);
        createPanel.add(generateReports);
        getContentPane().add(createPanel);

        createBaseProduct.addActionListener(e -> {
            String title = titleText.getText();
            float rating =  Float.parseFloat(ratingText.getText());
            int calories = Integer.parseInt(caloriesText.getText());
            int proteins = Integer.parseInt(proteinsText.getText());
            int fats = Integer.parseInt(fatsText.getText());
            int sodium = Integer.parseInt(sodiumText.getText());
            float price = Float.parseFloat(priceText.getText());
            try{
                delivery.createBaseMenuItem(title,rating,calories,proteins,fats,sodium,price);
                newItemToProd.addItem(title);
                editBaseProduct.addItem(title);
            }
            catch (AssertionError ae){
                ae.printStackTrace();
            }
        });
        createCompositeProduct.addActionListener(e -> {
            String title = titleText.getText();
            try{

                delivery.createCompositeMenuItem(title);
                compositeProductToAdd.addItem(title);
                editCompProd.addItem(title);
            }catch (AssertionError ae){
                ae.printStackTrace();
            }
        });

        addComponent.addActionListener(e -> {
            String comp = compositeProductToAdd.getSelectedItem().toString();
            String newComp = newItemToProd.getSelectedItem().toString();
            delivery.addComponent(comp,newComp);
            newItemToProd.addItem(newComp);
            removeComp.addItem(newComp);
        });

        editBaseProductBtn.addActionListener(e -> {
            String title = titleText.getText();
            float rating = Float.parseFloat(ratingText.getText());
            int calories = Integer.parseInt(caloriesText.getText());
            int proteins = Integer.parseInt(proteinsText.getText());
            int fats = Integer.parseInt(fatsText.getText());
            int sodium = Integer.parseInt(sodiumText.getText());
            float price = Float.parseFloat(priceText.getText());
            try{
                delivery.editBaseProduct(editBaseProduct.getSelectedItem().toString(),title, rating,calories,proteins,fats,sodium,price);

            }catch (AssertionError ae){
                ae.printStackTrace();
            }
        });
        deleteBaseProduct.addActionListener(e -> {
            String deleteP = editBaseProduct.getSelectedItem().toString();
            delivery.deleteBaseProduct(deleteP);
            newItemToProd.removeItem(deleteP);
            editBaseProduct.removeItem(deleteP);
        });

        deleteComponent.addActionListener(e -> {
            String comp = compositeProductToAdd.getSelectedItem().toString();
            String newComp = newItemToProd.getSelectedItem().toString();
            delivery.removeComponent(comp, newComp);
            removeComp.removeItem(newComp);

        });
        ///defineWindowListener();
        generateReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    delivery.generateRaport1(Integer.parseInt(time1T.getText()), Integer.parseInt(time2T.getText()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    delivery.generateRaport2(Integer.parseInt(moreThan2.getText()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    delivery.generateRaport3(Integer.parseInt(noOrders.getText()),Integer.parseInt(valueO.getText()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    delivery.generateRaport4(Integer.parseInt(daYt.getText()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

    }

    private String[] comboBox(String component){
        CompositeProduct cp  = (CompositeProduct) delivery.getMeniu().get(component);
        int i=0;
        for (MenuItem mi:cp.getComponents()){

            i++;
        }
        String[] str = new String[i];
        i=0;

        for (MenuItem mi:cp.getComponents()){
            str[i] = mi.getTitle();
            i++;
        }
        return str;
    }


}
