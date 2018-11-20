package com.wszib.SeaBattle;

import com.wszib.SeaBattle.GridOperation.FleetBuilderBase;
import com.wszib.SeaBattle.GridOperation.FleetBuilderPlayer;
import com.wszib.SeaBattle.GridOperation.GridHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;


public class FXFleetCreatorController extends FXBaseController{

    @FXML GridPane gridPanePlayer;

    @FXML VBox length4VBox;
    @FXML VBox length3VBox;
    @FXML VBox length2VBox;
    @FXML VBox length1VBox;

    @FXML Button create4LengthButton;
    @FXML Button create3LengthButton;
    @FXML Button create2LengthButton;
    @FXML Button create1LengthButton;

    @FXML Label countLabels4;
    @FXML Label countLabels3;
    @FXML Label countLabels2;
    @FXML Label countLabels1;

    private int[][] board2DArray = new int[10][10];

    private FleetBuilderPlayer.ShipType currentCreatingShip = FleetBuilderBase.ShipType.NULL;
    private boolean isShipAppearingWhileCreating = false;

    private boolean isOneCellShipFullyCreated = false;
    private boolean isTwoCellShipFullyCreated= false;
    private boolean isThreeCellShipFullyCreated = false;
    private boolean isFourCellShipFullyCreated = false;

    public FXFleetCreatorController(){
        board2DArray = fleetBuilderPlayer.getBoard2DArray();
    }

    @Override
    public GridPane getGridPanePlayer(){
        return gridPanePlayer;
    }

    @Override
    // Never will be used
    public GridPane getGridPaneComputer(){
        return new GridPane();
    }


    private FleetBuilderPlayer fleetBuilderPlayer = new FleetBuilderPlayer();
    private Integer colIndex;
    private Integer rowIndex;

    @Override
    public Labeled getLabeled(GridPane gridPane, Integer colIndex, Integer rowIndex){
        AnchorPane currentAnchorPane;
        if(gridPane.equals(gridPanePlayer)){
            currentAnchorPane = (AnchorPane)gridPane.getChildren().get((rowIndex + 1) * 11 + colIndex - (rowIndex + 2));
        }else {
            currentAnchorPane = (AnchorPane)gridPane.getChildren().get((rowIndex - 1) * 11 + colIndex - (rowIndex));
        }
        return (Labeled) currentAnchorPane.getChildren().get(0);
    }

    public void refreshBoard(ActionEvent e){
        refresh();
    }

    private void refresh(){
        board2DArray = fleetBuilderPlayer.getBoard2DArray();
        refreshColorOnGrid();
        updateCountLabels();
    }

    public void clickedInCell(MouseEvent e){
        Button source = (Button)e.getSource();
        colIndex = GridPane.getColumnIndex(source.getParent());
        rowIndex = GridPane.getRowIndex(source.getParent());
        fleetBuilderPlayer.placeShip(this,colIndex,rowIndex,currentCreatingShip);
        isShipAppearingWhileCreating = false;
        board2DArray = fleetBuilderPlayer.getBoard2DArray();
        refresh();
    }

    private void updateCountLabels(){
        countLabels4.setText(Ship.getCountFourCell().toString());
        countLabels3.setText(Ship.getCountThreeCell().toString());
        countLabels2.setText(Ship.getCountTwoCell().toString());
        countLabels1.setText(Ship.getCountOneCell().toString());
    }

    private void setCountlabelsTo0(){
        countLabels4.setText("0");
        countLabels3.setText("0");
        countLabels2.setText("0");
        countLabels1.setText("0");
    }

