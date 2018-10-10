public class Main {

    public static void main(String[] args) {
        int[] array = { 2, 5, 1, 3, 1, 2, 3, 5, 3, 5, 7, 8};

        int firstIndex = 0;
        int lastIndex = 0;
        boolean bExist = false;

        for (int i = 0; i < array.length; ++i){
            if(array[i] == 1){
                if(array[i + 1] == 2){
                    if(array[i + 2] == 3){
                        firstIndex = i;
                        lastIndex = i+2;
                        bExist = true;
                    }
                }
            }
        }
        if(bExist == true){
            System.out.println("Sequence of number 1, 2, 3 exist from "  + firstIndex +  " index to " + lastIndex +" index");
        }
        else{
            System.out.println("Sequence of number 1, 2, 3 does not exist");
        }
    }
}
