package set;

import org.junit.Assert;
import org.junit.Test;
import set.dimensions.type.Card;
import set.dimensions.type.CardSet;

import java.util.ArrayList;
import java.util.Set;

import static set.SetSolver.getPossibleSets;
import static set.dimensions.type.Card.newCard;

/**
 * Created by yeison on 3/14/16.
 */
public class SetSolverTest {


    @Test
    public void test3SameCards1(){
        ArrayList<Card> cards = new ArrayList<>();

        int[] valuesByDimension1 = new int[]{0,0,0,0};

        for (int i = 0; i < 3; i++) {
            cards.add(newCard(valuesByDimension1));
        }

        Set<CardSet> possibleSets = getPossibleSets(cards, 4, 4);

        Assert.assertEquals(1, possibleSets.size());
    }

    @Test
    public void test3SameCards2(){
        ArrayList<Card> cards = new ArrayList<>();

        int[] valuesByDimension1 = new int[]{1,0,0,0};

        for (int i = 0; i < 3; i++) {
            cards.add(newCard(valuesByDimension1));
        }

        Set<CardSet> possibleSets = getPossibleSets(cards, 4, 4);

        Assert.assertEquals(1, possibleSets.size());
    }

    @Test
    public void test3SameCards3(){
        ArrayList<Card> cards = new ArrayList<>();

        int[] valuesByDimension1 = new int[]{0,1,0,0};

        for (int i = 0; i < 3; i++) {
            cards.add(newCard(valuesByDimension1));
        }

        Set<CardSet> possibleSets = getPossibleSets(cards, 4, 4);

        Assert.assertEquals(1, possibleSets.size());
    }

    @Test
    public void test3SameCards4(){
        ArrayList<Card> cards = new ArrayList<>();

        int[] valuesByDimension1 = new int[]{1,0,1,0};

        for (int i = 0; i < 3; i++) {
            cards.add(newCard(valuesByDimension1));
        }

        Set<CardSet> possibleSets = getPossibleSets(cards, 4, 4);

        Assert.assertEquals(1, possibleSets.size());
    }

    @Test
    public void test3SameCards5(){
        ArrayList<Card> cards = new ArrayList<>();

        int[] valuesByDimension1 = new int[]{0,3,0,3};

        for (int i = 0; i < 3; i++) {
            cards.add(newCard(valuesByDimension1));
        }

        Set<CardSet> possibleSets = getPossibleSets(cards, 4, 4);

        Assert.assertEquals(1, possibleSets.size());
    }

    @Test
    public void test64SameCards1(){
        ArrayList<Card> cards = new ArrayList<>();

        int[] valuesByDimension1 = new int[]{0,3,0,3};

        for (int i = 0; i < 64; i++) {
            cards.add(newCard(valuesByDimension1));
        }

        Set<CardSet> possibleSets = getPossibleSets(cards, 4, 4);

        Assert.assertEquals(1, possibleSets.size());
    }

    @Test
    public void test3DifferentCards1(){
        ArrayList<Card> cards = new ArrayList<>();

        int[] valuesByDimension1 = new int[]{0,0,0,0};
        int[] valuesByDimension2 = new int[]{1,1,1,1};
        int[] valuesByDimension3 = new int[]{2,2,2,2};


        cards.add(newCard(valuesByDimension1));
        cards.add(newCard(valuesByDimension2));
        cards.add(newCard(valuesByDimension3));


        Set<CardSet> possibleSets = getPossibleSets(cards, 4, 4);

        Assert.assertEquals(1, possibleSets.size());
    }

    @Test
    public void test3DifferentCards2(){
        ArrayList<Card> cards = new ArrayList<>();

        int[] valuesByDimension1 = new int[]{0,0,0,0};
        int[] valuesByDimension2 = new int[]{1,1,1,1};
        int[] valuesByDimension3 = new int[]{3,3,3,3};


        cards.add(newCard(valuesByDimension1));
        cards.add(newCard(valuesByDimension2));
        cards.add(newCard(valuesByDimension3));


        Set<CardSet> possibleSets = getPossibleSets(cards, 4, 4);

        Assert.assertEquals(1, possibleSets.size());
    }

    @Test
    public void test3DifferentCards3(){
        ArrayList<Card> cards = new ArrayList<>();

        int[] valuesByDimension1 = new int[]{2,2,2,2};
        int[] valuesByDimension2 = new int[]{1,1,1,1};
        int[] valuesByDimension3 = new int[]{3,3,3,3};


        cards.add(newCard(valuesByDimension1));
        cards.add(newCard(valuesByDimension2));
        cards.add(newCard(valuesByDimension3));


        Set<CardSet> possibleSets = getPossibleSets(cards, 4, 4);

        Assert.assertEquals(1, possibleSets.size());
    }

