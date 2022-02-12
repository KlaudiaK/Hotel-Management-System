package hotel.management;

import java.io.*;
import java.util.ArrayList;

public class CustomerFileDataSource {
    final String path;


    public CustomerFileDataSource(String path) {
        this.path = path;
    }

    void saveCustomerToFile(ArrayList<CustomerEntity> entities) throws IOException, ClassNotFoundException{

        ObjectOutputStream writer = null;
        try {

            writer = new ObjectOutputStream(new FileOutputStream(path));
            writer.flush();
            writer.writeObject(entities);
        } finally {
            if(writer != null){
                writer.close();
            }

        }

    }



    ArrayList<CustomerEntity> readCustomersFromFile() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = null;
        try{
            reader = new ObjectInputStream(new FileInputStream(path));
            ArrayList<CustomerEntity> entities = (ArrayList<CustomerEntity>) reader.readObject();

            return entities;
        } finally {
            if(reader != null){
                reader.close();
            }

        }

    }


}