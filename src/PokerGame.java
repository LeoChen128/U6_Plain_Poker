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
    int totalValue = 0;
    int wildValue = 0;


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

    public List<Integer> countCards(List<String> hand){
        List<Integer> tracker = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            tracker.add(0);
        }

        // Count occurrences of each card
        for (String card : hand) {
            if (card.equals("Ace")) {
                tracker.set(0, tracker.get(0) + 1);
            }
            else if (card.equals("King")) {
                tracker.set(1, tracker.get(1) + 1);
            }
            else if (card.equals("Queen")) {
                tracker.set(2, tracker.get(2) + 1);
            }
            else if (card.equals("Jack")) {
                tracker.set(3, tracker.get(3) + 1);
            }
            else if (card.equals("10")) {
                tracker.set(4, tracker.get(4) + 1);
            }
            else if (card.equals("9")) {
                tracker.set(5, tracker.get(5) + 1);
            }
            else if (card.equals("8")) {
                tracker.set(6, tracker.get(6) + 1);
            }
            else if (card.equals("7")) {
                tracker.set(7, tracker.get(7) + 1);
            }
            else if (card.equals("6")) {
                tracker.set(8, tracker.get(8) + 1);
            }
            else if (card.equals("5")) {
                tracker.set(9, tracker.get(9) + 1);
            }
            else if (card.equals("4")) {
                tracker.set(10, tracker.get(10) + 1);
            }
            else if (card.equals("3")) {
                tracker.set(11, tracker.get(11) + 1);
            }
            else if (card.equals("2")) {
                tracker.set(12, tracker.get(12) + 1);
            }
        }

        return tracker;
    }

//    public List<Integer> findNumOfNums(List<String> hand){
//        int aceCount = 0;
//        int kingCount = 0;
//        int queenCount = 0;
//        int jackCount = 0;
//        int tenCount = 0;
//        int nineCount = 0;
//        int eightCount = 0;
//        int sevenCount = 0;
//        int sixCount = 0;
//        int fiveCount = 0;
//        int fourCount = 0;
//        int threeCount = 0;
//        int twoCount = 0;
//
//        for (String card : hand){
//            if (card.equals("Ace")){
//                aceCount++;
//            }
//            else if (card.equals("King")){
//                kingCount++;
//            }
//            else if (card.equals("Queen")){
//                queenCount++;
//            }
//            else if (card.equals("Jack")){
//                jackCount++;
//            }
//            else if (card.equals("10")){
//                tenCount++;
//            }
//            else if (card.equals("9")){
//                nineCount++;
//            }
//            else if (card.equals("8")){
//                eightCount++;
//            }
//            else if (card.equals("7")){
//                sevenCount++;
//            }
//            else if (card.equals("6")){
//                sixCount++;
//            }
//            else if (card.equals("5")){
//                fiveCount++;
//            }
//            else if (card.equals("4")){
//                fourCount++;
//            }
//            else if (card.equals("3")){
//                threeCount++;
//            }
//            else if (card.equals("2")){
//                twoCount++;
//            }
//        }
//        numOfNums.add(aceCount);
//        numOfNums.add(kingCount);
//        numOfNums.add(queenCount);
//        numOfNums.add(jackCount);
//        numOfNums.add(tenCount);
//        numOfNums.add(nineCount);
//        numOfNums.add(eightCount);
//        numOfNums.add(sevenCount);
//        numOfNums.add(sixCount);
//        numOfNums.add(fiveCount);
//        numOfNums.add(fourCount);
//        numOfNums.add(threeCount);
//        numOfNums.add(twoCount);
//        return numOfNums;
//    }



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

    public void addHand(String[] cards, int bid) {
        List<String> cardList = new ArrayList<>();
        for (String card : cards) {
            cardList.add(card);
        }
        numOfNums = new ArrayList<>();
        numOfNums = countCards(cardList);
        totalBids.add(bid);
        totalHands.add(cards);
        addToList(cardList);
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
        if (checkFiveKind()){
            numberToAdd = 7;
        }
        else if (checkFourKind()){
            numberToAdd = 6;
        }
        else if (checkThreeKind()){
            numberToAdd = 4;
        }
        else if (checkFullHouse()){
            numberToAdd = 5;
        }
        else if (checkTwoPair()){
            numberToAdd = 3;
        }
        else if (checkOnePair()){
            numberToAdd = 2;
        }
        else if (checkHighCard()){
            numberToAdd = 1;
        }

        if (Objects.equals(hand.getFirst(), "2")){
            numberToAdd += 0.2;
        }

        // if the first card is 3, add 0.3, etc.
        handTypes.add(numberToAdd);

    }



    // --------------------------------------------------------------------------------
    // CHECKING PORTION OF CODE
    // --------------------------------------------------------------------------------


    public void addToList(List<String> hand) {
        getType(hand);
        unorganizedHands.add(hand);
    }

