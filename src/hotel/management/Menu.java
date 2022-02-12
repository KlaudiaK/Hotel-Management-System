package hotel.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    private JButton newBookingButton;
    private JButton showAllBookingsButton;
    private JPanel mainPanel;
    private JButton addNewRoomButton;
    private JButton showRoomInfoButton;
    private JButton showCustomersInfoButton;
    CustomerFileDataSource customerDataSource = new CustomerFileDataSource("booking.ser");
    RoomFileDataSource roomDataSource = new RoomFileDataSource("rooms.ser");

    CustomerRepository customerRepository = new CustomerRepository(customerDataSource);

    RoomRepository roomRepository = new RoomRepository(roomDataSource);


    public Menu(){
        setupFrame();
        addActionEvent();

    }
    private void setupFrame() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 700));
        setResizable(false);
        setVisible(true);
        newBookingButton.setIcon(new ImageIcon("newBooking.png"));
        showAllBookingsButton.setIcon(new ImageIcon("showBookings.png"));
        showRoomInfoButton.setIcon(new ImageIcon("room.png"));
        addNewRoomButton.setIcon(new ImageIcon("addRoom.png"));
        showCustomersInfoButton.setIcon(new ImageIcon("customer.png"));

    }


    public void addActionEvent() {
        newBookingButton.addActionListener(this);
        showAllBookingsButton.addActionListener(this);
        addNewRoomButton.addActionListener(this);
        showRoomInfoButton.addActionListener(this);
        showCustomersInfoButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == newBookingButton) {

            new Customer(customerRepository, roomRepository);
            setVisible(false);
            dispose();

        }
        else if (e.getSource() == showAllBookingsButton) {

            new ShowBookings(customerRepository);
            setVisible(false);
            dispose();
        }
        else if (e.getSource() == addNewRoomButton) {
            new newRoom(roomRepository);
            setVisible(false);
            dispose();
        }
        else if (e.getSource() == showRoomInfoButton) {
            new RoomInfo(roomRepository, customerRepository);
            setVisible(false);
            dispose();

        }

        else if (e.getSource() == showCustomersInfoButton) {
            new CustomerInfo( customerRepository);
            setVisible(false);
            dispose();

        }

    }


}
