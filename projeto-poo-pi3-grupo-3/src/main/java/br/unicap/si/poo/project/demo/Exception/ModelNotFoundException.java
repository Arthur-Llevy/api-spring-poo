package br.unicap.si.poo.project.demo.Exception;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String modelName) {
        super(modelName + " not found with this id");
    }
}