//    public void organizeListByType(){
//
//        if (handTypes.contains(7)){
//            while (handTypes.contains(7)){
//                int indexOfSeven = handTypes.indexOf(7);
//                handTypes.remove(indexOfSeven);
//                rankOfHands.add(unorganizedHands.get(indexOfSeven));
//                organizedHandTypes.add(7);
//            }
//        }
//
//        else if (handTypes.contains(6)){
//            while (handTypes.contains(6)){
//                int indexOfSix = handTypes.indexOf(6);
//                handTypes.remove(indexOfSix);
//                rankOfHands.add(unorganizedHands.get(indexOfSix));
//                organizedHandTypes.add(6);
//            }
//        }
//
//        else if (handTypes.contains(5)){
//            while (handTypes.contains(5)){
//                int indexOfFive = handTypes.indexOf(5);
//                handTypes.remove(indexOfFive);
//                rankOfHands.add(unorganizedHands.get(indexOfFive));
//                organizedHandTypes.add(5);
//            }
//        }
//
//        else if (handTypes.contains(4)){
//            while (handTypes.contains(4)){
//                int indexOfFour = handTypes.indexOf(4);
//                handTypes.remove(indexOfFour);
//                rankOfHands.add(unorganizedHands.get(indexOfFour));
//                organizedHandTypes.add(4);
//            }
//        }
//
//        else if (handTypes.contains(3)){
//            while (handTypes.contains(3)){
//                int indexOfThree = handTypes.indexOf(3);
//                handTypes.remove(indexOfThree);
//                rankOfHands.add(unorganizedHands.get(indexOfThree));
//                organizedHandTypes.add(3);
//            }
//        }
//
//        else if (handTypes.contains(2)){
//            while (handTypes.contains(2)){
//                int indexOfTwo = handTypes.indexOf(2);
//                handTypes.remove(indexOfTwo);
//                rankOfHands.add(unorganizedHands.get(indexOfTwo));
//                organizedHandTypes.add(2);
//            }
//        }
//
//
//        else if (handTypes.contains(1)){
//            while (handTypes.contains(1)){
//                int indexOfOne = handTypes.indexOf(1);
//                handTypes.remove(indexOfOne);
//                rankOfHands.add(unorganizedHands.get(indexOfOne));
//                organizedHandTypes.add(1);
//            }
//        }
//    }
public void organizeListByType() {
    rankOfHands.clear();
    // Five of a kind to high card
    for (int i = 0; i < unorganizedHands.size(); i++) {
        if (handTypes.get(i) == 7) {
            rankOfHands.add(unorganizedHands.get(i));
        }
    }
    for (int i = 0; i < unorganizedHands.size(); i++) {
        if (handTypes.get(i) == 6) {
            rankOfHands.add(unorganizedHands.get(i));
        }
    }
    for (int i = 0; i < unorganizedHands.size(); i++) {
        if (handTypes.get(i) == 5) {
            rankOfHands.add(unorganizedHands.get(i));
        }
    }
    for (int i = 0; i < unorganizedHands.size(); i++) {
        if (handTypes.get(i) == 4) {
            rankOfHands.add(unorganizedHands.get(i));
        }
    }
    for (int i = 0; i < unorganizedHands.size(); i++) {
        if (handTypes.get(i) == 3) {
            rankOfHands.add(unorganizedHands.get(i));
        }
    }
    for (int i = 0; i < unorganizedHands.size(); i++) {
        if (handTypes.get(i) == 2) {
            rankOfHands.add(unorganizedHands.get(i));
        }
    }
    for (int i = 0; i < unorganizedHands.size(); i++) {
        if (handTypes.get(i) == 1) {
            rankOfHands.add(unorganizedHands.get(i));
        }
    }
}
    public void getTotalValue(){
        organizeListByType();
        int rankOfCards = 1;
        for (int i = 0; i<rankOfHands.size();i++){
            totalValue += totalBids.get(i) * rankOfCards;
            rankOfCards++;
        }
    }
}
