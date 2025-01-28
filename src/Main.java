import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] fileData = getFileData("src/input_file");
        String[] numbers = new String[0];
        String line = "";
        for (int i = 0; i < fileData.length; i++) {
            line = fileData[i];
            int bid = Integer.parseInt(getBidAmount(line));

        }
        for(int i = 0; i < line.length(); i++){
            int bar = line.indexOf("|");

            numbers = line.split(",");
            System.out.println(Arrays.toString(numbers));
        }
    }
    public static String[] getFileData(String fileName) {
        String fileData = "";
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                fileData += s.nextLine() + "\n";
            }
            return fileData.split("\n");
        }
        catch (FileNotFoundException e) {
            return null;
        }
    }

    public static String getBidAmount(String line){
        int bar = line.indexOf("|");
        return line.substring(bar + 1);
    }

}
