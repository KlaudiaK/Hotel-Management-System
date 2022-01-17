package hotel.management;

import java.io.*;
import java.util.ArrayList;

public class FileDataSource {
    final String path;

    public FileDataSource(String path) {
        this.path = path;
    }

    void saveToFile(ArrayList<CustomerEntity> entities) throws IOException{
        ObjectOutputStream writer = null;
        try {
            writer = new ObjectOutputStream(new FileOutputStream(path, true));
            writer.flush();
            writer.writeObject(entities);
        } finally {
            if(writer != null){
                writer.close();
            }

        }


    }



    ArrayList<CustomerEntity> readFromFile() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = null;
        try{
            reader = new ObjectInputStream(new FileInputStream(path));
            ArrayList<CustomerEntity> entities = (ArrayList<CustomerEntity>) reader.readObject();
            for (CustomerEntity c: entities){
                c.print();
            }
            return entities;
        } finally {
            if(reader != null){
                reader.close();
            }

        }

    }
}