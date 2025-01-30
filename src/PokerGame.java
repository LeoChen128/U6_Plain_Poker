import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PokerGame {
    String currentHand = " ";
    String mostRecentHand = " ";
    List<Integer> numOfHands = new ArrayList<>();
    List<List<String>> rankOfHands;
    List<Integer> numOfNums;
    List<Double> handTypes = new ArrayList<>();
    int[] rankOfHands2;
    List<Integer> totalBids = new ArrayList<>();
    List<String[]> totalHands = new ArrayList<>();
    List<List<String>> unorganizedHands = new ArrayList<>();
    List<Integer> organizedHandTypes = new ArrayList<>();


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


//    public void handType(List<Integer> numOfNums){
//        if (checkFiveKind(numOfNums)){
//            return;
//        }
//        if (checkFourKind(numOfNums)){
//            return;
//        }
//        if (checkFullHouse(numOfNums)){
//            return;
//        }
//        if (checkThreeKind(numOfNums)){
//            return;
//        }
//        if (checkTwoPair(numOfNums)){
//            return;
//        }
//        if (checkOnePair(numOfNums)){
//            return;
//        }
//        checkHighCard(numOfNums){
//            return;
//        };
//  }
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

    public void getType(List<String> hand){
        double numberToAdd = 0;
        int type = 0;
        if (checkFiveKind()){
            type = 7;
            numberToAdd = 7;
        }
        else if (checkFourKind()){
            type = 6;
            numberToAdd = 6;
        }
        else if (checkThreeKind()){
            type = 4;
            numberToAdd = 4;
        }
        else if (checkFullHouse()){
            type = 5;
            numberToAdd = 5;
        }
        else if (checkTwoPair()){
            type =  3;
            numberToAdd = 3;
        }
        else if (checkOnePair()){
            type =  2;
            numberToAdd = 2;
        }
        else if (checkHighCard()){
            type = 1;
            numberToAdd = 1;
        }

        if (Objects.equals(hand.getFirst(), "2")){
            numberToAdd += 0.2;
        }
        handTypes.add(numberToAdd);

    }



    // --------------------------------------------------------------------------------
    // CHECKING PORTION OF CODE
    // --------------------------------------------------------------------------------


    public void addToList(List<String> hand) {

        getType(hand);
        unorganizedHands.add(hand);
    }

    public void organizeListByType(){

        if (handTypes.contains(7)){
            while (handTypes.contains(7)){
                int indexOfSeven = handTypes.indexOf(7);
                handTypes.remove(indexOfSeven);
                rankOfHands.add(unorganizedHands.get(indexOfSeven));
                organizedHandTypes.add(7);
            }
        }

        else if (handTypes.contains(6)){
            while (handTypes.contains(6)){
                int indexOfSix = handTypes.indexOf(6);
                handTypes.remove(indexOfSix);
                rankOfHands.add(unorganizedHands.get(indexOfSix));
                organizedHandTypes.add(6);
            }
        }

        else if (handTypes.contains(5)){
            while (handTypes.contains(5)){
                int indexOfFive = handTypes.indexOf(5);
                handTypes.remove(indexOfFive);
                rankOfHands.add(unorganizedHands.get(indexOfFive));
                organizedHandTypes.add(5);
            }
        }

        else if (handTypes.contains(4)){
            while (handTypes.contains(4)){
                int indexOfFour = handTypes.indexOf(4);
                handTypes.remove(indexOfFour);
                rankOfHands.add(unorganizedHands.get(indexOfFour));
                organizedHandTypes.add(4);
            }
        }

        else if (handTypes.contains(3)){
            while (handTypes.contains(3)){
                int indexOfThree = handTypes.indexOf(3);
                handTypes.remove(indexOfThree);
                rankOfHands.add(unorganizedHands.get(indexOfThree));
                organizedHandTypes.add(3);
            }
        }

        else if (handTypes.contains(2)){
            while (handTypes.contains(2)){
                int indexOfTwo = handTypes.indexOf(2);
                handTypes.remove(indexOfTwo);
                rankOfHands.add(unorganizedHands.get(indexOfTwo));
                organizedHandTypes.add(2);
            }
        }


        else if (handTypes.contains(1)){
            while (handTypes.contains(1)){
                int indexOfOne = handTypes.indexOf(1);
                handTypes.remove(indexOfOne);
                rankOfHands.add(unorganizedHands.get(indexOfOne));
                organizedHandTypes.add(1);
            }
        }

    }


    public void organizeByCards(){

    }
}
