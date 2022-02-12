package hotel.management;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    static String getName(String name) throws  InvalidNameFormatException {

        String[] splitName = name.split(" ");
        if(splitName.length != 2 ){
            throw new InvalidNameFormatException();
        }
        else {
            if (!name.matches( "^[A-Z][a-z]{2,}(?: [A-Z][a-z]*)*$")){
                throw new InvalidNameFormatException();
            }

        }

        return name ;


    }
    static String getPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        Pattern pattern = Pattern.compile("^\\d{9}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()){
            throw new InvalidPhoneNumberException();
        }
        return phoneNumber;
    }

    static String getEmail(String email) throws InvalidEmailException {

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()){
            throw new InvalidEmailException();
        }
        return email;
    }
    static String getIC(String IC) throws InvalidICException {

        Pattern patternIC = Pattern.compile("^[A-Z]{3}\\d{6}$");
        Pattern patternPassport = Pattern.compile("^[A-Z]{2}\\d{7}$");
        Matcher matcherIC = patternIC.matcher(IC);
        Matcher matcherPassport = patternPassport.matcher(IC);
        if (!(matcherIC.matches() || matcherPassport.matches() )){
            throw new InvalidICException();
        }
        return IC;
    }

    static Integer getGuestsNo(String guestsNo) throws InvalidGuestsNoFormatException {
        try{
            Integer guestsNoInt = Integer.parseInt(guestsNo);

            return guestsNoInt;
        } catch (NumberFormatException exception){
            throw new InvalidGuestsNoFormatException();
        }

    }
    static Integer getDaysOfStay(String daysOfStay) throws InvalidDaysOfStayFormatException {
        try{
            Integer daysOfStayInt = Integer.parseInt(daysOfStay);

            return daysOfStayInt;
        } catch (NumberFormatException exception){
            throw new InvalidDaysOfStayFormatException();
        }

    }

    static Integer getPrice(String price) throws InvalidPriceFormatException {
        try{
            Integer priceInt = Integer.parseInt(price);

            return priceInt;
        } catch (NumberFormatException exception){
            throw new InvalidPriceFormatException();
        }

    }

}
