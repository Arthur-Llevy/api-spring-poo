package br.unicap.si.poo.project.demo.Exception;

public class InvalidIdParameter extends RuntimeException {
    public InvalidIdParameter() {
        super("Id must be a valid number");
    }
}
