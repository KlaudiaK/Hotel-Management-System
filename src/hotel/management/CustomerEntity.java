package hotel.management;

import javax.swing.plaf.IconUIResource;
import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;
import java.io.Serializable;
import java.util.Date;


public class CustomerEntity implements Serializable {


    private String name;
    private String sex;
    private String IC;
    private String address;
    private String phoneNumber;
    private String email;
    private String guestsNo;
    private String roomNo;
    private String dateOfCheckOut;
    private String dateOfCheckIn;
    public CustomerEntity(String name, String sex, String IC, String address, String phoneNumber, String email, String guestsNo, String roomNo, String dateOfCheckIn, String dateOfCheckOut){
        this.name = name;
        this.sex = sex;
        this.IC = IC;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.guestsNo = guestsNo;
        this.roomNo = roomNo; 

        this.dateOfCheckIn = dateOfCheckIn;
        this.dateOfCheckOut = dateOfCheckOut;
    }

    public CustomerEntity() {

    }


    Object[] getAsRow(){

        return new Object[] {roomNo, "not available", name,IC, phoneNumber, email, dateOfCheckIn,dateOfCheckOut};
    }

    String getDateOfCheckIn(){
        return dateOfCheckIn;
    }
    String getDateOfCheckOut(){
        return dateOfCheckOut;
    }

    public void print(){
        System.out.println(name+ " " + sex);
    }

    public static final class Builder{
        private String name;
        private String sex;
        private String IC;
        private String address;
        private String phoneNumber;
        private String email;
        private String guestsNo;
        private String roomNo;
        private String dateOfCheckOut;
        private String dateOfCheckIn;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder sex(String sex) {
            this.sex = sex;
            return this;
        }

        public Builder IC(String IC) {
            this.IC = IC;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder guestsNo(String guestsNo) {
            this.guestsNo = guestsNo;
            return this;
        }

        public Builder roomNo(String roomNo) {
            this.roomNo = roomNo;
            return this;
        }

        public Builder dateOfCheckOut(String dateOfCheckOut) {
            this.dateOfCheckOut = dateOfCheckOut;
            return this;
        }

        public Builder dateOfCheckIn(String dateOfCheckIn) {
            this.dateOfCheckIn = dateOfCheckIn;
            return this;
        }

        public CustomerEntity build(){
            CustomerEntity customer = new CustomerEntity();
            customer.name = this.name;
            customer.sex = this.sex;
            customer.IC = this.IC;
            customer.address = this.address;
            customer.phoneNumber = this.phoneNumber;
            customer.email = this.email;
            customer.guestsNo = this.guestsNo;
            customer.roomNo = this.roomNo;
            customer.dateOfCheckOut = this.dateOfCheckOut;
            customer.dateOfCheckIn = this.dateOfCheckIn;
            return customer;
        }
    }
}
