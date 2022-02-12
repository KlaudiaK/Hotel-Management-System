package hotel.management;

import java.io.*;
import java.util.ArrayList;

public class RoomFileDataSource {

    final String path;

    public RoomFileDataSource(String path) {
        this.path = path;
    }

    void saveRoomToFile(ArrayList<RoomEntity> entities) throws IOException, ClassNotFoundException{

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

    ArrayList<RoomEntity> readRoomsFromFile() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = null;
        try{
            reader = new ObjectInputStream(new FileInputStream(path));
            ArrayList<RoomEntity> entities = (ArrayList<RoomEntity>) reader.readObject();

            return entities;
        } finally {
            if(reader != null){
                reader.close();
            }

        }

    }
}
