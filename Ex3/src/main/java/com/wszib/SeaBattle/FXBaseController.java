package com.wszib.SeaBattle;

import javafx.scene.control.Labeled;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public abstract class FXBaseController {


    public abstract GridPane getGridPaneComputer();
    public abstract GridPane getGridPanePlayer();

    public abstract Labeled getLabeled(GridPane gridPane, Integer colIndex, Integer rowIndex);

    protected void changeVBoxColor(VBox vbox, String colors){
        vbox.setStyle(("-fx-background-color:" + colors));
    }
    protected void changeVBoxColorToDefault(VBox vbox){
        vbox.setStyle("-fx-background-color: transparent");

    }
    protected boolean isInRangeOfArraySides(Integer colIndex, Integer rowIndex){
        return  rowIndex > -1 &&    // Top
                rowIndex < 10 &&    // Bottom
                colIndex > -1 &&    // Right
                colIndex < 10;      // Left
    }

}
