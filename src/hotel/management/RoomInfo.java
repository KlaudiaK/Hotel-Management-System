package hotel.management;

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

public class RoomInfo extends JFrame{


    private JPanel mainPanel;
    public JTable roomsTable;
    private JComboBox roomTypeComboBox;
    private JButton showButton;
    public JButton SelectButton;
    private JButton backToMenuButton;
    private JButton showAllButton;
    public final DefaultTableModel model = new DefaultTableModel();
    private final RoomRepository roomRepository;
    private final CustomerRepository customerRepository;

    public RoomInfo(RoomRepository roomRepository, CustomerRepository customerRepository){
        this.roomRepository = roomRepository;
        this.customerRepository = customerRepository;
        setupFrame();
        readData();

        showButton.addActionListener(e -> {
            try {
                addRowToTable();
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File wasn't found.");
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
    private void setupFrame() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(700, 600));
        setResizable(false);

        String header[] = {"Room No.", "Room Type", "Price"};
        model.setColumnCount(3);
        roomsTable.setModel(model);
        for(int i=0;i<roomsTable.getColumnCount();i++)
        {
            TableColumn column = roomsTable.getTableHeader().getColumnModel().getColumn(i);
            column.setHeaderValue(header[i]);
        }
        Font font = new Font("sanserif", Font.PLAIN, 15);
        roomsTable.getTableHeader().setFont(font);

        showButton.setIcon(new ImageIcon("search.png"));
        backToMenuButton.setIcon(new ImageIcon("menu.png"));


        setVisible(true);
    }




    private void readData(){
        List<RoomEntity> entities = roomRepository.readFromFile();
        entities.forEach(e->model.addRow(e.getAsRow()));
        sortTable();
    }


    private void addRowToTable() throws FileNotFoundException {
        model.setRowCount(0);

        String selectedType = roomTypeComboBox.getSelectedItem().toString();
        ArrayList<RoomEntity> entities = roomRepository.readFromFile();

        for (RoomEntity e : entities) {
            String type = e.getType();
            if (type.equals(selectedType)) {
                model.addRow(e.getAsRow());

            }
        }
    }

    public void showAvailable(String dateOfCheckIn, String dateOfCheckOut) throws ParseException {

        model.setRowCount(0);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        List<CustomerEntity> entities = customerRepository.readFromFile();
        ArrayList<String> numbersNotAvailable = new ArrayList<>();
        Date dateIn = sdf.parse(dateOfCheckIn);
        Date dateOut = sdf.parse(dateOfCheckOut);

        for (CustomerEntity e : entities) {
            if(!((dateOut.before(sdf.parse(e.getDateOfCheckIn())) || dateOut.equals(sdf.parse(e.getDateOfCheckIn()))) || (dateIn.after(sdf.parse(e.getDateOfCheckOut())) || dateIn.equals(sdf.parse(e.getDateOfCheckOut()))))){
                numbersNotAvailable.add(e.getRoomNo());
            }
        }

        List<RoomEntity> roomEntities = roomRepository.readFromFile();
        roomEntities.forEach(r->{
            if(!numbersNotAvailable.contains(r.getId())) model.addRow(r.getAsRow());
        });



    }

    private void sortTable(){
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        roomsTable.setRowSorter(sorter);
        List<RowSorter.SortKey> sortList = new ArrayList<>();
        sortList.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortList);

    }


}
