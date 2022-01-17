package hotel.management;

import java.io.IOException;
import java.util.ArrayList;



public class CustomerRepository {
    final FileDataSource dataSource;

    public CustomerRepository(FileDataSource dataSource) {
        this.dataSource = dataSource;
    }

    void saveToFile(ArrayList<CustomerEntity> entities) throws CannotSaveToFileException {
        try {
            dataSource.saveToFile(entities);
        } catch (IOException e) {
            throw new CannotSaveToFileException();
        }


    }


    ArrayList<CustomerEntity> readFromFile()  {
        try {
            return dataSource.readFromFile();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<CustomerEntity>();
        }
    }
}