    private void controlVBoxColorAndButtonDisabling(){
        if(Ship.getCountOneCell() < 4) {
            if (create1LengthButton.isDisable()) {
                create1LengthButton.setDisable(false);
                changeVBoxColorToDefault(length1VBox);
                isOneCellShipFullyCreated = false;
            }
        } else {
            changeVBoxColor(length1VBox, GridHandler.Colors.LIGHTRED);
            create1LengthButton.setDisable(true);
            isOneCellShipFullyCreated = true;
        }

        if(Ship.getCountTwoCell() < 3) {
            if (create2LengthButton.isDisable()) {
                create2LengthButton.setDisable(false);
                changeVBoxColorToDefault(length2VBox);
                isTwoCellShipFullyCreated = false;
            }
        } else {
            changeVBoxColor(length2VBox, GridHandler.Colors.LIGHTRED);
            create2LengthButton.setDisable(true);
            isTwoCellShipFullyCreated = true;
        }

        if(Ship.getCountThreeCell() < 2) {
            if (create3LengthButton.isDisable()) {
                create3LengthButton.setDisable(false);
                changeVBoxColorToDefault(length3VBox);
                isThreeCellShipFullyCreated = false;
            }
        } else {
            changeVBoxColor(length3VBox, GridHandler.Colors.LIGHTRED);
            create3LengthButton.setDisable(true);
            isThreeCellShipFullyCreated = true;
        }

        if(Ship.getCountFourCell() < 1) {
            if (create4LengthButton.isDisable()) {
                create4LengthButton.setDisable(false);
                changeVBoxColorToDefault(length4VBox);
                isFourCellShipFullyCreated = false;
            }
        } else {
            changeVBoxColor(length4VBox, GridHandler.Colors.LIGHTRED);
            create4LengthButton.setDisable(true);
            isFourCellShipFullyCreated = true;
        }

    }

    public void clearBoard(ActionEvent e){
        setBoard2DarrayTO0(board2DArray);
        fleetBuilderPlayer.initializeboard2DArray();
        setDefaultColorOnBoard();
        fleetBuilderPlayer.clearColectionOfShips();
        controlVBoxColorAndButtonDisabling();
        setCountlabelsTo0();
        refresh();
    }

    private void setBoard2DarrayTO0(int[][] arrayToFillWith0){
        for(int i = 0; i < 10;i++){
            for(int j = 0; j < 10;j++){
                arrayToFillWith0[i][j] = 0;
            }
        }
    }

    public void actionEventExit(ActionEvent e){
        Stage stage = (Stage) gridPanePlayer.getScene().getWindow();
        stage.close();
    }

    public void enteredCell(MouseEvent e){
        Button source = (Button)e.getSource();
        colIndex = GridPane.getColumnIndex(source.getParent());
        rowIndex = GridPane.getRowIndex(source.getParent());

        if(isShipAppearingWhileCreating){
            fleetBuilderPlayer.createShip(this,colIndex,rowIndex, currentCreatingShip, GridHandler.TypeOfPlayers.PLAYER);
        }
        refreshColorOnGrid();
    }

    public void exitedCell(MouseEvent e){
        Button source = (Button)e.getSource();
        Integer colIndex = GridPane.getColumnIndex(source.getParent());
        Integer rowIndex = GridPane.getRowIndex(source.getParent());
        fleetBuilderPlayer.removeShip(this,colIndex,rowIndex, currentCreatingShip, GridHandler.TypeOfPlayers.PLAYER);
        refresh();
    }

    public void rotateShip(KeyEvent e){
        if(e.getCode() == KeyCode.R){
            fleetBuilderPlayer.rotateShip(this,colIndex,rowIndex, currentCreatingShip, GridHandler.TypeOfPlayers.PLAYER);
        }
    }

    public void setSaveFileTosav1(ActionEvent e){
        if(canSave()){
            saveToFile("sav1.txt");
            createInformationMessageBox("Fleet succesfully saved.");
        }else{
            createInformationMessageBox("Can't save Fleet because not all of the ships were created.");
        }

    }
    public void setSaveFileTosav2(ActionEvent e){
        if(canSave()){
            saveToFile("sav2.txt");
            createInformationMessageBox("Fleet succesfully saved.");
        }else{
            createInformationMessageBox("Can't save Fleet because not all of the ships were created.");
        }
    }
    public void setSaveFileTosav3(ActionEvent e){
        if(canSave()){
            saveToFile("sav3.txt");
            createInformationMessageBox("Fleet succesfully saved.");
        }else{
            createInformationMessageBox("Can't save Fleet because not all of the ships were created.");
        }
    }
    public void setSaveFileTosav4(ActionEvent e){
        if(canSave()){
            saveToFile("sav4.txt");
            createInformationMessageBox("Fleet succesfully saved.");
        }else{
            createInformationMessageBox("Can't save Fleet because not all of the ships were created.");
        }
    }

