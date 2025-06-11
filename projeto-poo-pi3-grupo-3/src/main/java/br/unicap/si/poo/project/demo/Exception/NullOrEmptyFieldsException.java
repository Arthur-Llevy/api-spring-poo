package br.unicap.si.poo.project.demo.Exception;

public class NullOrEmptyFieldsException extends RuntimeException {
    public NullOrEmptyFieldsException(String fields) {
        super("The field(s) can not be null or empty: " + fields);
    }
}
