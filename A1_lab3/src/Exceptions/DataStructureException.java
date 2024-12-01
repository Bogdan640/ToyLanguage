package Exceptions;

public class DataStructureException extends MyException {

    public DataStructureException(String basemessage, String additionalMessage) {
        super(basemessage,additionalMessage);
    }

    public static DataStructureException data_structure_empty(String additionalMessage) {
        return new DataStructureException("The data structure is empty", additionalMessage);
    }

    public static DataStructureException defined_key(String additionalMessage) {
        return new DataStructureException("The key already exist in the dictionary", additionalMessage);
    }

    public static DataStructureException not_defined_key(String additionalMessage) {
        return new DataStructureException("The key does not exist in the dictionary", additionalMessage);
    }
    public static DataStructureException clone_problem(String additionalMessage) {
        return new DataStructureException("The clone operation failed", additionalMessage);
    }
}


