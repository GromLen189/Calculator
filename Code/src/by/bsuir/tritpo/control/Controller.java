package by.bsuir.tritpo.control;

import by.bsuir.tritpo.binary.BinaryCalculator;
import by.bsuir.tritpo.converter.NumberConverter;
import by.bsuir.tritpo.exception.CustomException;
import by.bsuir.tritpo.matrix.MatrixCalculator;
import by.bsuir.tritpo.parser.MatrixParser;
import by.bsuir.tritpo.validator.BinaryValidator;
import by.bsuir.tritpo.validator.ConverterValidator;
import by.bsuir.tritpo.validator.MatrixValidator;
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

    private EventHandler<ActionEvent> chooseFormatConverterHandler;
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

        initChooseOperationBinaryBox();
        initCountBinaryHandler();

        initAddMatrixHandler();
        initSubMatrixHandler();
        initMulMatrixHandler();
        initTranspMatrixHandler();
        initMulNumMatrixHandler();
        initInvertMatrixHandler();
        initDivMatrixHandler();
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

            if (!validator.notMoreThanIntMax(input, converterFormat)) {
                infoBox("Too big number input (> 2147483647 dec)", "Warning");
            } else {
                if (!validator.onlyValidChar(input, converterFormat)) {
                    infoBox("Invalid simbols in input", "Warning");
                } else {
                    setConverterResult(calculator.convert(input, converterFormat));
                }
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

    private void initChooseOperationBinaryBox(){
        String[] list = {"and", "or", "xor", "not a"};
        for(String status : list) {
            chooseOperationBinary.getItems().add(status);
        }
        chooseOperationBinary.setValue(list[0]);
    }

    private void initCountBinaryHandler() {
        countBinaryHandler = e-> {

           BinaryValidator validator = new BinaryValidator();
            BinaryCalculator calculator = new BinaryCalculator();
            String number1 = firstNumberBinary.getText();
            String number2 = secondNumberBinary.getText();

            if (!validator.onlyValidChar(number1)) {
                infoBox("Invalid input characters first number", "Warning");
            } else {
                if(!validator.onlyValidChar(number2)) {
                    infoBox("Invalid input characters first number", "Warning");
                } else {
                    int first = Integer.parseInt(number1, 2);
                    int second = Integer.parseInt(number2, 2);
                    switch ((String) chooseOperationBinary.getSelectionModel().getSelectedItem()) {
                        case "and":
                            outputBinary.setText(calculator.and(first, second));
                            break;
                        case "or":
                            outputBinary.setText(calculator.or(first, second));
                            break;
                        case "xor":
                            outputBinary.setText(calculator.xor(first, second));
                            break;
                        case "not a":
                            outputBinary.setText(calculator.not(first));
                            break;
                    }
                }
            }
        };
        countBinary.setOnAction(countBinaryHandler);
    }

    private void initAddMatrixHandler() {
        addMatrixHandler = e-> {

            MatrixValidator validator = new MatrixValidator();
            MatrixCalculator calculator = new MatrixCalculator();
            MatrixParser parser = new MatrixParser();

            try {
                double[][] m1 = parser.getMatrix(firstMatrix.getText());
                double[][] m2 = parser.getMatrix(secondMatrix.getText());
                if(!validator.sameSize(m1, m2)) {
                    infoBox("Invalid input. Matrix are not same sized", "Warning");
                } else {
                    outputMatrix.setText(parser.matrixToString(calculator.add(m1, m2)));
                }
            } catch (CustomException e1) {
                infoBox("Invalid input", "Warning");
            }
        };
        addMatrix.setOnAction(addMatrixHandler);
    }

    private void initSubMatrixHandler() {
        subMatrixHandler = e-> {

            MatrixValidator validator = new MatrixValidator();
            MatrixCalculator calculator = new MatrixCalculator();
            MatrixParser parser = new MatrixParser();

            try {
                double[][] m1 = parser.getMatrix(firstMatrix.getText());
                double[][] m2 = parser.getMatrix(secondMatrix.getText());
                if(!validator.sameSize(m1, m2)) {
                    infoBox("Invalid input. Matrix are not same sized", "Warning");
                } else {
                    outputMatrix.setText(parser.matrixToString(calculator.sub(m1, m2)));
                }
            } catch (CustomException e1) {
                infoBox("Invalid input", "Warning");
            }
        };
        subMatrix.setOnAction(subMatrixHandler);
    }

    private void initMulMatrixHandler() {
        mulMatrixHandler = e-> {

            MatrixValidator validator = new MatrixValidator();
            MatrixCalculator calculator = new MatrixCalculator();
            MatrixParser parser = new MatrixParser();

            try {
                double[][] m1 = parser.getMatrix(firstMatrix.getText());
                double[][] m2 = parser.getMatrix(secondMatrix.getText());
                if(!validator.firstRowNumEqualsSecondColNum(m1, m2)) {
                    infoBox("Invalid input. M1 row number != M2 column number", "Warning");
                } else {
                    outputMatrix.setText(parser.matrixToString(calculator.mul(m1, m2)));
                }
            } catch (CustomException e1) {
                infoBox("Invalid input", "Warning");
            }
        };
        mulMatrix.setOnAction(mulMatrixHandler);
    }

    private void initTranspMatrixHandler() {
        transpMatrixHandler = e-> {

            MatrixCalculator calculator = new MatrixCalculator();
            MatrixParser parser = new MatrixParser();

            try {
                double[][] m1 = parser.getMatrix(firstMatrix.getText());
                    outputMatrix.setText(parser.matrixToString(calculator.transpon(m1)));
            } catch (CustomException e1) {
                infoBox("Invalid input", "Warning");
            }
        };
        transpMatrix.setOnAction(transpMatrixHandler);
    }

    private void initMulNumMatrixHandler() {
        mulNumMatrixHandler = e-> {

            MatrixValidator validator = new MatrixValidator();
            MatrixCalculator calculator = new MatrixCalculator();
            MatrixParser parser = new MatrixParser();

            try {
                double[][] m1 = parser.getMatrix(firstMatrix.getText());
                String number = numberMulMatrix.getText().trim();
                if(number.isEmpty() || !validator.rigthtMulNumber(number)) {
                    infoBox("Invalid input N", "Warning");
                } else {
                    outputMatrix.setText(parser.matrixToString(calculator.mulNum(m1,
                                                                Double.valueOf(number))));
                }
            } catch (CustomException e1) {
                infoBox("Invalid input", "Warning");
            }
        };
        mulNumMatrix.setOnAction( mulNumMatrixHandler);
    }

    private void initInvertMatrixHandler() {
        invertMatrixHandler = e-> {

            MatrixCalculator calculator = new MatrixCalculator();
            MatrixParser parser = new MatrixParser();

            try {
                double[][] m1 = parser.getMatrix(firstMatrix.getText());
                outputMatrix.setText(parser.matrixToString(calculator.invert(m1)));
            } catch (CustomException e1) {
                infoBox("Invalid input", "Warning");
            }
        };
        invertMatrix.setOnAction(invertMatrixHandler);
    }

    private void initDivMatrixHandler() {
        divMatrixHandler = e-> {

            MatrixValidator validator = new MatrixValidator();
            MatrixCalculator calculator = new MatrixCalculator();
            MatrixParser parser = new MatrixParser();

            try {
                double[][] m1 = parser.getMatrix(firstMatrix.getText());
                double[][] m2 = parser.getMatrix(secondMatrix.getText());
                if(!validator.firstRowNumEqualsSecondColNum(m1, m2)) {
                    infoBox("Invalid input. M1 row number != M2 column number", "Warning");
                } else {
                    if(!validator.isSquare(m2)){
                        infoBox("Invalid input. Matrix is not square", "Warning");
                    } else {
                        outputMatrix.setText(parser.matrixToString(calculator.div(m1, m2)));
                    }
                }
            } catch (CustomException e1) {
                infoBox("Invalid input", "Warning");
            }
        };
        divMatrix.setOnAction(divMatrixHandler);
    }

    private static void infoBox(String infoMessage, String titleBar)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
}
