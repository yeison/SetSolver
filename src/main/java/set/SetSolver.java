package set;

import net.openhft.koloboke.collect.map.ObjIntMap;
import net.openhft.koloboke.collect.map.hash.HashObjIntMaps;
import set.dimensions.type.Card;
import set.dimensions.type.CardSet;
import set.dimensions.type.Dimension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static set.dimensions.type.Card.newCard;

/**
 * Created by yeison on 3/12/16.
 */
public class SetSolver {

    /**
     * It turns out that any second card may follow any first card.
     * Once the second card is selected, there will be a small set
     * of specific third cards that complete the set. Knowing
     * this narrows down the problem space significantly because we
     * only need to perform a cartesian product of the input cards.
     * Then we can perform O(1) lookups of the specific third card to
     * validate whether the set is possible given the first two cards.
     *
     * This solution runs in O(k^2) where k is the number of unique cards
     * passed in as input.
     *
     */


    public static void main(String[] args){

        ArrayList<Card> cards = new ArrayList<>();

        int[] valuesByDimension1 = new int[]{0,0,0,0};
        int[] valuesByDimension2 = new int[]{1,1,1,1};
        int[] valuesByDimension3 = new int[]{2,2,2,2};
        int[] valuesByDimension4 = new int[]{3,3,3,3};


        cards.add(newCard(valuesByDimension1));
        cards.add(newCard(valuesByDimension2));
        cards.add(newCard(valuesByDimension3));
        cards.add(newCard(valuesByDimension4));


        Set<CardSet> possibleSets = getPossibleSets(cards, 4, 4);

        System.out.println();
    }

    /**
     * Compute the sets that are possible for the given input cards and the specified dimension and value sizes
     */
    public static Set<CardSet> getPossibleSets(ArrayList<Card> cards, int dimensionSize, int valuesSize){
        ArrayList<Dimension> dimensions = new ArrayList<>(dimensionSize);

        for (int i = 0; i < dimensionSize; i++) {
            dimensions.add(new Dimension(i, valuesSize));
        }

        Card.setDimensions(dimensions);

        return getPossibleSets(cards);

    }


    private static Set<CardSet> getPossibleSets(ArrayList<Card> cards){
        Set<CardSet> result = new HashSet<>();

        // map each card to its frequency
        ObjIntMap<Card> quantityMap = HashObjIntMaps.newMutableMap();

        for (Card c : cards){
            quantityMap.addValue(c, 1);
        }


        // reduce the problem to unique cards only
        ArrayList<Card> uniqueCards = new ArrayList(quantityMap.keySet());

        for (int i = 0; i < uniqueCards.size(); i++) {
            // TODO: examine the cost of this get
            Card card1 = uniqueCards.get(i);

            quantityMap.addValue(card1, -1); // reduce quantity by 1 for card 1

            for (int j = i; j < uniqueCards.size(); j++) {
                Card card2 = uniqueCards.get(j);

                int quantity = quantityMap.getInt(card2);

                // if true - we have a unique two cards combination, determine and lookup the third
                if(quantity >= 1){
                    quantityMap.addValue(card2, -1); // reduce freq by 1 for current card2

                    List<Card> cards3 = determineThirdCards(card1, card2);

                    for(Card card3 : cards3){
                        // if the card exists and has sufficient quantity, we can form a set
                        if(quantityMap.getInt(card3) >= 1){
                            result.add(new CardSet(card1, card2, card3));
                            //quantityMap.addValue(card3, -1); // reduce quantity of card3
                        }
                    }
                }

                quantityMap.put(card2, quantity); // reset quantity of card2
            }

            // once we're done with card i in the outer loop, set its quantity to 0
            quantityMap.put(card1, 0);
        }

        return result;

    }


    private static List<Card> determineThirdCards(Card card1, Card card2) {
        ArrayList<Card> validCards = new ArrayList<>();


        boolean[] isSame = new boolean[card1.getDimensions().size()];

        boolean isEqual = true;

        // for each dimension determine if the values are the same
        for (int i = 0; i < isSame.length; i++) {
            isSame[i] = card1.getValueByDimension(i) == card2.getValueByDimension(i);
            if(! isSame[i]){
                isEqual = false;
            }
        }

        // if both cards are equal, card 3 must also be equal
        if(isEqual){
            validCards.add(card1);
            return validCards;
        }


        // create valid third cards
        List<List<Integer>> validValues = new ArrayList<>();

        for (int i = 0; i < isSame.length; i++) {

            if(isSame[i]){
                ArrayList valueList = new ArrayList<>(1);
                valueList.add(card1.getValueByDimension(i));
                validValues.add(valueList);
            }else{
                int valuesSize = card1.getDimensions().get(0).getValuesSize();
                ArrayList valueList = new ArrayList<>(valuesSize - 2);

                for (int j = 0; j < valuesSize; j++) {
                    // ignore the values from card 1 and card 2
                    if(j == card1.getValueByDimension(i) || j == card2.getValueByDimension(i)) continue;
                    valueList.add(j);
                }

                validValues.add(valueList);
            }
        }

        int[] valuesByDimension = new int[card1.getDimensions().size()]; // shared array
        generateThirdCards(validValues, 0, valuesByDimension, validCards);

        return validCards;
    }

    private static void generateThirdCards(List<List<Integer>> validValues, int dimension, int[] valuesByDimension, ArrayList<Card> validCards) {
        // base case - all values are set, now generate the valid card
        if(dimension == valuesByDimension.length){
            validCards.add(newCard(valuesByDimension));
            return;
        }

        int nextDimension = dimension + 1;

        for(Integer value : validValues.get(dimension)){
            valuesByDimension[dimension] = value; // set value for dimension
            generateThirdCards(validValues, nextDimension, valuesByDimension, validCards); // make recursive call with value set
        }
    }


}
