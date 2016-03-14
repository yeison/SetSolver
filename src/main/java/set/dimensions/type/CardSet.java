package set.dimensions.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yeison on 3/13/16.
 */
public class CardSet {

    private final List<Card> cards;

    public CardSet(Card ... cards) {
        List<Card> newCards = new ArrayList<>(cards.length);

        for(Card c : cards){
            newCards.add(c);
        }

        Collections.sort(newCards);
        this.cards = Collections.unmodifiableList(newCards);
    }


    public List<Card> getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardSet cardSet = (CardSet) o;

        for (int i = 0; i < cards.size(); i++) {
            if( ! getCards().get(i).equals(cardSet.getCards().get(i)) ){
                return false;
            }
        }

        return true;

    }

    @Override
    public int hashCode() {
        int result = 0;

        for (Card c : getCards()){
            result = 31 * result + c.hashCode();
        }

        return result;
    }
}
