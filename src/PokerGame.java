import java.util.ArrayList;
import java.util.List;

public class PokerGame {
    String currentHand = " ";
    String mostRecentHand = " ";
    List<Integer> numOfHands = new ArrayList<>();
    int[] rankOfHands;
    List<Integer> totalBids = new ArrayList<>();
    List<String[]> totalHands = new ArrayList<>();


    public PokerGame(int listLength){
        numOfHands.add(0);
        numOfHands.add(0);
        numOfHands.add(0);
        numOfHands.add(0);
        numOfHands.add(0);
        numOfHands.add(0);
        numOfHands.add(0);
        rankOfHands = new int[listLength];
    }

    public void addHand (String[] cards, int bid){
        //this is where the hand gets added to the poker game
        totalHands.add(cards);
        totalBids.add(bid);
        List<Integer> card = countCardAppearance(cards);
        handType(card);

    }

    public List<Integer> countCardAppearance(String[] cards) {
        //15 unique cards
        int[] count = new int[15];
        for (String card : cards){
            int value = cardValue(card,false);
            count[value]++;
        }
        List<Integer> removeZero = new ArrayList<>();
        //since the list contains integers of 0's, we have to ignore them
        for (int num : count){
            if (num > 0){
                removeZero.add(num);
            }
        }

        return removeZero;
    }
    public void handType(List<Integer> numOfNums){
        if (checkFiveKind(numOfNums)){
            return;
        }
        if (checkFourKind(numOfNums)){
            return;
        }
        if (checkFullHouse(numOfNums)){
            return;
        }
        if (checkThreeKind(numOfNums)){
            return;
        }
        if (checkTwoPair(numOfNums)){
            return;
        }
        if (checkOnePair(numOfNums)){
            return;
        }
        checkHighCard(numOfNums);
    }
    public boolean checkFiveKind(List<Integer> numOfNums){
        if (numOfNums.contains(5)){
            numOfHands.set(0, numOfHands.getFirst() + 1);
            mostRecentHand = currentHand;
            currentHand = "FIVEKIND";
            return true;
        }
        return false;
    }

    public boolean checkFourKind(List<Integer> numOfNums){
        if (numOfNums.contains(4)){
            numOfHands.set(1, numOfHands.get(1) + 1);
            mostRecentHand = currentHand;
            currentHand = "FOURKIND";
            return true;
        }
        return false;
    }

    public boolean checkFullHouse(List<Integer> numOfNums){
        if (numOfNums.contains(3)){
            if (numOfNums.contains(2)){
                numOfHands.set(2, numOfHands.get(2) + 1);
                mostRecentHand = currentHand;
                currentHand = "FULLHOUSE";
                return true;
            }
        }
        return false;
    }

    public boolean checkThreeKind(List<Integer> numOfNums){
        if (numOfNums.contains(3)) {
            if (numOfNums.contains(1)){
                numOfHands.set(3, numOfHands.get(3) + 1);
                mostRecentHand = currentHand;
                currentHand = "THREEKIND";
                return true;
            }
        }
        return false;
    }

    public boolean checkTwoPair(List<Integer> numOfNums){
        int numOfTwos = 0;
        for (int number : numOfNums){
            if (number == 2){
                numOfTwos++;
            }
        }
        if (numOfTwos == 2){
            numOfHands.set(4, numOfHands.get(4) + 1);
            mostRecentHand = currentHand;
            currentHand = "TWOPAIR";
            return true;
        }
        return false;
    }

    public boolean checkOnePair(List<Integer> numOfNums){
        int numOfTwos = 0;
        for (int number : numOfNums){
            if (number == 2){
                numOfTwos++;
            }
        }
        if (numOfTwos == 1){
            numOfHands.set(5, numOfHands.get(5) + 1);
            mostRecentHand = currentHand;
            currentHand = "ONEPAIR";
            return true;
        }
        return false;
    }

    public boolean checkHighCard(List<Integer> numOfNums){
        int numOfOnes = 0;
        for (int number : numOfNums){
            if (number == 1){
                numOfOnes++;
            }
        }
        if (numOfOnes == 5){
            numOfHands.set(6, numOfHands.get(6) + 1);
            mostRecentHand = currentHand;
            currentHand = "HIGHCARD";
            return true;
        }
        return false;
    }

    public int cardValue(String card, boolean wild){
        //boolean wild is prep for part 3
        if (card.equals("Ace")) {
            return 14;
        }
        if (card.equals("King")) {
            return 13;
        }
        if (card.equals("Queen")) {
            return 12;
        }
        if (card.equals("Jack")) {
            if (wild) {
                return 1;
            } else {
                return 11;
            }
        }
        if (card.equals("10")) {
            return 10;
        }
        return Integer.parseInt(card);
    }


    // --------------------------------------------------------------------------------
    // CHECKING PORTION OF CODE
    // --------------------------------------------------------------------------------


    // public int rankOnHandType()

}
