import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("src/input_file");
            int lineCount = countLines(file);
            PokerGame game = new PokerGame(lineCount);
            splitHands(file, game);
            printResults(game);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    //for organization rubric
    public static int countLines(File file) throws FileNotFoundException {
        Scanner count = new Scanner(file);
        int lines = 0;
        while (count.hasNextLine()) {
            count.nextLine();
            lines++;
        }
        count.close();
        return lines;
    }

    public static void splitHands(File file, PokerGame game) throws FileNotFoundException {
        Scanner lines = new Scanner(file);
        while (lines.hasNextLine()) {
            String line = lines.nextLine();
            String[] parts = line.split("\\|");
            String[] cards = parts[0].split(",");
            int bid = Integer.parseInt(parts[1]);
            game.addHand(cards, bid);
        }
        lines.close();
        game.getTotalValue();
    }

    public static void printResults(PokerGame game) {
        List<Integer> counts = game.getNumOfHands();
        System.out.println("Number of five of a kind hands: " + counts.get(0));
        System.out.println("Number of four of a kind hands: " + counts.get(1));
        System.out.println("Number of full house hands: " + counts.get(2));
        System.out.println("Number of three of a kind hands: " + counts.get(3));
        System.out.println("Number of two pair hands: " + counts.get(4));
        System.out.println("Number of one pair hands: " + counts.get(5));
        System.out.println("Number of high card hands: " + counts.get(6));
        System.out.println("Total Bid Value: " + game.getTotalBidValue());
        System.out.println("Total Bid Value With Jacks Wild: " + game.getWildBidValue());
    }
}