package hotel.management;

import java.io.Serializable;
import java.util.ArrayList;

public class RoomEntity implements Serializable {

    final String id;
    //final ArrayList<String> notAvailableDaysList;

    public RoomEntity(String id){
        this.id = id;

    }

    Object[] getAsRow(){

        return new Object[] {};
    }
}
