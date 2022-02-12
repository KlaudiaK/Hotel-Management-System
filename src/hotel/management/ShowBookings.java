package hotel.management;


import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
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
    private JButton backToMenuButton;
    private JButton showAllButton;
    private JDateChooser dateChooser;
    public final DefaultTableModel model = new DefaultTableModel();
    private final CustomerRepository repository;


    public ShowBookings(CustomerRepository repository){
        this.repository = repository;
        setupFrame();
        readData();

        showButton.addActionListener(e-> {
            try {
                addRowToTable();
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File wasn't found.");
            }
            catch (ParseException ex){
                JOptionPane.showMessageDialog(this, "Error when parsing date.");
            }
        });

        showAllButton.addActionListener(e->{
            model.setRowCount(0);
            readData();
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

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<CustomerEntity> entities = repository.readFromFile();

        for (CustomerEntity e : entities) {

            String dateOfCheckIn = e.getDateOfCheckIn();
            Date dateIn = sdf.parse(dateOfCheckIn);
            String dateOfCheckOut = e.getDateOfCheckOut();
            Date dateOut = sdf.parse(dateOfCheckOut);

            if ((date.after(dateIn) || date.equals(dateIn)) && (date.equals(dateOut) || date.before(dateOut))) {
                model.addRow(e.getAsRow());

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


    private void readData(){
        List<CustomerEntity> entities = repository.readFromFile();
        entities.forEach(e-> model.addRow(e.getAsRow()));
        sortTable();

    }



}
