package hotel.management;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import com.toedter.calendar.JDateChooser;
public class Customer extends JFrame {


    private JPanel mainPanel;

    private JLabel personalDetailsLabel;
    private JLabel nameLabel;
    private JTextField nameTextField;

    private JLabel sexLabel;
    private ButtonGroup sexG;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JLabel ICLabel;
    private JTextField ICTextField;

    private JLabel contactDetailsLabel;
    private JLabel phoneNumberLabel;
    private JTextField phoneNumberTextField;
    private JLabel emailLabel;
    private JTextField emailTextField;
    private JLabel addressLabel;
    private JTextArea addressTextArea;

    private JLabel bookingDetailsLabel;
    private JLabel checkInDateLabel;
    private JLabel checkOutDateLabel;
    private JTextField dateOfCheckOutTextField;


    private JLabel daysOfStayLabel;
    private JTextField daysOfStayTextField;

    private JLabel guestsNoLabel;
    private JTextField guestsNoTextField;
    private JLabel roomNoLabel;
    private JTextField roomNoTextField;
    private JButton chooseRoomButton;

    private JDateChooser dateChooser;


    private JButton saveButton;
    private JButton cancelButton;
    private JPanel calendarPanel;
    private JLabel priceLabel;
    private JTextField priceTextField;
    private JButton backToMenuButton;
    private JTextField currentDateTextField;

    private final CustomerRepository repository;

    public Customer(CustomerRepository repository){
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
        chooseRoomButton.addActionListener(e->chooseRoom());
        daysOfStayTextField.addActionListener(e->getDateOfCheckOut());

        backToMenuButton.addActionListener(e->{
            new Menu();
            setVisible(false);
            dispose();
        });
        cancelButton.addActionListener(e->clear());

    }

