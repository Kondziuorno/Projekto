package com.wszib.SeaBattle.GridOperation;

import com.wszib.SeaBattle.FXBaseController;
import com.wszib.SeaBattle.Ship;

import java.util.ArrayList;

public class FleetBuilderPlayer extends FleetBuilderBase{

    private int[][] board2DArray = new int[10][10];

    public int[][] getBoard2DArray(){
        return board2DArray;
    }

    private ArrayList<Ship> collectionOfShips = new ArrayList<>();

    public ArrayList<Ship> getCollectionOfShips(){
        return collectionOfShips;
    }

    public void clearColectionOfShips(){
        collectionOfShips.clear();
        Ship.setAllShipCountAt0();
    }

    public void removeShip(Integer colIndex, Integer rowIndex){
        for(Ship ship : collectionOfShips){
            if(ship.getColIndex().equals(colIndex) && ship.getRowIndex().equals(rowIndex)){
                collectionOfShips.remove(ship);
            }
        }
    }
    public FleetBuilderPlayer(){
        initializeboard2DArray();
    }
    public void placeShip(FXBaseController mainBoard, Integer colIndex, Integer rowIndex,ShipType shipType){
        int tempColIndex = rowIndex - 1; // Grid in UI has one more collumn and rows for labels with numbers
        int tempRowIndex = colIndex - 1;

        switch (shipType){
            case ONECELL:
                if(isOneCellShipCanBePlaced(tempColIndex,tempRowIndex)){
                    System.out.println("One cell Ship is placed");
                    Ship ship = new Ship(shipType,colIndex,rowIndex);
                    collectionOfShips.add(ship);
                }else{
                    System.out.println("One cell Ship is not placed");
                }
                break;
            case TWOCELL:
                if(isTwoCellShipCanBePlaced(tempColIndex,tempRowIndex)){
                    System.out.println("Two cell Ship is placed");
                    Ship ship = new Ship(shipType,colIndex,rowIndex);
                    collectionOfShips.add(ship);
                }else{
                    System.out.println("Two cell Ship is not placed");
                }
                break;
            case THREECELL:
                if(isThreeCellShipCanBePlaced(tempColIndex,tempRowIndex)){
                    System.out.println("Three cell Ship is placed");
                    Ship ship = new Ship(shipType,colIndex,rowIndex);
                    collectionOfShips.add(ship);
                }else{
                    System.out.println("Three cell Ship is not placed");
                }
                break;
            case FOURCELL:
                if(isFourCellShipCanBePlaced(tempColIndex,tempRowIndex)){
                    System.out.println("Four cell Ship is placed");
                    Ship ship = new Ship(shipType,colIndex,rowIndex);
                    collectionOfShips.add(ship);
                }else{
                    System.out.println("Four cell Ship is not placed");
                }
                break;
        }
    }

    // For better understanding of Ships placement look at resources/ShipPlacement.jpg

    private boolean isOneCellShipCanBePlaced(Integer colIndex, Integer rowIndex){
        if(isEvenAndNotZeroInBoard2DArray(colIndex - 1,rowIndex - 1)){         // Top Left
            return false;
        }
        if(isEvenAndNotZeroInBoard2DArray(colIndex ,rowIndex - 1)){         // 1
            return false;
        }
        if(isEvenAndNotZeroInBoard2DArray(colIndex + 1,rowIndex - 1)){         // Top Right
            return false;
        }
        if(isEvenAndNotZeroInBoard2DArray(colIndex + 1,rowIndex )){         // 2
            return false;
        }
        if(isEvenAndNotZeroInBoard2DArray(colIndex - 1,rowIndex + 1)){         // Bottom Left
            return false;
        }
        if(isEvenAndNotZeroInBoard2DArray(colIndex - 1,rowIndex)){         // 3
            return false;
        }
        if(isEvenAndNotZeroInBoard2DArray(colIndex + 1,rowIndex + 1)){        // Bottom Right
            return false;
        }
        if(isEvenAndNotZeroInBoard2DArray(colIndex,rowIndex + 1)){         // 4
            return false;
        }
        if(isEvenAndNotZeroInBoard2DArray(colIndex,rowIndex)){                // REAL SHIP
            return false;
        }
        placeInBoard2DArray(colIndex -1, rowIndex -1, ShipType.ONECELL,false); // Top Left
        placeInBoard2DArray(colIndex,rowIndex - 1,ShipType.ONECELL,false);         // 1
        placeInBoard2DArray(colIndex + 1, rowIndex -1,ShipType.ONECELL, false); // Top Right
        placeInBoard2DArray(colIndex + 1, rowIndex, ShipType.ONECELL,false);        // 2
        placeInBoard2DArray(colIndex - 1, rowIndex + 1, ShipType.ONECELL, false); // Bottom Left
        placeInBoard2DArray(colIndex - 1, rowIndex, ShipType.ONECELL,false);        // 3
        placeInBoard2DArray(colIndex + 1, rowIndex + 1, ShipType.ONECELL, false); // Bottom Right
        placeInBoard2DArray(colIndex,rowIndex + 1, ShipType.ONECELL,false);        // 4
        placeInBoard2DArray(colIndex,rowIndex,ShipType.ONECELL,true); // REAL SHIP

        return true;        // Can be placed
    }

