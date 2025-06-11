package br.unicap.si.poo.project.demo.Exception;

public class MissingParameterException extends RuntimeException {
    public MissingParameterException(String parameter) {
        super("Parameter " + parameter + " is required");
    }
}
