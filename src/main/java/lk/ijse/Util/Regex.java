package lk.ijse.Util;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Paint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFieldValid(TextField textField, String text) {
        String field = "";

        switch (textField) {
            case NAME:
                field = "^[A-Z][a-zA-Z]*$";
                break;
            case ADDRESS:
                field = "^[a-zA-Z]+$\n";
                break;
            case CONTACT:
                field = "^\\d{10}$";
                break;
            case UNIT_PRICE:
                field = "^\\d+\\.\\d{5}$\n";
                break;
            case QTY:
                field = "^-?\\d+$\n";
                break;
            case DESC:
                field = "^[a-zA-Z]+$\n";
        }

        Pattern pattern = Pattern.compile(field);

        if (text != null) {
            if (text.trim().isEmpty()) {
                return false;
            }
        } else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean setTextColor(TextField address, JFXTextField textField){
        if (Regex.isTextFieldValid(address, textField.getText())){
            textField.setFocusColor(Paint.valueOf("Green"));
            textField.setUnFocusColor(Paint.valueOf("Green"));
            return true;
        }else {
            textField.setFocusColor(Paint.valueOf("Red"));
            textField.setUnFocusColor(Paint.valueOf("Red"));
            return false;
        }
    }

}
