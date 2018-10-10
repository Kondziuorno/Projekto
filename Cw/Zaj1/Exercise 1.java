public class Main {

    public static void main(String[] args) {
        int firstValue = 15;
        int secondValue = 26;

        boolean bTeen;

        bTeen = isTeen(firstValue,secondValue);
        if(bTeen == true){
            System.out.println("True, one of this two values is teen");
        }
        else{
            System.out.println("False, both values are teen");
        }
    }

    public static boolean isTeen(int firstValue, int secondValue){
        if (firstValue >= 13 && firstValue <= 19 && (secondValue < 13 || secondValue > 19)) {
            return true;
        }
        if ((firstValue < 13 || firstValue > 19) && secondValue >= 13 && secondValue <= 19) {
            return true;
        }
        if (firstValue >= 13 && firstValue <= 19 && secondValue >= 13 && secondValue <= 19) {
            return false;
        }
        return false;
    }
}
