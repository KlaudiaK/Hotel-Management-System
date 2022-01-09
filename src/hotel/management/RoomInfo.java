package hotel.management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class RoomInfo extends JFrame{


    private JPanel mainPanel;
    private JRadioButton onlyAvailableRadioButton;
    public JTable roomsTable;
    private JComboBox roomTypeComboBox;
    private JButton showButton;
    public JButton SelectButton;
    private JButton backToMenuButton;
    public final DefaultTableModel model = new DefaultTableModel();

    public RoomInfo(){
        setupFrame();



        showButton.addActionListener(e -> {
            try {
                addRowToTable();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        backToMenuButton.addActionListener(e->{
            new Menu();
            setVisible(false);
            dispose();
        });

    }
    private void setupFrame() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(700, 600));

        String header[] = {"Room No.", "Availability", "Room Type", "Price"};
        model.setColumnCount(4);
        roomsTable.setModel(model);
        for(int i=0;i<roomsTable.getColumnCount();i++)
        {
            TableColumn column = roomsTable.getTableHeader().getColumnModel().getColumn(i);

            column.setHeaderValue(header[i]);
        }
        Font font = new Font("sanserif", Font.PLAIN, 15);
        roomsTable.getTableHeader().setFont(font);
        try {
            showRecords();
        } catch (FileNotFoundException e) {
            System.out.println("Error when opening file.");
        }


        backToMenuButton.setIcon(new ImageIcon("menu.png"));
        setVisible(true);
    }




    private void addRowToTable() throws FileNotFoundException {
        model.setRowCount(0);
        File myObj = new File("rooms.txt");
        Scanner myReader = new Scanner(myObj);

        String roomType = (Objects.requireNonNull(roomTypeComboBox.getSelectedItem())).toString();
        if (onlyAvailableRadioButton.isSelected()) {
            while (myReader.hasNextLine()) {
                String roomsInfo = myReader.nextLine();
                String[] roomsInfoS = roomsInfo.split(",");
                if (roomsInfoS[1].equals("available") && roomsInfoS[2].equals(roomType) ){
                    model.addRow(roomsInfoS);

                }

            }
        }
        else{
            while (myReader.hasNextLine()) {
                String roomsInfo = myReader.nextLine();
                String[] roomsInfoS = roomsInfo.split(",");
                if (roomsInfoS[2].equals(roomType) ){
                    model.addRow(roomsInfoS);
                }

            }
        }

    }

    private void showRecords() throws FileNotFoundException {

        File myObj = new File("rooms.txt");
        Scanner myReader = new Scanner(myObj);

        while (myReader.hasNextLine()) {
            String roomsInfo = myReader.nextLine();
            String[] roomsInfoS = roomsInfo.split(",");

            model.addRow(roomsInfoS);



        }
    }



}