    private void createInformationMessageBox(String message){
        Alert messageBox = new Alert(Alert.AlertType.INFORMATION);
        messageBox.setTitle("Information Message Box");
        messageBox.setHeaderText(null);
        messageBox.setContentText(message);
        messageBox.showAndWait();
    }

    private boolean canSave(){
        return isOneCellShipFullyCreated && isTwoCellShipFullyCreated && isThreeCellShipFullyCreated && isFourCellShipFullyCreated;
    }

    private void saveToFile(String savToSave){
        writeMatrix(savToSave);
    }

    private void writeMatrix(String filename) {
        try {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < board2DArray.length; i++)//for each row
            {
                for (int j = 0; j < board2DArray.length; j++)//for each column
                {
                    builder.append(board2DArray[i][j] + "");//append to the output string
                    if (j < board2DArray.length - 1)//if this is not the last row element
                        builder.append(",");//then add comma (if you don't like commas you can use spaces)
                }
                builder.append("\n");//append new line at the end of the row
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            ;
            writer.write(builder.toString());//save the string representation of the board
            writer.close();
        } catch (IOException e){
            //why does the catch need its own curly?
        }

    }

    public void chooseOneCellShipToCreate(MouseEvent e){

        currentCreatingShip = FleetBuilderBase.ShipType.ONECELL;
        isShipAppearingWhileCreating = true;
        System.out.println("One Cell");
        requestFocusFromButtonInGridPane();
    }

    public void destroyOneCellShip(MouseEvent e){

    }

    public void destroyAllOneCellShip(MouseEvent e){

    }

    public void chooseTwoCellShipToCreate(MouseEvent e){

        currentCreatingShip = FleetBuilderBase.ShipType.TWOCELL;
        isShipAppearingWhileCreating = true;
        System.out.println("Two Cell");
        requestFocusFromButtonInGridPane();
    }

    public void destroyTwoCellShip(MouseEvent e){

    }

    public void destroyAllTwoCellShip(MouseEvent e){

    }

    public void chooseThreeCellShipToCreate(MouseEvent e){

        currentCreatingShip = FleetBuilderBase.ShipType.THREECELL;
        isShipAppearingWhileCreating = true;
        System.out.println("Three Cell");
        requestFocusFromButtonInGridPane();
    }

    public void destroyThreeCellShip(MouseEvent e){

    }

    public void destroyAllThreeCellShip(MouseEvent e){

    }

    public void chooseFourCellShipToCreate(MouseEvent e){

        currentCreatingShip = FleetBuilderBase.ShipType.FOURCELL;
        isShipAppearingWhileCreating = true;
        System.out.println("Four Cell");
        requestFocusFromButtonInGridPane();
    }

    public void destroyFourCellShip(MouseEvent e){

    }

    public void destroyAllFourCellShip(MouseEvent e){

    }

    private void refreshColorOnGrid(){
        controlVBoxColorAndButtonDisabling();
        for (int i = 0; i < board2DArray.length;i++)
        {
            for (int j = 0; j < board2DArray[i].length;j++)
            {
                if(isEvenInBoard2DArray(j,i)){
                    fleetBuilderPlayer.changeLabeledColor(this,i + 1,j + 1, GridHandler.Colors.GREEN, GridHandler.TypeOfPlayers.PLAYER);
                }
            }
        }
    }

    private void setDefaultColorOnBoard(){
        for(int i = 0; i < board2DArray.length;i++){
            for (int j = 0; j < board2DArray[i].length; j++){
                fleetBuilderPlayer.setDefaultStyleOnButton(this,i + 1, j + 1, GridHandler.TypeOfPlayers.PLAYER);
            }
        }
    }

    private boolean isEvenInBoard2DArray(Integer colIndex, Integer rowIndex){
        if(isInRangeOfArraySides(colIndex,rowIndex)){
            return board2DArray[colIndex][rowIndex] % 2 == 0 && board2DArray[colIndex][rowIndex] != 0;
        }
        return false;
    }

    private void requestFocusFromButtonInGridPane(){
        AnchorPane currentAnchontPane = (AnchorPane)gridPanePlayer.getChildren().get(40);
        currentAnchontPane.getChildren().get(0).requestFocus();
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





}
