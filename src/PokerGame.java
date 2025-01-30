import java.util.ArrayList;
import java.util.List;

public class PokerGame {
    String currentHand = " ";
    String mostRecentHand = " ";
    List<Integer> numOfHands = new ArrayList<>();
    List<List<String>> rankOfHands;
    List<Integer> numOfNums;
    List<Integer> handTypes = new ArrayList<>();
    int[] rankOfHands2;
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
        rankOfHands = new ArrayList<>();
        numOfNums = new ArrayList<>();
        rankOfHands2 = new int[listLength];
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
    public List<Integer> findNumOfNums(List<String> hand){
        int aceCount = 0;
        int kingCount = 0;
        int queenCount = 0;
        int jackCount = 0;
        int tenCount = 0;
        int nineCount = 0;
        int eightCount = 0;
        int sevenCount = 0;
        int sixCount = 0;
        int fiveCount = 0;
        int fourCount = 0;
        int threeCount = 0;
        int twoCount = 0;

        for (String card : hand){
            if (card.equals("Ace")){
                aceCount++;
            }
            else if (card.equals("King")){
                kingCount++;
            }
            else if (card.equals("Queen")){
                queenCount++;
            }
            else if (card.equals("Jack")){
                jackCount++;
            }
            else if (card.equals("10")){
                tenCount++;
            }
            else if (card.equals("9")){
                nineCount++;
            }
            else if (card.equals("8")){
                eightCount++;
            }
            else if (card.equals("7")){
                sevenCount++;
            }
            else if (card.equals("6")){
                sixCount++;
            }
            else if (card.equals("5")){
                fiveCount++;
            }
            else if (card.equals("4")){
                fourCount++;
            }
            else if (card.equals("3")){
                threeCount++;
            }
            else if (card.equals("2")){
                twoCount++;
            }
        }
        numOfNums.add(aceCount);
        numOfNums.add(kingCount);
        numOfNums.add(queenCount);
        numOfNums.add(jackCount);
        numOfNums.add(tenCount);
        numOfNums.add(nineCount);
        numOfNums.add(eightCount);
        numOfNums.add(sevenCount);
        numOfNums.add(sixCount);
        numOfNums.add(fiveCount);
        numOfNums.add(fourCount);
        numOfNums.add(threeCount);
        numOfNums.add(twoCount);
        return numOfNums;
    }



    public boolean checkFiveKind(){
        if (numOfNums.contains(5)){
            numOfHands.set(0, numOfHands.getFirst() + 1);
            mostRecentHand = currentHand;
            currentHand = "FIVEKIND";
            return true;
        }
        return false;
    }

    public boolean checkFourKind(){
        if (numOfNums.contains(4)){
            numOfHands.set(1, numOfHands.get(1) + 1);
            mostRecentHand = currentHand;
            currentHand = "FOURKIND";
            return true;
        }
        return false;
    }

    public boolean checkFullHouse(){
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

    public boolean checkThreeKind(){
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

    public boolean checkTwoPair(){
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

    public boolean checkOnePair(){
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

    public boolean checkHighCard(){
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

    public Integer getType(){
        int type = 0;
        if (checkFiveKind()){
            type = 7;
            handTypes.add(7);
        }
        else if (checkFourKind()){
            type = 6;
            handTypes.add(6);
        }
        else if (checkThreeKind()){
            type = 4;
            handTypes.add(4);
        }
        else if (checkFullHouse()){
            type = 5;
            handTypes.add(5);
        }
        else if (checkTwoPair()){
            type =  3;
            handTypes.add(3);
        }
        else if (checkOnePair()){
            type =  2;
            handTypes.add(2);
        }
        else if (checkHighCard()){
            type = 1;
            handTypes.add(1);
        }

        return type;
    }



    // --------------------------------------------------------------------------------
    // CHECKING PORTION OF CODE
    // --------------------------------------------------------------------------------


    public List<List<String>> rankHands(List<String> hand){

        if (rankOfHands.isEmpty()){
            getType();
            rankOfHands.add(hand);
        }

        else{
            int currentType = getType();
            handTypes.removeLast();
            int counter = 0;
            for (int type : handTypes){

            }
        }
        return rankOfHands;
    }

}
