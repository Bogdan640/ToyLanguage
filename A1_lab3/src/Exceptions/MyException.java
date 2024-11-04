package Exceptions;

public class MyException extends Exception {
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    public MyException(String basemessage, String additionalMessage) {
        super(RED+"ERROR: "+basemessage+(additionalMessage!=null ? ": "+additionalMessage: "") + RESET);
    }




}
