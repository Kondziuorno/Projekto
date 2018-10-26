import java.util.ArrayList;
import java.util.Arrays;

public class Employee {
    public String name;
    public ArrayList<String> arrayOfSurnames = new ArrayList<>();
    public String email = "";
    public Employee(){

    }
    public Employee(String name,String ... surname){
        this.name = name;
        arrayOfSurnames.addAll(Arrays.asList(surname));
    }

    public String createEmail(int counter){
        if(arrayOfSurnames.size() == 0){
            if(counter == 0){
                email = name.toLowerCase() + "@mex.com";
                return email;
            } else {
                email = name.toLowerCase() + counter + "@mex.com";
                return email;
            }
        } else {
            if(counter == 0){
                StringBuilder sb = new StringBuilder();
                for (String arrayOfSurname : arrayOfSurnames) {
                    sb.append(arrayOfSurname.toLowerCase());
                    sb.append(".");
                }
                sb.append(name.toLowerCase()).append("@mex.com");
                email = sb.toString();
                return email;
            } else {
                StringBuilder sb = new StringBuilder();
                for (String arrayOfSurname : arrayOfSurnames) {
                    sb.append(arrayOfSurname.toLowerCase());
                    sb.append(".");
                }
                sb.append(name.toLowerCase()).append(counter).append("@mex.com");
                email = sb.toString();
                return email;
            }
        }

    }

    public void setNameAndAllSurnames(String input){
        arrayOfSurnames.clear();
        ArrayList<String> words = new ArrayList<>(Arrays.asList(input.split("\\s+")));
        name = words.get(0);
        words.remove(0); // TODO Find Better way without removing
        // TODO check performance of this chunk of code
        for(int i = 0; i < words.size(); ++i){
            if(words.get(i).contains("-")){
                String[] localWord = words.get(i).split("-");
                words.remove(words.get(i));
                words.add(i,localWord[0]);
                words.add(i +1,localWord[1]);
            }
        }

        arrayOfSurnames.addAll(words);
    }

    public void printNameAndAllSurnames(){
        System.out.println(name);
        System.out.println(" ");
        for (String arrayOfSurname : arrayOfSurnames) {
            System.out.println(arrayOfSurname);
        }
    }
}
