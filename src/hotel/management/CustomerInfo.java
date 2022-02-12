package hotel.management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CustomerInfo extends JFrame{
    private JPanel mainPanel;
    private JButton backToMenuButton;
    private JTable customersTable;
    private JComboBox ICComboBox;
    private JButton showButton;
    private JButton showAllButton;

    public final DefaultTableModel model = new DefaultTableModel();

    private final CustomerRepository repository;

    public CustomerInfo(CustomerRepository repository){

        this.repository = repository;
        setupFrame();
        readData();

        showButton.addActionListener(e -> {
            try {
                addRowToTable();
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File wasn't found.");
            }
        });
        backToMenuButton.addActionListener(e->{
            new Menu();
            setVisible(false);
            dispose();
        });
        showAllButton.addActionListener(e->{
            model.setRowCount(0);
            readData();
        });
        


    }
    private void setupFrame() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(850, 500));
        setResizable(false);

        String header[] = {"Name", "Sex", "IC/Passport", "Address", "Phone Number", "Email"};
        model.setColumnCount(6);
        customersTable.setModel(model);
        customersTable.setRowHeight(20);

        customersTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        customersTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        customersTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        customersTable.getColumnModel().getColumn(3).setPreferredWidth(190);
        customersTable.getColumnModel().getColumn(4).setPreferredWidth(90);
        customersTable.getColumnModel().getColumn(5).setPreferredWidth(100);

        for(int i=0;i<customersTable.getColumnCount();i++)
        {
            TableColumn column = customersTable.getTableHeader().getColumnModel().getColumn(i);

            column.setHeaderValue(header[i]);
        }
        Font font = new Font("sanserif", Font.PLAIN, 15);
        customersTable.getTableHeader().setFont(font);

        addRecordsToICComboBox();
        backToMenuButton.setIcon(new ImageIcon("menu.png"));
        showButton.setIcon(new ImageIcon("search.png"));
        setVisible(true);
    }

    private void readData(){
        List<CustomerEntity> entities = repository.readFromFile();
        entities.forEach(e->model.addRow(e.getCustomerAsRow()));
        sortTable();
    }

    private void sortTable(){
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        customersTable.setRowSorter(sorter);
        List<RowSorter.SortKey> sortList = new ArrayList<>();
        sortList.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortList);
    }


    private void addRowToTable() throws FileNotFoundException {
        model.setRowCount(0);

        ArrayList<CustomerEntity> entities = repository.readFromFile();

        for (CustomerEntity e : entities) {
            if ((ICComboBox.getSelectedItem().toString()).equals(e.getIC())) {
                model.addRow(e.getCustomerAsRow());
            }
        }

    }

    private void addRecordsToICComboBox(){
        ArrayList<CustomerEntity> entities = repository.readFromFile();
        for (CustomerEntity e : entities) {
            ICComboBox.addItem(e.getIC());
        }
    }
}
