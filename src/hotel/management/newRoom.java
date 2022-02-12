package hotel.management;

import javax.swing.*;
import java.awt.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;


public class newRoom extends JFrame {
    private JTextField roomNoTextField;
    private JTextField availabilityTextField;
    private JComboBox roomTypeComboBox;
    private JTextField priceTextField;
    private JLabel roomTypeLabel;
    private JLabel pricelabel;
    private JLabel roomNoLabel;
    private JLabel newRoomLabel;
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton backToMenuButton;



    private final RoomRepository repository;


    public newRoom(RoomRepository repository){
        this.repository = repository;
        setupFrame();
        textFields();
        saveButton.addActionListener(e -> {
            try {
                writeToFile();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });

        cancelButton.addActionListener(e->clear());
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
        setResizable(false);
        setVisible(true);

    }

    private void textFields(){
        roomNoTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        priceTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        roomTypeComboBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        backToMenuButton.setIcon(new ImageIcon("menu.png"));
        saveButton.setIcon(new ImageIcon("save.png"));
        cancelButton.setIcon(new ImageIcon("cancel.png"));
    }

    private void writeToFile() throws ParseException {
        Integer roomNo = null;
        String roomType = null;
        Integer price = null;

        try{
            roomNo = Integer.valueOf(roomNoTextField.getText());

            roomType = Objects.requireNonNull(roomTypeComboBox.getSelectedItem()).toString();
            price = Validators.getPrice(priceTextField.getText());

        }catch (AppExceptions e){
            e.toString();
        }

        if (roomNo != null  && roomType != null && price != null){
            saveData();

        }

    }

    void saveData(){
        RoomEntity newRoom = new RoomEntity(roomNoTextField.getText(), roomTypeComboBox.getSelectedItem().toString(), priceTextField.getText());
        ArrayList<RoomEntity> entities = repository.readFromFile();
        entities.add(newRoom);
        try {
            repository.saveToFile(entities);
        } catch (CannotSaveToFileException e) {
            JOptionPane.showMessageDialog(this, e.toString());
        }
        JOptionPane.showMessageDialog(this, "Saved succesfully!");
    }

    private void clear(){
        roomNoTextField.setText("");
        priceTextField.setText("");

    }

}
