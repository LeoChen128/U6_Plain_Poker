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
    List<List<String>> organizedHandTypes = new ArrayList<>();
    int totalValue = 0;
    int wildValue = 0;
    List<List<String>> fiveOfKinds = new ArrayList<>();
    List<List<String>> fourOfKinds = new ArrayList<>();
    List<List<String>> fullHouses = new ArrayList<>();
    List<List<String>> threeOfKinds = new ArrayList<>();
    List<List<String>> twoPairs = new ArrayList<>();
    List<List<String>> onePairs = new ArrayList<>();
    List<List<String>> highCards = new ArrayList<>();
    List<Integer> rankOfHandsHelper;
    int numOfFiveKind = 0;
    int numOfFourKind = 0;
    int numOfFullHouse = 0;
    int numOfThreeKind = 0;
    int numOfTwoPair = 0;
    int numOfOnePair = 0;
    int numOfHighCard = 0;


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
        rankOfHandsHelper = new ArrayList<>();
    }

    private int countCard(List<String> hand, String cards) {
        int count = 0;
        for (String card : hand) {
            if (card.equals(cards)) {
                count++;
            }
        }
        return count;
    }

    public List<Integer> countCards(List<String> hand) {
        List<Integer> counts = new ArrayList<>();
        List<String> special = new ArrayList<>();

        for (String card : hand) {
            if (!special.contains(card)) {
                special.add(card);
            }
        }

        for (String cards : special) {
            int count = 0;
            for (String card : hand) {
                if (card.equals(cards)) {
                    count++;
                }
            }
            counts.add(count);
        }

        return counts;
    }



    public boolean checkFiveKind(){
        if (numOfNums.contains(5)){
            numOfHands.set(0, numOfHands.getFirst() + 1);
            mostRecentHand = currentHand;
            currentHand = "FIVEKIND";
            numOfFiveKind++;
            return true;
        }
        return false;
    }

    public boolean checkFourKind(){
        if (numOfNums.contains(4)){
            numOfHands.set(1, numOfHands.get(1) + 1);
            mostRecentHand = currentHand;
            currentHand = "FOURKIND";
            numOfFourKind++;
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
                numOfFullHouse++;
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
                numOfThreeKind++;
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
            numOfTwoPair++;
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
            numOfOnePair++;
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
            numOfHighCard++;
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

    public boolean isFirstCardStronger(String card1, String card2, boolean isWildJacks) {
        int value1 = cardValue(card1, isWildJacks);
        int value2 = cardValue(card2, isWildJacks);
        return value1 > value2;
    }

    public boolean isFirstHandStronger(List<String> hand1, List<String> hand2, boolean isWildJacks) {
        for (int i = 0; i < 5; i++) {
            String card1 = hand1.get(i);
            String card2 = hand2.get(i);
            if (!card1.equals(card2)) {
                return isFirstCardStronger(card1, card2, isWildJacks);
            }
        }
        return false;
    }

    public void organizeListByType() {
        rankOfHands.clear();

        //five of a kind
        for (int i = 0; i < unorganizedHands.size(); i++) {
            if (handTypes.get(i) == 7) {
                List<String> currentHand = unorganizedHands.get(i);
                int insertIndex = rankOfHands.size();
                boolean inserted = false;
                for (int j = 0; j < rankOfHands.size() && !inserted; j++) {
                    if (isFirstHandStronger(currentHand, rankOfHands.get(j), false)) {
                        insertIndex = j;
                        inserted = true;
                    }
                }
                rankOfHands.add(insertIndex, currentHand);
                fiveOfKinds.add(currentHand);
            }
        }

        //four of a kind
        for (int i = 0; i < unorganizedHands.size(); i++) {
            if (handTypes.get(i) == 6) {
                List<String> currentHand = unorganizedHands.get(i);
                int insertIndex = rankOfHands.size();
                boolean inserted = false;
                for (int j = rankOfHands.size() - fourOfKinds.size(); j < rankOfHands.size() && !inserted; j++) {
                    if (isFirstHandStronger(currentHand, rankOfHands.get(j), false)) {
                        insertIndex = j;
                        inserted = true;
                    }
                }
                rankOfHands.add(insertIndex, currentHand);
                fourOfKinds.add(currentHand);
            }
        }

        //full house
        for (int i = 0; i < unorganizedHands.size(); i++) {
            if (handTypes.get(i) == 5) {
                List<String> currentHand = unorganizedHands.get(i);
                int insertIndex = rankOfHands.size();
                boolean inserted = false;
                for (int j = rankOfHands.size() - fullHouses.size(); j < rankOfHands.size() && !inserted; j++) {
                    if (isFirstHandStronger(currentHand, rankOfHands.get(j), false)) {
                        insertIndex = j;
                        inserted = true;
                    }
                }
                rankOfHands.add(insertIndex, currentHand);
                fullHouses.add(currentHand);
            }
        }

        //three of a kind
        for (int i = 0; i < unorganizedHands.size(); i++) {
            if (handTypes.get(i) == 4) {
                List<String> currentHand = unorganizedHands.get(i);
                int insertIndex = rankOfHands.size();
                boolean inserted = false;
                for (int j = rankOfHands.size() - threeOfKinds.size(); j < rankOfHands.size() && !inserted; j++) {
                    if (isFirstHandStronger(currentHand, rankOfHands.get(j), false)) {
                        insertIndex = j;
                        inserted = true;
                    }
                }
                rankOfHands.add(insertIndex, currentHand);
                threeOfKinds.add(currentHand);
            }
        }

        //two pairs
        for (int i = 0; i < unorganizedHands.size(); i++) {
            if (handTypes.get(i) == 3) {
                List<String> currentHand = unorganizedHands.get(i);
                int insertIndex = rankOfHands.size();
                boolean inserted = false;
                for (int j = rankOfHands.size() - twoPairs.size(); j < rankOfHands.size() && !inserted; j++) {
                    if (isFirstHandStronger(currentHand, rankOfHands.get(j), false)) {
                        insertIndex = j;
                        inserted = true;
                    }
                }
                rankOfHands.add(insertIndex, currentHand);
                twoPairs.add(currentHand);
            }
        }

        //one pair
        for (int i = 0; i < unorganizedHands.size(); i++) {
            if (handTypes.get(i) == 2) {
                List<String> currentHand = unorganizedHands.get(i);
                int insertIndex = rankOfHands.size();
                boolean inserted = false;
                for (int j = rankOfHands.size() - onePairs.size(); j < rankOfHands.size() && !inserted; j++) {
                    if (isFirstHandStronger(currentHand, rankOfHands.get(j), false)) {
                        insertIndex = j;
                        inserted = true;
                    }
                }
                rankOfHands.add(insertIndex, currentHand);
                onePairs.add(currentHand);
            }
        }

        //high card
        for (int i = 0; i < unorganizedHands.size(); i++) {
            if (handTypes.get(i) == 1) {
                List<String> currentHand = unorganizedHands.get(i);
                int insertIndex = rankOfHands.size();
                boolean inserted = false;
                for (int j = rankOfHands.size() - highCards.size(); j < rankOfHands.size() && !inserted; j++) {
                    if (isFirstHandStronger(currentHand, rankOfHands.get(j), false)) {
                        insertIndex = j;
                        inserted = true;
                    }
                }
                rankOfHands.add(insertIndex, currentHand);
                highCards.add(currentHand);
            }
        }
    }



    public List<String> wildJack(List<String> hand) {
        List<String> bestHand = new ArrayList<>(hand);
        int jackCount = countCard(hand, "Jack");

        if (jackCount == 0) {
            return bestHand;
        }

        String mostFrequentCard = "Ace";
        int maxCount = 0;

        for (String card : hand) {
            if (!card.equals("Jack")) {
                int count = countCard(hand, card);
                if (count > maxCount) {
                    maxCount = count;
                    mostFrequentCard = card;
                }
            }
        }

        for (int i = 0; i < bestHand.size(); i++) {
            if (bestHand.get(i).equals("Jack")) {
                bestHand.set(i, mostFrequentCard);
            }
        }

        return bestHand;
    }

    public void getTotalValue() {
        totalValue = 0;
        wildValue = 0;

        organizeListByType();
        for (int i = 0; i < rankOfHands.size(); i++) {
            List<String> hand = rankOfHands.get(i);
            int handIndex = unorganizedHands.indexOf(hand);
            int rank = rankOfHands.size() - i;
            totalValue += totalBids.get(handIndex) * rank;
        }
        List<List<String>> originalHands = new ArrayList<>(unorganizedHands);
        List<Double> originalHandTypes = new ArrayList<>(handTypes);

        unorganizedHands.clear();
        handTypes.clear();

        for (List<String> hand : originalHands) {
            List<String> bestHand = wildJack(hand);
            unorganizedHands.add(hand);
            numOfNums = countCards(bestHand);
            getType(bestHand);
        }

        organizeListByType();

        for (int i = 0; i < rankOfHands.size(); i++) {
            List<String> hand = rankOfHands.get(i);
            int handIndex = originalHands.indexOf(hand);
            int rank = rankOfHands.size() - i;
            wildValue += totalBids.get(handIndex) * rank;
        }

        unorganizedHands = new ArrayList<>(originalHands);
        handTypes = new ArrayList<>(originalHandTypes);
    }
}
