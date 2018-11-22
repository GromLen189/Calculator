package by.bsuir.tritpo.control;

import by.bsuir.tritpo.converter.NumberConverter;
import by.bsuir.tritpo.validator.ConverterValidator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class Controller {

    @FXML Button addMatrix, subMatrix, mulMatrix, divMatrix, transpMatrix, invertMatrix, mulNumMatrix;
    @FXML TextArea firstMatrix, secondMatrix, outputMatrix;
    @FXML TextField numberMulMatrix;

    @FXML Button countBinary;
    @FXML TextField firstNumberBinary, secondNumberBinary, outputBinary;
    @FXML ChoiceBox chooseOperationBinary;

    @FXML Button convertNumber;
    @FXML TextField inputConverter;
    @FXML ChoiceBox chooseFormatConverter;
    @FXML Label binaryOutput, octalOutput, decimalOutput, hexadecimalOutput;

    private EventHandler<ActionEvent> chooseFormatConverterHandler, chooseOperationBinaryHandler;
    private EventHandler<ActionEvent> addMatrixHandler, subMatrixHandler, mulMatrixHandler;
    private EventHandler<ActionEvent> divMatrixHandler, transpMatrixHandler;
    private EventHandler<ActionEvent> invertMatrixHandler, mulNumMatrixHandler;
    private EventHandler<ActionEvent> countBinaryHandler, convertNumberHandler;
    private int converterFormat = 2;

    @FXML
    public void initialize(){
        initChooseFormatConverterHandler();
        initChooseFormatConverterBox();
        initConvertNumberHandler();
    }


    private void initChooseFormatConverterBox(){
        String[] list = {"binary", "decimal", "octal", "hexadecimal"};
        for(String status : list) {
            chooseFormatConverter.getItems().add(status);
        }
        chooseFormatConverter.setValue(list[0]);
        chooseFormatConverter.setOnAction(chooseFormatConverterHandler);
    }

    private void initChooseFormatConverterHandler() {
        chooseFormatConverterHandler = e-> {
            String newFormat = (String) chooseFormatConverter.getSelectionModel().getSelectedItem();
            switch (newFormat) {
                case "binary":
                    converterFormat = 2;
                    break;
                case "octal":
                    converterFormat = 8;
                    break;
                case "decimal":
                    converterFormat = 10;
                    break;
                case "hexadecimal":
                    converterFormat = 16;
                    break;
                default:
                    converterFormat = 2;
                    break;
            }
        };
    }

    private void initConvertNumberHandler() {
        convertNumberHandler = e-> {

            ConverterValidator validator = new ConverterValidator();
            NumberConverter calculator = new NumberConverter();
            String input = inputConverter.getText();

            if (!validator.notMoreThanIntMax(input)
                || !validator.onlyValidChar(input, converterFormat)) {
                infoBox("Invalid input", "Warning");
            } else {
                setConverterResult(calculator.convert(input, converterFormat));
            }
        };
        convertNumber.setOnAction(convertNumberHandler);
    }

    private void setConverterResult(List<String> result) {
        binaryOutput.setText(result.get(0));
        octalOutput.setText(result.get(1));
        decimalOutput.setText(result.get(2));
        hexadecimalOutput.setText(result.get(3));
    }

    private static void infoBox(String infoMessage, String titleBar)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
}