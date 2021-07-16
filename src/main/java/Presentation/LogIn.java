package Presentation;

import Business.DeliveryService;
import Data.Serializator;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LogIn extends JFrame{
    DeliveryService delivery;
    JButton loginAdmin;
    JButton loginEmp;
    JButton loginClient;
    JPanel loginPanel;
    JTextField userText;
    JTextField passText;
    JButton newUser;
    JLabel username;
    JLabel password;

    public ArrayList<User> clients = new ArrayList<User>();

    public LogIn(DeliveryService delivery){
        super("Delivery Service Autentification");
        this.delivery=delivery;
        loginClient = new JButton("Client");
        loginAdmin = new JButton("Admin");
        loginEmp = new JButton("Employee");

        loginPanel = new JPanel();
        userText = new JTextField(20);
        passText = new JPasswordField(15);
        newUser = new JButton("New Client");
        username = new JLabel("Username: ");
        password = new JLabel("Password: ");

        setSize(400,200);
        setLocation(500,280);
        loginPanel.setLayout (null);


        userText.setBounds(100,30,150,20);
        passText.setBounds(100,65,150,20);
        loginAdmin.setBounds(20,100,100,20);
        loginEmp.setBounds(120,100,100,20);
        loginClient.setBounds(220,100,100,20);
        newUser.setBounds(20,135,100,20);
        username.setBounds(20,28,80,20);
        password.setBounds(20,63,80,20);

        loginPanel.add(loginAdmin);
        loginPanel.add(loginEmp);
        loginPanel.add(loginClient);
        loginPanel.add(userText);
        loginPanel.add(passText);
        loginPanel.add(newUser);
        loginPanel.add(username);
        loginPanel.add(password);

        getContentPane().add(loginPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        defineWindowListener();

        Writer writerClient;
        //  String clientFile = "newUser.txt";
        File clientFile = new File("newUser.txt");
        if(clientFile.exists()){

            //Checks if the file exists. will not add anything if the file does exist.
        }else{
            try{
                File texting = new File("newUser.txt");
                writerClient = new BufferedWriter(new FileWriter(texting));
                writerClient.write("message");
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        loginClient.addActionListener(e -> {
            try {
                File file = new File("newUser.txt");

                Scanner scan = new Scanner(file);


                String usertxt = " ";
                String passtxt = " ";
                String userGUI = userText.getText();
                String passGUI = passText.getText();


                while (scan.hasNext()) {
                    usertxt = scan.nextLine();
                    passtxt = scan.nextLine();
                    if (userGUI.equals(usertxt) && passGUI.equals(passtxt)) {
                        new Client(delivery);
                        break;
                    }

                }
                for (User c : clients) {
                    usertxt = c.getUsername();
                    passtxt = c.getPassword();
                    System.out.println(usertxt + passtxt);
                    if (userGUI.equals(usertxt) && passGUI.equals(passtxt)) {
                        new Client(delivery);
                        break;
                    }
                }

                if (userGUI.equals(usertxt) && passGUI.equals(passtxt)) {

                } else if (userGUI.equals("") && passGUI.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please insert Username and Password");
                } else {

                    JOptionPane.showMessageDialog(null, "Wrong Username / Password");
                    userText.setText("");
                    passText.setText("");
                    userText.requestFocus();
                }
            }catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

        });

        ///admin
        Writer writerAdmin;
        File adminFile = new File("admin.txt");
        if(adminFile.exists()){

            //Checks if the file exists. will not add anything if the file does exist.
        }else{
            try{
                File texting = new File("admin.txt");
                writerAdmin = new BufferedWriter(new FileWriter(texting));
                writerAdmin.write("message");
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        loginAdmin.addActionListener(e -> {
            try {
                File file = new File("admin.txt");
                Scanner scan = new Scanner(file);

                String usertxt = " ";
                String passtxt = " ";
                String userGUI = userText.getText();
                String passGUI = passText.getText();


                while (scan.hasNext()) {
                    usertxt = scan.nextLine();
                    passtxt = scan.nextLine();
                    if(userGUI.equals(usertxt) && passGUI.equals(passtxt)) {
                        new Administrator(delivery);
                        break;
                    }

                }

                if(userGUI.equals(usertxt) && passGUI.equals(passtxt)) {

                }
                else
                if(userGUI.equals("") && passGUI.equals("")){
                    JOptionPane.showMessageDialog(null,"Please insert Username and Password");
                }
                else {

                    JOptionPane.showMessageDialog(null,"Wrong Username / Password");
                    userText.setText("");
                    passText.setText("");
                    userText.requestFocus();
                }
            } catch (IOException d) {
                d.printStackTrace();
            }

        });

        ///employee
        Writer writerEmp;
        File empFile = new File("emp.txt");
        if(empFile.exists()){

            //Checks if the file exists. will not add anything if the file does exist.
        }else{
            try{
                File texting = new File("emp.txt");
                writerEmp = new BufferedWriter(new FileWriter(texting));
                writerEmp.write("message");
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        loginEmp.addActionListener(e -> {
            try {
                File file = new File("emp.txt");
                Scanner scan = new Scanner(file);

                String usertxt = " ";
                String passtxt = " ";
                String userGUI = userText.getText();
                String passGUI = passText.getText();


                while (scan.hasNext()) {
                    usertxt = scan.nextLine();
                    passtxt = scan.nextLine();
                    if(userGUI.equals(usertxt) && passGUI.equals(passtxt)) {
                        delivery.setEmployee(new Employee());
                        break;
                    }

                }

                if(userGUI.equals(usertxt) && passGUI.equals(passtxt)) {
                    //nothing happens

                }
                else
                if(userGUI.equals("") && passGUI.equals("")){
                    JOptionPane.showMessageDialog(null,"Please insert Username and Password");
                }
                else {

                    JOptionPane.showMessageDialog(null,"Wrong Username / Password");
                    userText.setText("");
                    passText.setText("");
                    userText.requestFocus();
                }
            } catch (IOException d) {
                d.printStackTrace();
            }

        });
        newUser.addActionListener(e -> {
            new NewUser(delivery);
            dispose();

        });
    }
    private void defineWindowListener(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Serializator.serialize(delivery);
                super.windowClosing(e);
            }
        });
    }
}
