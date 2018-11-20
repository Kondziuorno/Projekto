package com.wszib.SeaBattle;

import com.wszib.SeaBattle.GridOperation.FleetBuilderBase;

public class Ship{
    private FleetBuilderBase.ShipType shipType;
    private Integer shipID; // ID of next ship of same type
    private static Integer countOneCell = 0;
    private static Integer countTwoCell = 0;
    private static Integer countThreeCell = 0;
    private static Integer countFourCell = 0;

    private Integer colIndex;
    private Integer rowIndex;

    public Ship(FleetBuilderBase.ShipType shipType, Integer colIndex, Integer rowIndex){
        this.shipType = shipType;
        this.colIndex = colIndex;
        this.rowIndex = rowIndex;

        switch (shipType){
            case ONECELL:
                shipID = countOneCell++;
                break;
            case TWOCELL:
                shipID = countTwoCell++;
                break;
            case THREECELL:
                shipID = countThreeCell++;
                break;
            case FOURCELL:
                shipID = countFourCell++;
                break;
        }
    }

    public Integer getColIndex() {
        return colIndex;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public static void printAllCountOfShips(){
        System.out.println(countOneCell.toString());
        System.out.println(countTwoCell.toString());
        System.out.println(countThreeCell.toString());
        System.out.println(countFourCell.toString());
    }

    public static void setAllShipCountAt0(){
        countOneCell = 0;
        countTwoCell = 0;
        countThreeCell = 0;
        countFourCell = 0;
    }

    public FleetBuilderBase.ShipType getShipType(){
        return shipType;
    }

    public static Integer getCountOneCell(){
        return countOneCell;
    }

    public static Integer getCountTwoCell(){
        return countTwoCell;
    }

    public static Integer getCountThreeCell(){
        return countThreeCell;
    }

    public static Integer getCountFourCell(){
        return countFourCell;
    }

    public Integer getShipID(){
        return shipID;
    }
}