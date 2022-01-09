package hotel.management;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;


public class newRoom extends JFrame {
    private JTextField roomNoTextField;
    private JTextField availabilityTextField;
    private JComboBox roomTypeComboBox;
    private JTextField priceTextField;
    private JLabel roomTypeLabel;
    private JLabel pricelabel;
    private JLabel roomNoLabel;
    private JLabel availabilityLabel;
    private JLabel newRoomLabel;
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private JButton backToMenuButton;
    private ButtonGroup availableG;


    public newRoom(){
        setupFrame();

        textFields();

        saveButton.addActionListener(e -> {
            try {
                writeToFile();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
        backToMenuButton.addActionListener(e->{
            new Menu();
            setVisible(false);
            dispose();
        });



    }

    private void setupFrame(){
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(550, 600));


        setVisible(true);

    }

    private void textFields(){
        roomNoTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        priceTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        roomTypeComboBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        availableG = new ButtonGroup();
        availableG.add(yesRadioButton);
        availableG.add(noRadioButton);
        yesRadioButton.setActionCommand("available");
        noRadioButton.setActionCommand("not available");

        backToMenuButton.setIcon(new ImageIcon("menu.png"));
    }

    private void writeToFile() throws ParseException {
        Integer roomNo = null;
        String availability = null;
        String roomType = null;
        Integer price = null;

        try{
            roomNo = Validators.getRoomNo(roomNoTextField.getText());
            try{
                availability = availableG.getSelection().getActionCommand();
            } catch (NullPointerException e) {
            throw new InvalidAvailabilityException();
        }

            roomType = Objects.requireNonNull(roomTypeComboBox.getSelectedItem()).toString();
            price = Validators.getPrice(priceTextField.getText());
        }catch (AppExceptions e){
            System.out.println(e.toString());
        }

/*
        String roomNo = roomNoTextField.getText();

        String availability = availableG.getSelection().getActionCommand();
        String roomType = Objects.requireNonNull(roomTypeComboBox.getSelectedItem()).toString();
        String price = priceTextField.getText();

 */
        if (roomNo != null && availability != null && roomType != null && price != null){
            String customerInfo = roomNo+","+ availability+","+ roomType+ "," + price+ "\n";
            try {
                FileWriter myWriter = new FileWriter("rooms.txt", true);
                myWriter.write(customerInfo +  "\n");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

    }



}
