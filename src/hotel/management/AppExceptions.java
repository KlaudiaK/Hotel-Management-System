package hotel.management;

abstract class AppExceptions extends Exception{};

class InvalidNameFormatException extends AppExceptions{
    @Override
    public String toString(){
        return "Wrong name format.";
    }
}

class InvalidPhoneNumberException extends AppExceptions{
    @Override
    public String toString(){
        return "Wrong phone number format.";
    }
}

class InvalidSexException extends AppExceptions{
    @Override
    public String toString(){
        return "Check sex.";
    }
}

class InvalidEmailException extends AppExceptions{
    @Override
    public String toString(){
        return "Wrong email format.";
    }
}

class InvalidICException extends AppExceptions{
    @Override
    public String toString(){
        return "Wrong IC/Passport format.";
    }
}

class InvalidGuestsNoFormatException extends AppExceptions{
    @Override
    public String toString(){
        return "Wrong guests No. format.";
    }
}

class InvalidDaysOfStayFormatException extends AppExceptions{
    @Override
    public String toString(){
        return "Wrong days of stay format.";
    }
}
class InvalidAvailabilityException extends AppExceptions{
    @Override
    public String toString(){
        return "Check availability.";
    }
}
class InvalidPriceFormatException extends AppExceptions{
    @Override
    public  String toString(){
        return "Wrong price format.";
    }
}

class InvalidRoomNoFormatException extends AppExceptions{
    @Override
    public  String toString(){
        return "Wrong room No. format.";
    }
}
class CannotSaveToFileException extends AppExceptions{
    @Override
    public  String toString(){
        return "Cannot save to file.";
    }
}