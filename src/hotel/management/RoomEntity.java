package hotel.management;

import java.io.Serializable;


public class RoomEntity implements Serializable {

    private String id;
    private String type;
    private String price;


    public RoomEntity(String id, String type, String price){
        this.id = id;
        this.type = type;
        this.price = price;

    }

    Object[] getAsRow(){

        return new Object[] {id, type,price};
    }

    String getType(){
        return this.type;
    }

    String getId(){
        return this.id;
    }


}
