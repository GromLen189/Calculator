package by.bsuir.tritpo.control;

import by.bsuir.tritpo.matrix.MatrixCalculator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Controller {

    @FXML Button matrix, binary, converter;
    @FXML Pane workSpace;

    private EventHandler<ActionEvent> matrixHandler, binaryHandler, converterHandler;

    @FXML
    public void initialize(){
        MatrixCalculator matr = new MatrixCalculator();
        int[][] m1 = {{1,2,3}, {4,5,6}, {7,8,9}};
        m1 = matr.mul(m1, m1);
    }

    private void initMatrixHandler() {
        matrixHandler = e-> {
            /*try {
                workSpace.getChildren().set(getClass().getResourceAsStream("matrix1.fxml"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }*/
        };
        matrix.setOnAction(matrixHandler);
    }

    private void initBinaryHandler() {
        binaryHandler = e-> {
            /*try {
                workSpace.getChildren().set(getClass().getResourceAsStream("matrix1.fxml"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }*/
        };
        binary.setOnAction(binaryHandler);
    }

    private void initConverterHandler() {
        converterHandler = e-> {
            /*try {
                workSpace.getChildren().set(getClass().getResourceAsStream("converter.fxml"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }*/
        };
        converter.setOnAction(converterHandler);
    }
}
