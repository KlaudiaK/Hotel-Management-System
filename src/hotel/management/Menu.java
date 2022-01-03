package hotel.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Menu extends JFrame implements ActionListener {
    private JButton newBookingButton;
    private JButton showAllBookingsButton;
    private JPanel mainPanel;
    private JButton addNewRoomButton;
    private JButton showRoomInfoButton;
    private JLabel menuLabel;

    public Menu(){
        setupFrame();
        addActionEvent();
       // buttonDesign();


    }
    private void setupFrame() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(550, 500));
        setVisible(true);

        newBookingButton.setIcon(new ImageIcon("newBooking.png"));
        showAllBookingsButton.setIcon(new ImageIcon("showBookings.png"));
        showRoomInfoButton.setIcon(new ImageIcon("room.png"));
        addNewRoomButton.setIcon(new ImageIcon("addRoom.jpg"));

    }
    private void buttonDesign(){
        newBookingButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    }



    public void addActionEvent() {
        newBookingButton.addActionListener(this);
        showAllBookingsButton.addActionListener(this);
        addNewRoomButton.addActionListener(this);
        showRoomInfoButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == newBookingButton) {
            new Customer();
            setVisible(false);
            dispose();

        }
        else if (e.getSource() == showAllBookingsButton) {
            new ShowBookings();
            setVisible(false);
            dispose();
        }
        else if (e.getSource() == addNewRoomButton) {
            new newRoom();
            setVisible(false);
            dispose();
        }
        else if (e.getSource() == showRoomInfoButton) {
            new RoomInfo();
            setVisible(false);
            dispose();

        }

    }


    public void addMenu(){

        JMenuBar menuBar = new JMenuBar();

        // File Menu, F - Mnemonic
        JMenu goToMenu = new JMenu("Go To");
        goToMenu.setMnemonic(KeyEvent.VK_F);

        menuBar.add(goToMenu);
        // File->New, N - Mnemonic
        JMenuItem customerMenuItem = new JMenuItem("New Customer");
        customerMenuItem.addActionListener(e->new Customer());
        JMenuItem showBookingMenuItem = new JMenuItem("Show Bookings");
        showBookingMenuItem.addActionListener(e->new ShowBookings());
        goToMenu.add(customerMenuItem);
        goToMenu.add(showBookingMenuItem);

        setJMenuBar(menuBar);

    }
}
