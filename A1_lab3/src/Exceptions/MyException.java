package Exceptions;

public class MyException extends Exception {
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";
    public MyException(String basemessage, String additionalMessage) {
        super(RED+"ERROR: "+basemessage+(additionalMessage!=null ? ": "+additionalMessage: "") + RESET);
    }

    public static MyException is_empty(String additionalMessage){
        return new MyException("The data structure you are trying to access is empty", additionalMessage);
    }

    public static MyException not_defined(String additionalMessage){
        return new MyException("The element you are trying to access does not exist", additionalMessage);
    }

    public static MyException exist(String additionalMessage){
        return new MyException("The element you are trying to define is already defined", additionalMessage);
    }

    public static MyException type_mismatch(String additionalMessage){
        return new MyException("There is a mismatch between the types of your parameters", additionalMessage);
    }


    public static MyException invalid_input(String additionalMessage){
        return new MyException("The input you provided is invalid",additionalMessage);
    }


}
