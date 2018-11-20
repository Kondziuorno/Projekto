package com.wszib.SeaBattle.GridOperation;

import com.wszib.SeaBattle.FXBaseController;

public class FleetBuilderBase extends GridHandler{

    public enum ShipType{
        NULL, ONECELL,TWOCELL,THREECELL,FOURCELL
    }

    public Integer colIndex;
    public Integer rowIndex;
    public boolean bRotation = false;

    public void createShip(FXBaseController mainBoard, Integer colIndex, Integer rowIndex, ShipType type, TypeOfPlayers typeOfPlayers){
        switch (type) {
            case ONECELL:
                changeLabeledColor(mainBoard, colIndex, rowIndex, Colors.GREEN, typeOfPlayers);
                break;
            case TWOCELL:
                if(bRotation){
                    changeLabeledColor(mainBoard, colIndex, rowIndex, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard, colIndex - 1, rowIndex, Colors.GREEN, typeOfPlayers);
                }else{
                    changeLabeledColor(mainBoard, colIndex, rowIndex, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard, colIndex, rowIndex - 1, Colors.GREEN, typeOfPlayers);
                }
                break;
            case THREECELL:
                if(bRotation){
                    changeLabeledColor(mainBoard, colIndex - 1, rowIndex, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard, colIndex, rowIndex, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard, colIndex + 1, rowIndex, Colors.GREEN, typeOfPlayers);
                }else {
                    changeLabeledColor(mainBoard, colIndex, rowIndex - 1, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard, colIndex, rowIndex, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard, colIndex, rowIndex + 1, Colors.GREEN, typeOfPlayers);
                }
                break;
            case FOURCELL:
                if(bRotation){
                    changeLabeledColor(mainBoard, colIndex - 1, rowIndex, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard, colIndex, rowIndex, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard, colIndex + 1, rowIndex, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard, colIndex + 2, rowIndex, Colors.GREEN, typeOfPlayers);
                }else{
                    changeLabeledColor(mainBoard, colIndex , rowIndex - 1, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard, colIndex, rowIndex, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard, colIndex , rowIndex + 1, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard, colIndex , rowIndex + 2, Colors.GREEN, typeOfPlayers);
                }
                break;
            }
    }

    public void removeShip(FXBaseController mainBoard, Integer colIndex, Integer rowIndex, ShipType type, TypeOfPlayers typeOfPlayers){
        switch (type){
            case ONECELL:
                setDefaultStyleOnButton(mainBoard,colIndex,rowIndex,typeOfPlayers);
                break;
            case TWOCELL:
                if(bRotation){
                    setDefaultStyleOnButton(mainBoard,colIndex,rowIndex,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex - 1,rowIndex,typeOfPlayers);
                }
                else{
                    setDefaultStyleOnButton(mainBoard,colIndex,rowIndex,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex,rowIndex - 1,typeOfPlayers);
                }
                break;
            case THREECELL:
                if(bRotation){
                    setDefaultStyleOnButton(mainBoard,colIndex - 1,rowIndex,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex,rowIndex,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex + 1,rowIndex,typeOfPlayers);
                }
                else{
                    setDefaultStyleOnButton(mainBoard,colIndex,rowIndex - 1,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex,rowIndex,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex,rowIndex + 1,typeOfPlayers);
                }
                break;
            case FOURCELL:
                if(bRotation){
                    setDefaultStyleOnButton(mainBoard,colIndex - 1,rowIndex,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex,rowIndex,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex + 1,rowIndex,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex + 2,rowIndex,typeOfPlayers);
                }
                else {
                    setDefaultStyleOnButton(mainBoard,colIndex,rowIndex - 1,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex,rowIndex,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex,rowIndex + 1,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex,rowIndex + 2,typeOfPlayers);
                }
                break;
        }
    }
    public void rotateShip(FXBaseController mainBoard, Integer colIndex, Integer rowIndex, ShipType type, TypeOfPlayers typeOfPlayers){
        switch (type){
            case TWOCELL:
                if(bRotation){
                    setDefaultStyleOnButton(mainBoard,colIndex - 1,rowIndex,typeOfPlayers);
                    changeLabeledColor(mainBoard,colIndex ,rowIndex - 1,Colors.GREEN,typeOfPlayers);
                    bRotation = false;
                }else{
                    setDefaultStyleOnButton(mainBoard,colIndex ,rowIndex - 1,typeOfPlayers);
                    changeLabeledColor(mainBoard,colIndex - 1 ,rowIndex ,Colors.GREEN,typeOfPlayers);
                    bRotation = true;
                }
                break;
            case THREECELL:
                if(bRotation){
                    setDefaultStyleOnButton(mainBoard,colIndex - 1, rowIndex,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex + 1, rowIndex,typeOfPlayers);

                    changeLabeledColor(mainBoard,colIndex,rowIndex - 1, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard,colIndex,rowIndex + 1, Colors.GREEN, typeOfPlayers);
                    bRotation = false;
                }else {

                    setDefaultStyleOnButton(mainBoard,colIndex , rowIndex - 1,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex , rowIndex + 1,typeOfPlayers);

                    changeLabeledColor(mainBoard,colIndex - 1,rowIndex, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard,colIndex + 1,rowIndex, Colors.GREEN, typeOfPlayers);
                    bRotation = true;
                }
                break;
            case FOURCELL:
                if(bRotation){
                    setDefaultStyleOnButton(mainBoard,colIndex - 1, rowIndex,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex + 1, rowIndex,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex + 2, rowIndex,typeOfPlayers);

                    changeLabeledColor(mainBoard,colIndex,rowIndex - 1, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard,colIndex,rowIndex + 1, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard,colIndex,rowIndex + 2, Colors.GREEN, typeOfPlayers);
                    bRotation = false;
                }else{
                    setDefaultStyleOnButton(mainBoard,colIndex , rowIndex - 1,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex , rowIndex + 1,typeOfPlayers);
                    setDefaultStyleOnButton(mainBoard,colIndex , rowIndex + 2,typeOfPlayers);

                    changeLabeledColor(mainBoard,colIndex - 1,rowIndex, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard,colIndex + 1,rowIndex, Colors.GREEN, typeOfPlayers);
                    changeLabeledColor(mainBoard,colIndex + 2,rowIndex, Colors.GREEN, typeOfPlayers);
                    bRotation = true;
                }
                break;
        }
    }
}
