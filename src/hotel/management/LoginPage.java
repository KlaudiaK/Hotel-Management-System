package hotel.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoginPage extends JFrame implements ActionListener {


    private JTextField userTextField;
    private JPasswordField passwordField;
    private JCheckBox showPassword;
    private JButton loginButton;
    private JButton resetButton;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JPanel mainPanel;


    public LoginPage(){
        setupFrame();
        addActionEvent();
    }


    private void setupFrame() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(550, 500));



        setVisible(true);
    }
    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();


            try {
                boolean check = false;
                File myObj = new File("login.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine() && !check) {
                    String data = myReader.nextLine();
                    String[] checkData = data.split(",");
                    if (userText.equals(checkData[0]) && pwdText.equals(checkData[1])) {
                        JOptionPane.showMessageDialog(this, "Login Successful");
                        check = true;
                        System.out.println(data);
                    }

                }
                if (!check)
                {
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                }
                else {
                    setVisible(false);
                    dispose();
                    new Menu();
                }

                myReader.close();
            } catch (FileNotFoundException ex) {
                System.out.println("An error occurred.");
                ex.printStackTrace();
            }



        }

        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }

        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }
    }
}
