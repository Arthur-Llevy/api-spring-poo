package br.unicap.si.poo.project.demo.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class VerifyNullAndEmptyFields<T> {
    public ArrayList<String> verify(T classToVefiry) {
        ArrayList<String> nullFieldsStack = new ArrayList<>();
        Field[] fields = classToVefiry.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(classToVefiry);

                if (field.getName().equalsIgnoreCase("id")) {
                    continue;
                }

                if (field.getName().equalsIgnoreCase("favorites")) {
                    continue;
                }

                if (field.getName().equalsIgnoreCase("users")) {
                    continue;
                }

                if (field.getName().equalsIgnoreCase("saleHistory")) {
                    continue;
                }

                if (value == null) {
                    nullFieldsStack.add(field.getName());
                } else if (value instanceof String) {
                    String strValue = (String) value;
                    if (strValue.isEmpty()) {
                        nullFieldsStack.add(field.getName());
                    }
                } else if (value instanceof Double) {
                    Double doubleValue = (Double) value;
                    if (doubleValue.toString() == null) {
                        nullFieldsStack.add(field.getName());
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return nullFieldsStack;
    }
}