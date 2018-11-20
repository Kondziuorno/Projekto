package com.wszib.SeaBattle.GridOperation;
import com.wszib.SeaBattle.FXBaseController;

public class GridHandler {

    public enum TypeOfPlayers{
        NONE,PLAYER,COMPUTER
    }

    public interface Colors{
        String GREEN = "#33FF33";
        String YELLOW = "#FFFF33";
        String RED = "#FF3333";
        String LIGHTRED = "#FF3300";
        String WHITE = "FFFFFF";
    }


    public void changeLabeledText(FXBaseController mainBoard, Integer colIndex, Integer rowIndex, String textToSetupOnLabeled, TypeOfPlayers typeOfPlayers){
        switch (typeOfPlayers){
            case NONE:
                System.out.println("None, YOU CANT READ THIS.");
                break;
            case PLAYER:
                mainBoard.getLabeled(mainBoard.getGridPanePlayer(),colIndex,rowIndex).setText(textToSetupOnLabeled);
                break;
            case COMPUTER:
                mainBoard.getLabeled(mainBoard.getGridPaneComputer(),colIndex,rowIndex).setText(textToSetupOnLabeled);
                break;
        }
    }

    public void changeLabeledColor(FXBaseController mainBoard, Integer colIndex, Integer rowIndex, String colors, TypeOfPlayers typeOfPlayers){
        switch (typeOfPlayers){
            case NONE:
                System.out.println("None, YOU CANT READ THIS.");
                break;
            case PLAYER:
                mainBoard.getLabeled(mainBoard.getGridPanePlayer(),colIndex,rowIndex).setStyle("-fx-background-color:" + colors);
                break;
            case COMPUTER:
                mainBoard.getLabeled(mainBoard.getGridPaneComputer(),colIndex,rowIndex).setStyle("-fx-background-color:" + colors);
        }
    }

    public void changeDisableButtonStatus(FXBaseController mainBoard, Integer colIndex, Integer rowIndex,boolean bValueOfButton){
        mainBoard.getLabeled(mainBoard.getGridPaneComputer(),colIndex,rowIndex).setDisable(bValueOfButton);
    }

    public void setDefaultStyleOnButton(FXBaseController mainBoard, Integer colIndex, Integer rowIndex, TypeOfPlayers typeOfPlayers){
        switch (typeOfPlayers){
            case NONE:
                System.out.println("None, YOU CANT READ THIS.");
                break;
            case PLAYER:
                mainBoard.getLabeled(mainBoard.getGridPanePlayer(),colIndex,rowIndex).setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;");
                break;
            case COMPUTER:
                mainBoard.getLabeled(mainBoard.getGridPaneComputer(),colIndex,rowIndex).setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;");
                break;
        }
    }

    public void setDefaultStyleOnlabel(FXBaseController mainBoard, Integer colIndex, Integer rowIndex, TypeOfPlayers typeOfPlayers){
        switch (typeOfPlayers){
            case NONE:
                break;
            case PLAYER:
                mainBoard.getLabeled(mainBoard.getGridPanePlayer(),colIndex,rowIndex).setStyle("-fx-background-color: D0D0D0; -fx-border-color: #9f9f9f;");
                break;
            case COMPUTER:
                mainBoard.getLabeled(mainBoard.getGridPaneComputer(),colIndex,rowIndex).setStyle("-fx-background-color: D0D0D0; -fx-border-color: #9f9f9f;");
                break;
        }
    }

}
