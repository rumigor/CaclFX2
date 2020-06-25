package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.CharArrayReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    @FXML
    public Button btn1;
    @FXML
    public Button btn2;
    @FXML
    public Button btn3;
    @FXML
    public Button btn4;
    @FXML
    public Button btn5;
    @FXML
    public Button btn6;
    @FXML
    public Button btn7;
    @FXML
    public Button btn8;
    @FXML
    public Button btn9;
    @FXML
    public Button btn0;
    @FXML
    public Button btnComma;
    @FXML
    public Button btnPlus;
    @FXML
    public Button btnMinus;
    @FXML
    public Button btnMultiply;
    @FXML
    public Button btnDivision;
    @FXML
    public Button btnSQRT;
    @FXML
    public Button btnEquation;
    @FXML
    public Button btnClear;
    @FXML
    public TextField textField;
    ArrayList <Double> numbers = new ArrayList<>();
    ArrayList<String> opers = new ArrayList<>();
    double result;


    public void getSQRT(ActionEvent actionEvent) {
        getResult();
        textField.clear();
        result = Math.sqrt(result);
        textField.setText(Double.toString(result));
    }

    public void getResult() {
        String[] operands = textField.getText().split("[-+X//]");
        for (String s : operands) {
            try {
            numbers.add(Double.parseDouble(s));
            } catch (NumberFormatException e) {
                textField.clear();
                textField.setText("Неправильный формат выражения!");
            }
        }
        if (textField.getText().charAt(0) == '-') {
            String[] operators = textField.getText().split("\\d+");
            if (operators.length == 0) result = numbers.get(0);
            else {
                opers.addAll(Arrays.asList(operators));
                opers.remove(0);
                opers.removeIf(x -> x.equals("."));
            }
        }
        String[] operators = textField.getText().split("\\d+");
        if (operators.length == 0) result = numbers.get(0);
        else {
            opers.addAll(Arrays.asList(operators));
            opers.remove(0);
            opers.removeIf(x -> x.equals("."));
            if (textField.getText().charAt(0) == '-') {
                numbers.set(0, numbers.get(0)*(-1));
                opers.remove(0);
            }
//            for (int i = 0; i < opers.size()-1; i++) {
//                if (opers.g)
//            }
            result = numbers.get(0);
            for (int i = 0; i < opers.size(); i++) {
                result = equation(opers.get(i), result, i);
            }
            opers.clear();
            numbers.clear();
            textField.clear();
            textField.setText(Double.toString(result));
        }
    }

    public double equation(String sign, double result, int index) {
        switch (sign) {
            case "+": result += numbers.get(index+1);
                break;
            case "-": result -= numbers.get(index+1);
                break;
            case "X": result *= numbers.get(index+1);
                break;
            case "/": result /= numbers.get(index+1);


        }
        return result;
    }

    public void addComma(ActionEvent actionEvent) {
        String num = textField.getText();

        textField.appendText(btnComma.getText());
    }


    public void clearTextArea(ActionEvent actionEvent) {
        textField.clear();
    }

    public void exitClick(ActionEvent actionEvent) {
        Stage stage = (Stage) textField.getScene().getWindow();
        stage.close();
    }

    public void unsupported(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Внимание!");
        alert.setHeaderText("Сообщение системы: ");
        alert.setContentText("К сожалению, функция пока не работает!");

        alert.showAndWait();
    }

    public void addText(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        textField.appendText(btn.getText());
    }
}
