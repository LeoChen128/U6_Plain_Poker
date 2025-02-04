import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PokerGame {
    String currentHand = " ";
    String mostRecentHand = " ";
    List<Integer> numOfHands = new ArrayList<>();
    List<List<String>> rankOfHands;
    List<Integer> numOfNums;
    List<Double> handTypes = new ArrayList<>();
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

        rankOfHandsHelper = new ArrayList<>();
    }

    public List<Integer> countCards(List<String> hand){
        List<Integer> tracker = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            tracker.add(0);
        }

        // Count occurrences of each card
        for (String card : hand) {
            if (card.equals("Ace")) {
                tracker.set(0, tracker.getFirst() + 1);
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
//
//        if (Objects.equals(hand.getFirst(), "2")){
//            numberToAdd += 0.2;
//        }

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


    public void organizeListByType() {
        rankOfHands.clear();
        // Five of a kind to high card
        for (int i = 0; i < unorganizedHands.size(); i++) {
            if (handTypes.get(i) == 7) {
                rankOfHands.add(unorganizedHands.get(i));
                fiveOfKinds.add(unorganizedHands.get(i));
            }

        }
        for (int i = 0; i < unorganizedHands.size(); i++) {
            if (handTypes.get(i) == 6) {
                rankOfHands.add(unorganizedHands.get(i));
                fourOfKinds.add(unorganizedHands.get(i));
            }
        }
        for (int i = 0; i < unorganizedHands.size(); i++) {
            if (handTypes.get(i) == 5) {
                rankOfHands.add(unorganizedHands.get(i));
                fullHouses.add(unorganizedHands.get(i));
            }
        }
        for (int i = 0; i < unorganizedHands.size(); i++) {
            if (handTypes.get(i) == 4) {
                rankOfHands.add(unorganizedHands.get(i));
                threeOfKinds.add(unorganizedHands.get(i));
            }
        }
        for (int i = 0; i < unorganizedHands.size(); i++) {
            if (handTypes.get(i) == 3) {
                rankOfHands.add(unorganizedHands.get(i));
                twoPairs.add(unorganizedHands.get(i));
            }
        }
        for (int i = 0; i < unorganizedHands.size(); i++) {
            if (handTypes.get(i) == 2) {
                rankOfHands.add(unorganizedHands.get(i));
                onePairs.add(unorganizedHands.get(i));
            }
        }
        for (int i = 0; i < unorganizedHands.size(); i++) {
            if (handTypes.get(i) == 1) {
                rankOfHands.add(unorganizedHands.get(i));
                highCards.add(unorganizedHands.get(i));
            }
        }

    }

    public ArrayList<String>[] bubbleSort(ArrayList<String>[] hands){
        for(int m = 0; m < hands.length - 1; m++){
            for(int a = 0; a < hands.length - m - 1; a++){
                if(cardValue(hands[a].getFirst(), false) > cardValue(hands[a+1].getFirst(), false)){
                    ArrayList<String> temp = hands[a];
                    hands[a] = hands[a+1];
                    hands[a+1] = temp;
                }
            }
        }
        return hands;
    }


    public void organizeListByFirst(){

        // FiveOfKind
        int five = numOfHands.getFirst();
        ArrayList<String>[] fiveOfKindArray = new ArrayList[five];

        for (int i = 0; i < five; i++){
            fiveOfKindArray[i] = (ArrayList<String>) fiveOfKinds.get(i);

        }

        fiveOfKindArray = bubbleSort(fiveOfKindArray);

        System.out.println(Arrays.toString(fiveOfKindArray));



        // Full

        int fullHouse = numOfHands.get(2);
        ArrayList<String>[] fullHouseArray = new ArrayList[fullHouse];

        for (int l = 0; l < fullHouse; l++){
            fullHouseArray[l] = (ArrayList<String>) fullHouses.get(l);
        }

        fullHouseArray = bubbleSort(fullHouseArray);

        System.out.println(Arrays.toString(fullHouseArray));

        // Four

        int four = numOfHands.get(1);
        ArrayList<String>[] fourOfKindArray = new ArrayList[four];

        for (int l = 0; l < four; l++){
            fourOfKindArray[l] = (ArrayList<String>) fourOfKinds.get(l);
        }

        fourOfKindArray = bubbleSort(fourOfKindArray);

        System.out.println(Arrays.toString(fourOfKindArray));

        // Three

        int three = numOfHands.get(3);
        ArrayList<String>[] threeOfKindArray = new ArrayList[three];

        for (int l = 0; l < three; l++){
            threeOfKindArray[l] = (ArrayList<String>) threeOfKinds.get(l);
        }

        threeOfKindArray = bubbleSort(threeOfKindArray);

        System.out.println(Arrays.toString(threeOfKindArray));


        // Two

//        int two = numOfTwoPair -1;
        int two = numOfHands.get(4) ;
        System.out.println(two);

        ArrayList<String>[] twoPairArray = new ArrayList[two];


        for (int l = 0; l < two; l++) {
            twoPairArray[l] = (ArrayList<String>) twoPairs.get(l);
            
        }




        twoPairArray = bubbleSort(twoPairArray);



        System.out.println(Arrays.toString(twoPairArray));


        // One Pair

        int one = numOfHands.get(5);
        ArrayList<String>[] onePairArray = new ArrayList[one];

        for (int l = 0; l < one; l++){
            onePairArray[l] = (ArrayList<String>) onePairs.get(l);
        }

        onePairArray = bubbleSort(onePairArray);

        System.out.println(Arrays.toString(onePairArray));


        // High Card

        int high = numOfHands.get(6);
        ArrayList<String>[] highCardArray = new ArrayList[high];

        for (int l = 0; l < high; l++){
            highCardArray[l] = (ArrayList<String>) highCards.get(l);
        }

        highCardArray = bubbleSort(highCardArray);

        System.out.println(Arrays.toString(highCardArray));

//        for (int i = 0; i < organizedHandTypes.size(); i++){
//            if (organizedHandTypes.get(i).contains("Jack")){
//
//            }
//        }
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
