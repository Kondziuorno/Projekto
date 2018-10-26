import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        String input = "";
        String onePartInput = " ";
        String confirmEmail = " ";
        int sameStringCounter = 0;

        Employee employee = new Employee();

        boolean bGenerateEmail;

        // TODO find better way to inform user

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (!isExitInput(input)) {
            System.out.println("Enter a String: (q or quit for ending email generation)");
            input = br.readLine();

            bGenerateEmail = true;

            if (input.isEmpty() || input.indexOf(' ') == 0) {
                System.out.println("Input cannot be empty or only space. Write your name and surname for email generation.");
                bGenerateEmail = false;
            }
            if (isExitInput(input)) {
                bGenerateEmail = false;
            }

            if (input.lastIndexOf(' ') == input.length() - 1){
                input = input.trim();
            }

            if (!(input.indexOf(' ') > 0)&& bGenerateEmail ) {
                System.out.println("Your email will not be in standard mex email style. Your email will look like " +
                        "input@mex.com while preferred is at least surname.name@mex.com. Are you sure you want to " +
                        "generate this email? [y/n] [q or quit for ending]");

                bGenerateEmail = checkAnswerOfQuery(onePartInput,br);
            }

            if(bGenerateEmail){
                employee.setNameAndAllSurnames(input);

                sameStringCounter = countHowManyEmployee(input);

                // employee.printNameAndAllSurnames();
            }

            if (bGenerateEmail) {

                System.out.println(employee.createEmail(sameStringCounter));

                try(FileWriter fw = new FileWriter("src/DataBaseOfEmails.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)) {

                    System.out.println("Do you like it ? [y/n] [q or quit for ending]");
                    if(checkAnswerOfQuery(confirmEmail,br)) {
                        out.println(input + "\t" + employee.email);
                    }
                } catch (IOException e) {
                    System.out.println("File not found to write on");
                }
            }
        }

    }

    private static boolean isExitInput(String input) {
        return input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit");
    }

    private static boolean checkAnswerOfQuery(String queryAnswer,BufferedReader br) throws IOException{
        while (!queryAnswer.isEmpty()) {
            //onePartInput = scOnePartInput.next();
            queryAnswer = br.readLine();

            if (queryAnswer.equalsIgnoreCase("y")) {
                return true;
            } else if (queryAnswer.equalsIgnoreCase("n")) {
                return false;
            } else {
                if (isExitInput(queryAnswer)){
                    System.exit(1);
                }
                System.out.println("Wrong letter, you need to confirm or not the last query with [y/n][q or quit for ending]");
            }
        }
        return true;
    }

    private static int countHowManyEmployee(String input){
        int counter = 0;
        try {
            // TODO better counting method
            Scanner scanner = new Scanner(new FileInputStream("src/DataBaseOfEmails.txt"));
            File file = new File("src/DataBaseOfEmails.txt");
            if(file.length() != 0){
                String scannerLine = "";
                while(scanner.hasNextLine()){

                    scannerLine = scanner.nextLine().trim();
                    if(scannerLine.contains("\t")){
                        scannerLine = scannerLine.substring(0,scannerLine.indexOf("\t"));
                    }

                    if(input.equals(scannerLine)){
                        counter++;
                    }
                }
            }

        } catch(FileNotFoundException e) {
            System.out.println("File not found to read from");
        }
        return counter;
    }
}
