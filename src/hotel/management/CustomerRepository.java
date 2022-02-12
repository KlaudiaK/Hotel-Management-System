package hotel.management;

import java.io.IOException;
import java.util.ArrayList;



public class CustomerRepository {
    final CustomerFileDataSource dataSource;

    public CustomerRepository(CustomerFileDataSource dataSource) {
        this.dataSource = dataSource;
    }

    void saveToFile(ArrayList<CustomerEntity> entities) throws CannotSaveToFileException {
        try {
            dataSource.saveCustomerToFile(entities);
        } catch (IOException | ClassNotFoundException e) {
            throw new CannotSaveToFileException();
        }
    }

     ArrayList<CustomerEntity> readFromFile()  {
        try {
            return dataSource.readCustomersFromFile();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<CustomerEntity>();
        }
    }
}


