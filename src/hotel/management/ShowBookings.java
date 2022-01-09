package hotel.management;


import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class ShowBookings extends JFrame{
    private JPanel calendarPanel;
    private JButton showButton;
    private JPanel mainPanel;
    private JTable bookingsTable;
    private JLabel statusLabel;
    private JLabel roomNoLabel;
    private JLabel customerNameLabel;
    private JLabel ICLabel;
    private JLabel phoneNumberLabel;
    private JLabel emailLabel;
    private JLabel checkInDateLabel;
    private JLabel checkOutDateLabel;
    private JButton backToMenuButton;

    private JDateChooser dateChooser;

    public final DefaultTableModel model = new DefaultTableModel();


    public ShowBookings(){
        setupFrame();
        showButton.addActionListener(e-> {
            try {
                addRowToTable();
            } catch (FileNotFoundException | ParseException ex) {
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
        setMinimumSize(new Dimension(1000, 600));
        setResizable(false);


        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");

        calendarPanel.setSize(new Dimension(300, 50));

        calendarPanel.add(dateChooser);


        model.setColumnCount(8);
        bookingsTable.setModel(model);

        bookingsTable.getColumnModel().getColumn(0).setPreferredWidth(70);
        bookingsTable.getColumnModel().getColumn(1).setPreferredWidth(75);
        bookingsTable.getColumnModel().getColumn(2).setPreferredWidth(110);
        bookingsTable.getColumnModel().getColumn(3).setPreferredWidth(80);
        bookingsTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        bookingsTable.getColumnModel().getColumn(5).setPreferredWidth(90);
        bookingsTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        bookingsTable.getColumnModel().getColumn(7).setPreferredWidth(100);

        String header[] = {"ROOM NO.", "STATUS", "CUSTOMER NAME", "IC / PASSPORT", "PHONE NUMBER", "EMAIL", "CHECK IN DATE", "CHECK OUT DATE"};

        for(int i=0;i<bookingsTable.getColumnCount();i++)
        {
            TableColumn column = bookingsTable.getTableHeader().getColumnModel().getColumn(i);

            column.setHeaderValue(header[i]);
        }
        Font font = new Font("sanserif", Font.PLAIN, 13);
        bookingsTable.getTableHeader().setFont(font);

        backToMenuButton.setIcon(new ImageIcon("menu.png"));
        showButton.setIcon(new ImageIcon("search.png"));





        setVisible(true);

    }

    private void addRowToTable() throws FileNotFoundException, ParseException {
        model.setRowCount(0);

        Date date =  dateChooser.getDate();
       // Set<Integer> roomSet = new HashSet<Integer> ();
 /*
        String strDate = DateFormat.getDateInstance().format(dateOfCheckIn);

       // System.out.println("Date: "+ strDate);
        System.out.println(sdf.format(dateOfCheckIn));

         */
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        File bookingFile = new File("booking.txt");
        Scanner myReader = new Scanner(bookingFile);


        while (myReader.hasNextLine()) {
            String bookingInfo = myReader.nextLine();
            Object[] bookingInfoS = bookingInfo.split(",");
            for(Object s: bookingInfoS){
                System.out.println(s.toString());
            }

            Object[] finalInfo = {bookingInfoS[9], "not available",bookingInfoS[0], bookingInfoS[2], bookingInfoS[4], bookingInfoS[5],bookingInfoS[6], bookingInfoS[7]};
            String dateOfCheckIn = (String) bookingInfoS[6];
            Date dateIn=sdf.parse(dateOfCheckIn);
            String dateOfCheckOut = (String) bookingInfoS[7];
            Date dateOut=sdf.parse(dateOfCheckOut);
            if (date.after(dateIn)){
                System.out.println("Tak");
            }
            if ((date.after(dateIn) || date.equals(dateIn)) &&(date.equals(dateOut)|| date.before(dateOut)) ){
                model.addRow(finalInfo);

            }





        }
    }
    private void sortTable(){
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        bookingsTable.setRowSorter(sorter);
        List<RowSorter.SortKey> sortList = new ArrayList<>();
        sortList.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortList);


    }








}
