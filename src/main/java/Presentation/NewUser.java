package Presentation;

import Business.DeliveryService;
import Data.Serializator;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class NewUser extends JFrame {
    DeliveryService service;
    JButton create;
    JButton back;
    JPanel newUserPanel;
    JTextField textUser;
    JLabel newUser;
    JLabel newPass;
    JTextField textPass;


    public NewUser(DeliveryService service){
        super("New Client");
        this.service = service;
        create = new JButton("Create");
        back = new JButton("Back");
        newUserPanel = new JPanel();
        textUser = new JTextField(15);
        textPass = new JPasswordField(15);
        newUser = new JLabel("Username: ");
        newPass = new JLabel("Password: ");

        setSize(300,200);
        setLocation(500,280);
        newUserPanel.setLayout (null);

        newUser.setBounds(20,28,80,20);
        newPass.setBounds(20,63,80,20);
        textUser.setBounds(100,30,150,20);
        textPass.setBounds(100,65,150,20);
        create.setBounds(110,100,80,20);
        back.setBounds(110,130,80,20);

        newUserPanel.add(back);
        newUserPanel.add(newUser);
        newUserPanel.add(newPass);
        newUserPanel.add(create);
        newUserPanel.add(textUser);
        newUserPanel.add(textPass);

        getContentPane().add(newUserPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Writer writer;
        File files = new File("newUser.txt");
        //String file1 = "newUser.txt";

        if(files.exists()){

            //Checks if the file exists. will not add anything if the file does exist.
        }else{
            try{
                File texting = new File("newUser.txt");
                writer = new BufferedWriter(new FileWriter(texting));
                writer.write("message");
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        create.addActionListener(e -> {
            try {


                File file = new File("newUser.txt");
                Scanner scan = new Scanner(file);

                FileWriter fileWrite = new FileWriter(file, true);

                String userText = " ";
                String passwordText = " ";
                String userGUI = textUser.getText();
                String passGUI = textPass.getText();
                while (scan.hasNext()) {
                    userText = scan.nextLine();
                    passwordText = scan.nextLine();
                    if(userGUI.equals(userText) && passGUI.equals(passwordText)) {
                        JOptionPane.showMessageDialog(null,"Username is already in use");
                        textUser.setText("");
                        textPass.setText("");
                        textUser.requestFocus();
                        break;
                    }
                }

                if(userGUI.equals(userText) && passGUI.equals(passwordText)) {

                }
                else if(userGUI.equals("") && passGUI.equals("")){
                    JOptionPane.showMessageDialog(null,"Please insert Username and Password");
                }
                else {
                    fileWrite.write(userGUI+"\r\n" +passGUI+ "\r\n");
                    fileWrite.close();
                    User client = new User(newUser.getText(), newPass.getText());

                    ///Serializator.serialize(client,file1);
                    JOptionPane.showMessageDialog(null,"Account has been created.");
                    dispose();
                    new LogIn(service);
                }
            } catch (IOException d) {
                d.printStackTrace();
            }

        });
        back.addActionListener(e -> {
            dispose();
            new LogIn(service);
        });
    }
}
