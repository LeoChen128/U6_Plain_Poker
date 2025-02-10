import java.util.ArrayList;
import java.util.List;

public class PokerGame {
    List<String[]> hands = new ArrayList<>();
    List<Integer> bids = new ArrayList<>();
    int fiveOfAKindCount = 0;
    int fourOfAKindCount = 0;
    int fullHouseCount = 0;
    int threeOfAKindCount = 0;
    int twoPairCount = 0;
    int onePairCount = 0;
    int highCardCount = 0;
    int totalValue = 0;
    int wildValue = 0;

    //needs parameter or else error
    public PokerGame(int lengthOfList) {
    }

    public int getTotalBidValue() {
        return totalValue;
    }

    public int getWildBidValue() {
        return wildValue;
    }

    public int getCardValue(String card, boolean isWild) {
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
            if (isWild) {
                return 1;
            } else {
                return 11;
            }
        }

        if (card.equals("10")) {
            return 10;
        }

        if (card.equals("9")) {
            return 9;
        }

        if (card.equals("8")) {
            return 8;
        }

        if (card.equals("7")) {
            return 7;
        }

        if (card.equals("6")) {
            return 6;
        }

        if (card.equals("5")) {
            return 5;
        }

        if (card.equals("4")) {
            return 4;
        }

        if (card.equals("3")) {
            return 3;
        }

        return 2;
    }

    public int[] countCards(String[] hand, boolean wildjack) {
        int[] cardCounts = new int[15];
        int jack = 0;

        for (String card : hand) {
            if (card.equals("Jack") && wildjack) {
                jack++;
            } else {
                int value = getCardValue(card, wildjack);
                cardCounts[value]++;
            }
        }

        if (wildjack && jack > 0) {
            int maxCount = 0;
            int maxIndex = 14;

            for (int i = 2; i <= 14; i++) {
                if (cardCounts[i] > maxCount) {
                    maxCount = cardCounts[i];
                    maxIndex = i;
                }
            }

            cardCounts[maxIndex] += jack;
        }

        return cardCounts;
    }

    public int getHandType(String[] hand, boolean countWildJacks) {
        int[] counts = countCards(hand, countWildJacks);

        //five of a kind
        for (int count : counts) {
            if (count == 5) {
                //7 is the highest rank from 5 of a kind to high card
                return 7;
            }
        }

        //four of a kind
        for (int count : counts) {
            if (count == 4) {
                return 6;
            }
        }

        //full house and three of a kind
        boolean hasThree = false;
        boolean hasTwo = false;

        for (int count : counts) {
            if (count == 3) {
                hasThree = true;
            }
            if (count == 2) {
                hasTwo = true;
            }
        }

        if (hasThree && hasTwo) {
            return 5; //full house
        }
        if (hasThree) {
            return 4; //three of a kind
        }

        // Count pairs
        int pairCount = 0;
        for (int count : counts) {
            if (count == 2) {
                pairCount++;
            }
        }

        if (pairCount == 2) {
            return 3; //two pair
        }
        if (pairCount == 1) {
            return 2; //one pair
        }

        return 1; //high card
    }

    public int compareHands(String[] hand1, String[] hand2, boolean useWildJacks) {
        //compare hand types
        int type1 = getHandType(hand1, useWildJacks);
        int type2 = getHandType(hand2, useWildJacks);

        if (type1 != type2) {
            return type1 - type2;
        }

        //compare cards
        for (int i = 0; i < 5; i++) {
            int value1 = getCardValue(hand1[i], useWildJacks);
            int value2 = getCardValue(hand2[i], useWildJacks);

            if (value1 != value2) {
                return value1 - value2;
            }
        }

        return 0;
    }

    public void addHand(String[] cards, int bid) {
        hands.add(cards);
        bids.add(bid);

        int type = getHandType(cards, false);
        if (type == 7) {
            fiveOfAKindCount++;
        } else if (type == 6) {
            fourOfAKindCount++;
        } else if (type == 5) {
            fullHouseCount++;
        } else if (type == 4) {
            threeOfAKindCount++;
        } else if (type == 3) {
            twoPairCount++;
        } else if (type == 2) {
            onePairCount++;
        } else if (type == 1) {
            highCardCount++;
        }
    }

    public void bubbleSort(List<Integer> indices, boolean useWildJacks) {
        int n = indices.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String[] hand1 = hands.get(indices.get(j));
                String[] hand2 = hands.get(indices.get(j + 1));

                if (compareHands(hand1, hand2, useWildJacks) > 0) {
                    int temp = indices.get(j);
                    indices.set(j, indices.get(j + 1));
                    indices.set(j + 1, temp);
                }
            }
        }
    }

    public void getTotalValue() {
        //goes through the hand list
        List<Integer> hand = new ArrayList<>();
        for (int i = 0; i < hands.size(); i++) {
            hand.add(i);
        }
        // w/o wild jack
        bubbleSort(hand, false);
        for (int i = 0; i < hand.size(); i++) {
            int rank = i + 1;
            totalValue += bids.get(hand.get(i)) * rank;
        }
        //wild Jack
        bubbleSort(hand, true);
        for (int i = 0; i < hand.size(); i++) {
            int rank = i + 1;
            wildValue += bids.get(hand.get(i)) * rank;
        }
    }

    public List<Integer> getNumOfHands() {
        List<Integer> counts = new ArrayList<>();
        counts.add(fiveOfAKindCount);
        counts.add(fourOfAKindCount);
        counts.add(fullHouseCount);
        counts.add(threeOfAKindCount);
        counts.add(twoPairCount);
        counts.add(onePairCount);
        counts.add(highCardCount);
        return counts;
    }
}