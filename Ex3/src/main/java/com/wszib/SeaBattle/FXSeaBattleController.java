package com.wszib.SeaBattle;

import com.wszib.SeaBattle.GridOperation.FleetBuilderBase;
import com.wszib.SeaBattle.GridOperation.GridHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class FXSeaBattleController extends FXBaseController{

    @FXML private MenuItem menuItemCreateFleet;

    @FXML private VBox vboxFleetStatusPlayer;
    @FXML private VBox vboxFleetStatusComputer;

    @FXML private GridPane gridPaneComputer;
    @FXML private GridPane gridPanePlayer;

    @FXML private Label labelStatusPlayer;
    @FXML private Label labelStatusComputer;

    @FXML private Label labelLeftStatus;
    @FXML private Label labelRightStatus;

    private int[][] board2DArray = new int[10][10];

    public void actionEventExit(ActionEvent e){
        Platform.exit();
    }

    public void generateRandom(ActionEvent e){

        Button source = (Button)e.getSource();
        Integer colIndex = GridPane.getColumnIndex(source.getParent());
        Integer rowIndex = GridPane.getRowIndex(source.getParent());
        System.out.printf("Mouse clicked cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());
    }

    public VBox getVboxFleetStatusPlayer(){
        return vboxFleetStatusPlayer;
    }

    public VBox getVboxFleetStatusComputer(){
        return vboxFleetStatusComputer;
    }

    public GridPane getGridPaneComputer(){
        return gridPaneComputer;
    }

    public GridPane getGridPanePlayer(){
        return gridPanePlayer;
    }

    public Label getLabelStatusPlayer() {
        return labelStatusPlayer;
    }

    public Label getLabelStatusComputer() {
        return labelStatusComputer;
    }

    public Label getLabelLeftStatus(){
        return labelLeftStatus;
    }

    public Label getLabelRightStatus(){
        return labelRightStatus;
    }

    public Labeled getLabeled(GridPane gridPane, Integer colIndex, Integer rowIndex){
        AnchorPane currentAnchorPane;
        if(gridPane.equals(gridPanePlayer)){
            currentAnchorPane = (AnchorPane)gridPane.getChildren().get((rowIndex + 1) * 11 + colIndex - (rowIndex + 2));
        }else {
            currentAnchorPane = (AnchorPane)gridPane.getChildren().get((rowIndex - 1) * 11 + colIndex - (rowIndex));
        }
        return (Labeled) currentAnchorPane.getChildren().get(0);
    }

    public void handleMenuItemCreateFleet(ActionEvent event){
        try{
            URL url = new File("src/main/resources/fxml/ui_FleetCreator.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Fleet Creator");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            System.out.println("Cant load new Window");
        }
    }

    public void checkGetButton(ActionEvent e){
    }

    private FleetBuilderBase fleetBuilderBase = new FleetBuilderBase();

    public void enteredCell(MouseEvent e){

        refresh();
    }

    public void exitedCell(MouseEvent e){

        refresh();
    }

    private void refresh(){
        refreshColorOnGrid();
        //debugPrintBoard2DArray(board2DArray);
    }

    private void refreshColorOnGrid(){
        for (int i = 0; i < board2DArray.length;i++)
        {
            for (int j = 0; j < board2DArray[i].length;j++)
            {
                if(isEvenInBoard2DArray(j,i)){
                    fleetBuilderBase.changeLabeledColor(this,i + 1,j + 1,GridHandler.Colors.GREEN, GridHandler.TypeOfPlayers.PLAYER);
                } else {
                    fleetBuilderBase.setDefaultStyleOnlabel(this,i + 1, j + 1, GridHandler.TypeOfPlayers.PLAYER);
                }
            }
        }
    }

    private void debugPrintBoard2DArray(int[][] arrayToPrint){
        for (int[] x : arrayToPrint)
        {
            for (int y : x)
            {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }

    private boolean isEvenInBoard2DArray(Integer colIndex, Integer rowIndex){
        if(isInRangeOfArraySides(colIndex,rowIndex)){
            return board2DArray[colIndex][rowIndex] % 2 == 0 && board2DArray[colIndex][rowIndex] != 0;
        }
        return false;
    }

    public void readSav1File(ActionEvent e){
        readFromTextFileToBoard2DArray("sav1.txt");
    }

    public void readSav2File(ActionEvent e){
        readFromTextFileToBoard2DArray("sav2.txt");
    }

    public void readSav3File(ActionEvent e){
        readFromTextFileToBoard2DArray("sav3.txt");
    }

    public void readSav4File(ActionEvent e){
        readFromTextFileToBoard2DArray("sav4.txt");
    }

    private void readFromTextFileToBoard2DArray(String filename){
        try {
            setBoard2DarrayTO0(board2DArray);
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = "";
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] cols = line.split(",");
                int col = 0;
                for (String c : cols) {
                    board2DArray[row][col] = Integer.parseInt(c);
                    col++;
                }
                row++;
            }
            reader.close();
            refresh();
        } catch (IOException e){
            System.out.println("File Not exist");
        }
    }

    private void setBoard2DarrayTO0(int[][] arrayToFillWith0){
        for(int i = 0; i < 10;i++){
            for(int j = 0; j < 10;j++){
                arrayToFillWith0[i][j] = 0;
            }
        }
    }


}
