package Presentation;

import Business.MenuItem;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class Employee extends JFrame implements Observer {
    JLabel deliveryService;
    JPanel box;
    JLabel order;

    public Employee(){
        super("Employees");
        setVisible(true);
        setSize(400,200);
        setLocation(500,280);

        box = new JPanel();
        deliveryService = new JLabel("ORDER'S QUEUE: ");
        box.setLayout(new BoxLayout(box,BoxLayout.Y_AXIS));
        box.add(deliveryService);
        getContentPane().add(box);

    }

    @Override
    public void update(Observable o, Object arg) {
        MenuItem mi = (MenuItem) arg;
        order = new JLabel(mi.getTitle());
        box.add(order);
        box.revalidate();

    }
}