    @Test
    public void test4DifferentCards1(){
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

        // solution is (4 choose 3) = 4
        Assert.assertEquals(4, possibleSets.size());
    }


    @Test
    public void test5DifferentCards1(){
        ArrayList<Card> cards = new ArrayList<>();

        cards.add(newCard(new int[]{0,0,0,0}));
        cards.add(newCard(new int[]{1,1,1,1}));
        cards.add(newCard(new int[]{2,2,2,2}));
        cards.add(newCard(new int[]{3,3,3,3}));
        cards.add(newCard(new int[]{4,4,4,4}));

        Set<CardSet> possibleSets = getPossibleSets(cards, 4, 5);

        // solution is (5 choose 3) = 10
        Assert.assertEquals(10, possibleSets.size());
    }

    @Test
    public void test5DifferentCardsWithRepeats1(){
        ArrayList<Card> cards = new ArrayList<>();

        cards.add(newCard(new int[]{0,0,0,0}));
        cards.add(newCard(new int[]{0,0,0,0}));
        cards.add(newCard(new int[]{0,0,0,0}));
        cards.add(newCard(new int[]{1,1,1,1}));
        cards.add(newCard(new int[]{1,1,1,1}));
        cards.add(newCard(new int[]{1,1,1,1}));
        cards.add(newCard(new int[]{1,1,1,1}));
        cards.add(newCard(new int[]{1,1,1,1}));
        cards.add(newCard(new int[]{2,2,2,2}));
        cards.add(newCard(new int[]{3,3,3,3}));
        cards.add(newCard(new int[]{3,3,3,3}));
        cards.add(newCard(new int[]{4,4,4,4}));
        cards.add(newCard(new int[]{4,4,4,4}));
        cards.add(newCard(new int[]{4,4,4,4}));


        Set<CardSet> possibleSets = getPossibleSets(cards, 4, 5);

        // solution is (5 choose 3) + 3 = 13
        Assert.assertEquals(13, possibleSets.size());
    }

    @Test
    public void testComplex3Cards1(){
        ArrayList<Card> cards = new ArrayList<>();

        cards.add(newCard(new int[]{0,1,0,1}));
        cards.add(newCard(new int[]{1,2,1,2}));
        cards.add(newCard(new int[]{2,3,2,3}));

        Set<CardSet> possibleSets = getPossibleSets(cards, 4, 5);

        Assert.assertEquals(1, possibleSets.size());
    }

    @Test
    public void testComplex3CardsWithRepeats1(){
        ArrayList<Card> cards = new ArrayList<>();

        cards.add(newCard(new int[]{0,1,0,1}));
        cards.add(newCard(new int[]{0,1,0,1}));
        cards.add(newCard(new int[]{1,2,1,2}));
        cards.add(newCard(new int[]{2,3,2,3}));


        Set<CardSet> possibleSets = getPossibleSets(cards, 4, 5);

        Assert.assertEquals(1, possibleSets.size());
    }

    @Test
    public void testComplex3CardsWithRepeats2(){
        ArrayList<Card> cards = new ArrayList<>();

        cards.add(newCard(new int[]{0,1,0,1}));
        cards.add(newCard(new int[]{0,1,0,1}));
        cards.add(newCard(new int[]{0,1,0,1}));
        cards.add(newCard(new int[]{1,2,1,2}));
        cards.add(newCard(new int[]{2,3,2,3}));


        Set<CardSet> possibleSets = getPossibleSets(cards, 4, 5);

        Assert.assertEquals(2, possibleSets.size());
    }

    @Test
    public void testComplex4Cards1(){
        ArrayList<Card> cards = new ArrayList<>();

        cards.add(newCard(new int[]{0,1,0,1}));
        cards.add(newCard(new int[]{1,2,1,2}));
        cards.add(newCard(new int[]{2,3,2,3}));
        cards.add(newCard(new int[]{3,4,3,4}));


        Set<CardSet> possibleSets = getPossibleSets(cards, 4, 5);

        // (4 choose 3) = 4
        Assert.assertEquals(4, possibleSets.size());
    }

    @Test
    public void testComplex5Cards1(){
        ArrayList<Card> cards = new ArrayList<>();

        cards.add(newCard(new int[]{0,1,0,1}));
        cards.add(newCard(new int[]{1,2,1,2}));
        cards.add(newCard(new int[]{2,3,2,3}));
        cards.add(newCard(new int[]{3,4,3,4}));
        cards.add(newCard(new int[]{4,5,4,5}));


        Set<CardSet> possibleSets = getPossibleSets(cards, 4, 6);

        // (5 choose 3) = 10
        Assert.assertEquals(10, possibleSets.size());
    }

}