    private boolean isTwoCellShipCanBePlaced(Integer colIndex, Integer rowIndex){
        if(isEvenAndNotZeroInBoard2DArray(colIndex,rowIndex)){                // REAL SHIP
            return false;
        }
        if(isEvenAndNotZeroInBoard2DArray(colIndex + 1,rowIndex + 1 )){        // Bottom Right
            return false;
        }
        if(bRotation){
            if(isEvenAndNotZeroInBoard2DArray(colIndex - 1,rowIndex - 2)){         // Top Left
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex,rowIndex - 2)){         // 1
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex + 1,rowIndex - 2)){         // Top Right
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex ,rowIndex + 1)){         // 6
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex - 1,rowIndex + 1)){         // Bottom Left
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex,rowIndex - 1)){            // REAL SHIP Top
                return false;
            }

            placeInBoard2DArray(colIndex - 1, rowIndex - 2, ShipType.TWOCELL,false); // Top Left
            placeInBoard2DArray(colIndex,rowIndex - 2,ShipType.TWOCELL,false);      // 1
            placeInBoard2DArray(colIndex - 1,rowIndex - 1, ShipType.TWOCELL,false); // 2
            placeInBoard2DArray(colIndex + 1, rowIndex - 2,ShipType.TWOCELL, false); // Top Right
            placeInBoard2DArray(colIndex + 1, rowIndex - 1, ShipType.TWOCELL,false); // 3
            placeInBoard2DArray(colIndex - 1, rowIndex + 1, ShipType.TWOCELL, false); // Bottom Left
            placeInBoard2DArray(colIndex - 1,rowIndex , ShipType.TWOCELL,false);    // 5
            placeInBoard2DArray(colIndex,rowIndex - 1,ShipType.TWOCELL,true); // REAL SHIP Top

        }else{
            if(isEvenAndNotZeroInBoard2DArray(colIndex - 2,rowIndex - 1)){         // Top Left
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex - 2,rowIndex )){         // 3
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex + 1,rowIndex - 1)){         // Top Right
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex + 1,rowIndex)){         // 4
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex - 2,rowIndex + 1)){         // Bottom Left
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex - 1,rowIndex)){            // REAL SHIP LEFT
                return false;
            }

            placeInBoard2DArray(colIndex - 2, rowIndex - 1, ShipType.TWOCELL,false); // Top Left
            placeInBoard2DArray(colIndex - 1,rowIndex - 1,ShipType.TWOCELL,false);  // 1
            placeInBoard2DArray(colIndex, rowIndex - 1, ShipType.TWOCELL,false);            // 2
            placeInBoard2DArray(colIndex + 1, rowIndex - 1,ShipType.TWOCELL, false); // Top Right
            placeInBoard2DArray(colIndex - 2, rowIndex, ShipType.TWOCELL,false);    // 3
            placeInBoard2DArray(colIndex - 2, rowIndex + 1, ShipType.TWOCELL, false); // Bottom Left
            placeInBoard2DArray(colIndex - 1,rowIndex + 1, ShipType.TWOCELL,false);    // 5
            placeInBoard2DArray(colIndex - 1,rowIndex,ShipType.TWOCELL,true); // REAL SHIP LEFT

        }


        placeInBoard2DArray(colIndex,rowIndex,ShipType.TWOCELL,true); // REAL SHIP
        placeInBoard2DArray(colIndex + 1, rowIndex + 1, ShipType.TWOCELL, false); // Bottom Right
        placeInBoard2DArray(colIndex + 1, rowIndex, ShipType.TWOCELL,false);    // 4
        placeInBoard2DArray(colIndex , rowIndex + 1, ShipType.TWOCELL,false);   // 6
        return true;
    }

    private boolean isThreeCellShipCanBePlaced(Integer colIndex, Integer rowIndex){
        if(isEvenAndNotZeroInBoard2DArray(colIndex,rowIndex)){            // Real Ship
            return false;
        }
        if(bRotation){
            if(isEvenAndNotZeroInBoard2DArray(colIndex - 1,rowIndex - 2)){     // Top Left
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex,rowIndex - 2)){     // 2
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex + 1,rowIndex - 2)){     // Top Right
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex - 1,rowIndex + 2)){     // Bottom Left
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex ,rowIndex + 2)){     // 7
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex + 1,rowIndex + 2)){     // Bottom Right
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex - 1,rowIndex)){        // Real Ship Top
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex + 1,rowIndex)){        // Real Ship Down
                return false;
            }
            placeInBoard2DArray(colIndex - 1, rowIndex - 2, ShipType.THREECELL,false); // Top Left
            placeInBoard2DArray(colIndex + 1, rowIndex - 2, ShipType.THREECELL,false); // Top Right
            placeInBoard2DArray(colIndex,rowIndex - 2,ShipType.THREECELL, false);   // 2
            placeInBoard2DArray(colIndex - 1, rowIndex, ShipType.THREECELL,false ); // 4
            placeInBoard2DArray(colIndex + 1, rowIndex, ShipType.THREECELL,false ); // 5
            placeInBoard2DArray(colIndex, rowIndex + 2 , ShipType.THREECELL,false ); // 7
            placeInBoard2DArray(colIndex - 1, rowIndex + 2, ShipType.THREECELL,false ); // Bottom Left
            placeInBoard2DArray(colIndex + 1, rowIndex + 2, ShipType.THREECELL,false ); // Bottom Right
            placeInBoard2DArray(colIndex,rowIndex - 1, ShipType.THREECELL,true); // Real Ship Top
            placeInBoard2DArray(colIndex,rowIndex + 1, ShipType.THREECELL,true); // Real Ship Down

        }else{
            if(isEvenAndNotZeroInBoard2DArray(colIndex - 2,rowIndex - 1)){     // Top Left
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex + 2,rowIndex - 1)){     // Top Right
                return false;
            }

            if(isEvenAndNotZeroInBoard2DArray(colIndex - 2,rowIndex )){     // 4
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex - 2,rowIndex + 1)){     // Bottom Left
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex + 2,rowIndex + 1)){     // Bottom Right
                return false;
            }

            if(isEvenAndNotZeroInBoard2DArray(colIndex + 2,rowIndex )){     // 5
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex,rowIndex- 1)){        // Real Ship Left
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex,rowIndex + 1)){        // Real Ship Right
                return false;
            }
            placeInBoard2DArray(colIndex - 2, rowIndex - 1, ShipType.THREECELL,false); // Top Left
            placeInBoard2DArray(colIndex,rowIndex - 1, ShipType.THREECELL, false); // 2
            placeInBoard2DArray(colIndex + 2, rowIndex - 1, ShipType.THREECELL,false); // Top Right
            placeInBoard2DArray(colIndex - 2, rowIndex, ShipType.THREECELL,false ); // 4
            placeInBoard2DArray(colIndex + 2, rowIndex, ShipType.THREECELL,false ); // 5
            placeInBoard2DArray(colIndex - 2, rowIndex + 1, ShipType.THREECELL,false ); // Bottom Left
            placeInBoard2DArray(colIndex, rowIndex + 1, ShipType.THREECELL,false ); // 7
            placeInBoard2DArray(colIndex + 2, rowIndex + 1, ShipType.THREECELL,false ); // Bottom Right
            placeInBoard2DArray(colIndex - 1,rowIndex, ShipType.THREECELL,true); // Real Ship Left
            placeInBoard2DArray(colIndex + 1,rowIndex, ShipType.THREECELL,true); // Real Ship Right
        }

        placeInBoard2DArray(colIndex - 1,rowIndex - 1, ShipType.THREECELL, false); // 1
        placeInBoard2DArray(colIndex + 1, rowIndex - 1, ShipType.THREECELL,false ); // 3
        placeInBoard2DArray(colIndex - 1, rowIndex + 1, ShipType.THREECELL,false ); // 6
        placeInBoard2DArray(colIndex + 1, rowIndex + 1 , ShipType.THREECELL,false ); // 8
        placeInBoard2DArray(colIndex,rowIndex , ShipType.THREECELL,true); // Real Ship
        return true;
    }

    private boolean isFourCellShipCanBePlaced(Integer colIndex, Integer rowIndex){
        if(isEvenAndNotZeroInBoard2DArray(colIndex,rowIndex)){ // Real Ship
            return false;
        }
        if (bRotation) {
            if(isEvenAndNotZeroInBoard2DArray(colIndex - 1,rowIndex - 2)){ // Top Left
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex,rowIndex - 2)){ // 2
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex + 1,rowIndex - 2)){ // Top Right
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex - 1,rowIndex + 3)){ // Bottom Left
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex,rowIndex + 3)){ // 10
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex + 1,rowIndex + 3)){ // Bottom Right
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex,rowIndex - 1)){ // Real Ship Top
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex,rowIndex + 1)){ // Real Ship Middle
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex,rowIndex + 2)){ // Real Ship Bottom
                return false;
            }
            placeInBoard2DArray(colIndex - 1, rowIndex - 2,ShipType.FOURCELL,false); // Top Left
            placeInBoard2DArray(colIndex , rowIndex - 2,ShipType.FOURCELL,false); // 2
            placeInBoard2DArray(colIndex + 1, rowIndex - 2,ShipType.FOURCELL,false); // Top Right
            placeInBoard2DArray(colIndex - 1, rowIndex,ShipType.FOURCELL,false); // 4
            placeInBoard2DArray(colIndex + 1, rowIndex,ShipType.FOURCELL,false); // 5
            placeInBoard2DArray(colIndex + 1, rowIndex + 2,ShipType.FOURCELL,false); // 7
            placeInBoard2DArray(colIndex - 1, rowIndex + 2,ShipType.FOURCELL,false); // 8
            placeInBoard2DArray(colIndex - 1, rowIndex + 3,ShipType.FOURCELL,false); // Bottom Left
            placeInBoard2DArray(colIndex, rowIndex + 3,ShipType.FOURCELL,false); // 10
            placeInBoard2DArray(colIndex + 1, rowIndex + 3,ShipType.FOURCELL,false); // Bottom Right
            placeInBoard2DArray(colIndex,rowIndex - 1,ShipType.FOURCELL,true); // Real Ship Top
            placeInBoard2DArray(colIndex,rowIndex,ShipType.FOURCELL,true); // Real Ship
            placeInBoard2DArray(colIndex,rowIndex + 1,ShipType.FOURCELL,true); // Real Ship Middle
            placeInBoard2DArray(colIndex,rowIndex + 2,ShipType.FOURCELL,true); // Real Ship Bottom

        }else{
            if(isEvenAndNotZeroInBoard2DArray(colIndex - 2,rowIndex - 1)){ // Top Left
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex + 3,rowIndex - 1)){ // Top Right
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex - 2,rowIndex )){ // 5
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex - 2,rowIndex + 1)){ // Bottom Left
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex + 3,rowIndex + 1)){ // Bottom Right
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex + 3,rowIndex)){ // 7
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex - 1,rowIndex)){ // Real Ship Left
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex + 1,rowIndex)){ // Real Ship Middle
                return false;
            }
            if(isEvenAndNotZeroInBoard2DArray(colIndex + 2,rowIndex)){ // Real Ship Right
                return false;
            }

            placeInBoard2DArray(colIndex - 2, rowIndex - 1,ShipType.FOURCELL,false); // Top Left
            placeInBoard2DArray(colIndex , rowIndex - 1,ShipType.FOURCELL,false); // 2
            placeInBoard2DArray(colIndex + 2, rowIndex - 1,ShipType.FOURCELL,false); // 4
            placeInBoard2DArray(colIndex + 3, rowIndex - 1,ShipType.FOURCELL,false); // Top Right
            placeInBoard2DArray(colIndex - 2, rowIndex,ShipType.FOURCELL,false); // 5
            placeInBoard2DArray(colIndex + 3, rowIndex,ShipType.FOURCELL,false); // 7
            placeInBoard2DArray(colIndex, rowIndex + 1,ShipType.FOURCELL,false); // 8
            placeInBoard2DArray(colIndex - 2, rowIndex + 1,ShipType.FOURCELL,false); // Bottom Left
            placeInBoard2DArray(colIndex + 2, rowIndex + 1,ShipType.FOURCELL,false); // 10
            placeInBoard2DArray(colIndex + 3, rowIndex + 1,ShipType.FOURCELL,false); // Bottom Right
            placeInBoard2DArray(colIndex - 1,rowIndex,ShipType.FOURCELL,true); // Real Ship Left
            placeInBoard2DArray(colIndex,rowIndex,ShipType.FOURCELL,true); // Real Ship
            placeInBoard2DArray(colIndex + 1,rowIndex,ShipType.FOURCELL,true); // Real Ship Middle
            placeInBoard2DArray(colIndex + 2,rowIndex,ShipType.FOURCELL,true); // Real Ship Right

        }


        placeInBoard2DArray(colIndex - 1, rowIndex - 1,ShipType.FOURCELL,false); // 1
        placeInBoard2DArray(colIndex + 1, rowIndex - 1,ShipType.FOURCELL,false); // 3
        placeInBoard2DArray(colIndex - 1, rowIndex + 1,ShipType.FOURCELL,false); // 6
        placeInBoard2DArray(colIndex + 1, rowIndex + 1,ShipType.FOURCELL,false); // 9
        return true;
    }

    private void placeInBoard2DArray(Integer colIndex, Integer rowIndex,ShipType shipType,boolean isShip){
        if (isInRangeOfArraySides(colIndex,rowIndex)){
            switch (shipType){
                case ONECELL:
                    if(isShip){
                        board2DArray[colIndex][rowIndex] = 2;
                    }else{
                        board2DArray[colIndex][rowIndex] = 1;
                    }
                    break;
                case TWOCELL:
                    if(isShip){
                        board2DArray[colIndex][rowIndex] = 4;
                    }else{
                        board2DArray[colIndex][rowIndex] = 3;
                    }
                    break;
                case THREECELL:
                    if(isShip){
                        board2DArray[colIndex][rowIndex] = 6;
                    }else{
                        board2DArray[colIndex][rowIndex] = 5;
                    }
                    break;
                case FOURCELL:
                    if(isShip){
                        board2DArray[colIndex][rowIndex] = 8;
                    }else{
                        board2DArray[colIndex][rowIndex] = 7;
                    }
                    break;
            }

        }
    }

    public void initializeboard2DArray(){
        for(int i = 0; i < 10;i++){
            for(int j = 0; j < 10;j++){
                board2DArray[i][j] = 0;
            }
        }
    }

    private boolean isInRangeOfArrayCorners(Integer colIndex, Integer rowIndex){
        return  colIndex > -1 && rowIndex > -1 ||   // Top Left
                colIndex < 10 && rowIndex > -1 ||   // Top Right
                colIndex > -1 && rowIndex < 10 ||   // Bottom Left
                colIndex < 10 && rowIndex < 10;     // Bottom Right

    }

    private boolean isInRangeOfArraySides(Integer colIndex, Integer rowIndex){
        return  rowIndex > -1 &&    // Top
                rowIndex < 10 &&    // Bottom
                colIndex > -1 &&    // Right
                colIndex < 10;      // Left
    }

    private boolean isEvenAndNotZero(int numberToCheck) {
        return numberToCheck % 2 == 0 && numberToCheck != 0;
    }

    private boolean isEvenAndNotZeroInBoard2DArray(Integer colIndex, Integer rowIndex){
        if(isInRangeOfArraySides(colIndex,rowIndex)){
            return board2DArray[colIndex][rowIndex] % 2 == 0 && board2DArray[colIndex][rowIndex] != 0;
        }
        return false;
    }
}
