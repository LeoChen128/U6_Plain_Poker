import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try {
            File f = new File("src/input_file");
            Scanner count = new Scanner(f);
            int lines = 0;
            while (count.hasNextLine()) {
                count.nextLine();
                lines++;
            }
            count.close();
            PokerGame game = new PokerGame(lines);

            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] split = line.split("\\|");
                String[] cards = split[0].split(",");
                int bid = Integer.parseInt(split[1]);
                game.addHand(cards,bid);
            }
            s.close();
            System.out.println("Number of five of a kind hands: " + game.numOfHands.get(0));
            System.out.println("Number of full house hands: " + game.numOfHands.get(2));
            System.out.println("Number of four of a kind hands: " + game.numOfHands.get(1));
            System.out.println("Number of three of a kind hands: " + game.numOfHands.get(3));
            System.out.println("Number of two pair hands: " + game.numOfHands.get(4));
            System.out.println("Number of one pair hands: " + game.numOfHands.get(5));
            System.out.println("Number of high card hands: " + game.numOfHands.get(6));
            System.out.println("Total Bid Value: " + game.totalValue);
            System.out.println("Total Bid Value With Jacks Wild: " + game.wildValue);
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }




    }

}
