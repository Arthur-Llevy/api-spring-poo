package br.unicap.si.poo.project.demo.Exception;

public class InvalidNumberField extends RuntimeException {
    public InvalidNumberField(String fieldName) {
        super("The field " + fieldName + " must be greater than 0");
    }
}
