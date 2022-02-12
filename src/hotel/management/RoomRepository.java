package hotel.management;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;


public class RoomRepository {

    final RoomFileDataSource dataSource;

    public RoomRepository(RoomFileDataSource dataSource) {
        this.dataSource = dataSource;
    }

    void saveToFile(ArrayList<RoomEntity> entities) throws CannotSaveToFileException {
        try {
            dataSource.saveRoomToFile(entities);
        } catch (IOException | ClassNotFoundException e) {
            throw new CannotSaveToFileException();
        }


    }


    ArrayList<RoomEntity> readFromFile()  {
        try {
            return dataSource.readRoomsFromFile();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<RoomEntity>();
        }
    }
}