    private void setupFrame(){
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 800));


        currentDateTextField.setText("Date\n" + (LocalDate.now()).toString());

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-MM-yyyy");
        calendarPanel.setSize(new Dimension(300, 50));

        calendarPanel.add(dateChooser);


        backToMenuButton.setIcon(new ImageIcon("menu.png"));

        saveButton.setIcon(new ImageIcon("save.png"));
        cancelButton.setIcon(new ImageIcon("cancel.png"));
        setVisible(true);

    }

    private void textFields(){
        nameTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        phoneNumberTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        ICTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        emailTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        daysOfStayTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        guestsNoTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        roomNoTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        dateOfCheckOutTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        priceTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        sexG = new ButtonGroup();
        sexG.add(maleRadioButton);
        sexG.add(femaleRadioButton);
        maleRadioButton.setActionCommand("male");
        femaleRadioButton.setActionCommand("female");

    }

    private void writeToFile() throws ParseException {
        String name = null;
        String sex = null;
        String IC = null;
        String address = null;
        String phoneNumber = null;
        String email = null;
        String guestsNo = null;
        String roomNo = null;
        String dateOfCheckOut = null;
        Date dateOfCheckIn = null;
        try {
            name = Validators.getName(nameTextField.getText());
            try {
                sex = sexG.getSelection().getActionCommand();
            } catch (NullPointerException e) {
                throw new InvalidSexException();
            }
            IC = Validators.getIC(ICTextField.getText());
            address = addressTextArea.getText();
            phoneNumber = Validators.getPhoneNumber(phoneNumberTextField.getText());
            email = Validators.getEmail(emailTextField.getText());
            dateOfCheckIn = dateChooser.getDate();

            dateOfCheckOut = dateOfCheckOutTextField.getText();
            guestsNo = String.valueOf(Validators.getGuestsNo(guestsNoTextField.getText()));
            roomNo = String.valueOf(Validators.getDaysOfStay(roomNoTextField.getText()));
        } catch (AppExceptions e) {
            System.out.println(e.toString());
        }


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateOfCheckInStr = sdf.format(dateOfCheckIn);


        if (name != null && sex != null && IC != null && address != null && phoneNumber != null && email != null && guestsNo != null && roomNo != null && dateOfCheckOut != null && dateOfCheckInStr != null){

            saveData();
/*
            String customerInfo = name + "," + sex + "," + IC + "," + address + "," + phoneNumber + "," + email + "," + dateOfCheckInStr + "," + dateOfCheckOut + "," + guestsNo + "," + roomNo + "\n";
            try {
                FileWriter myWriter = new FileWriter("booking.txt", true);
                myWriter.write(customerInfo + "\n");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

 */


        }

    }

    private void chooseRoom() {
        RoomInfo roomInfo = new RoomInfo();
        //TODO check availability of room
        roomInfo.SelectButton.addActionListener(e-> {

            String roomNo = null;

            if (roomInfo.roomsTable.getSelectedRow() != -1) {

                roomNo = roomInfo.model.getValueAt(roomInfo.roomsTable.getSelectedRow(), 0).toString();
                String price = roomInfo.model.getValueAt(roomInfo.roomsTable.getSelectedRow(), 3).toString();

                int priceInt = Integer.parseInt(price);
                priceInt *= Integer.parseInt(daysOfStayTextField.getText());
                priceTextField.setText(String.valueOf(priceInt));
            }
            String finalRoomNo = roomNo;
            roomNoTextField.setText(finalRoomNo);
            //TODO change availability of room
            try {
                changeAvailability(finalRoomNo);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            roomInfo.setVisible(false);
            roomInfo.dispose();
        });



    }

    private void changeAvailability(String roomNo) throws IOException {
        File myObj = new File("rooms.txt");
        Scanner myReader = new Scanner(myObj);

        ArrayList<String> finalRoomInfo = new ArrayList<String>();

        while (myReader.hasNextLine()) {
            String roomsInfo = myReader.nextLine();
            String[] roomsInfoS = roomsInfo.split(",");
            System.out.println("Rooms info"+roomsInfoS[0].toString());
            if ((roomsInfoS[0].toString()).equals(roomNo)){

                roomsInfoS[1] = "not available";
                String str = String.join(",", roomsInfoS);
                System.out.println("Rooms info "+str);
                finalRoomInfo.add(str);
            }
            else finalRoomInfo.add(roomsInfo);

        }
        FileWriter writer = new FileWriter("rooms.txt");

        for (String s: finalRoomInfo){
            writer.write(s+"\n");
        }

        writer.close();
    }

    private void getDateOfCheckOut(){
        Date dateOfCheckIn =  dateChooser.getDate();


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = DateFormat.getDateInstance().format(dateOfCheckIn);

        System.out.println("Date: "+ strDate);
        int daysOfStay = Integer.parseInt(daysOfStayTextField.getText());

        Calendar c = Calendar.getInstance();
        c.setTime(dateOfCheckIn);

        c.add(Calendar.DAY_OF_MONTH, daysOfStay);

        dateOfCheckOutTextField.setText(sdf.format(c.getTime()));
    }

    private void clear(){
        nameTextField.setText("");
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        ICTextField.setText("");
        addressTextArea.setText("");
        phoneNumberTextField.setText("");
        emailTextField.setText("");
        dateChooser.setCalendar(null);
        daysOfStayTextField.setText("");
        dateOfCheckOutTextField.setText("");
        guestsNoTextField.setText("");
        roomNoTextField.setText("");
        priceTextField.setText("");

    }

    private void saveData(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateOfCheckInStr = sdf.format(dateChooser.getDate());
        ArrayList<CustomerEntity> entities = new ArrayList<CustomerEntity>();

        CustomerEntity newCustomer = new CustomerEntity.Builder()
                .name(nameTextField.getText())
                .sex(sexG.getSelection().getActionCommand())
                .IC(ICTextField.getText())
                .address(addressTextArea.getText().replaceAll("\n", " "))
                .phoneNumber(phoneNumberTextField.getText())
                .email(emailTextField.getText())
                .guestsNo(guestsNoTextField.getText())
                .roomNo(roomNoTextField.getText())
                .dateOfCheckIn(dateOfCheckInStr)
                .dateOfCheckOut(dateOfCheckOutTextField.getText())
                .build();

        entities.add(newCustomer);
        try {
            repository.saveToFile(entities);
        } catch (CannotSaveToFileException e) {
            JOptionPane.showMessageDialog(this, e.toString());
        }


    }

}
