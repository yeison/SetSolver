package set.dimensions.type;

import com.google.common.math.IntMath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by yeison on 3/13/16.
 */
public class Card implements Comparable<Card>{

    // This variable will reference the available dimensions (singleton)
    private static List<Dimension> dimensions = null;

    // store the values by dimension for this card
    private final int[] valuesByDimension;

    Card(int[] valuesByDimension) {
        if(valuesByDimension.length == 0){
            throw new IllegalArgumentException("valuesByDimension must have at least 1 value");
        }

        this.valuesByDimension = Arrays.copyOf(valuesByDimension, valuesByDimension.length);
    }

    /**
     * Create a Card, with the dimension values specified in valuesByDimension
     * @param valuesByDimension For each dimension (e.g. 0,1,2,3 ... etc) specify a value
     * @return a newly created card
     */
    public static Card newCard(int[] valuesByDimension){
        Card c = new Card(valuesByDimension);
        return c;
    }


    /**
     *  This function maps values for each dimension-value pair
     *  to the int space.  Due to overflow the mapping is not
     *  one-to-one, but it is onto
     */
    static int computeIntValue(int valueByDimension, int ordinal){

        return ordinal +  IntMath.pow(dimensions.size(), valueByDimension);

    }

    @Override
    public int hashCode() {
        int result = 0;

        for (int i = 0; i < dimensions.size(); i++) {
            result += computeIntValue(valuesByDimension[i], i);
        }

        return result / dimensions.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Card o = (Card) obj;

        // to test equality - compare the value for each dimension
        for (int i = 0; i < valuesByDimension.length; i++) {
            if(getValueByDimension(i) != o.getValueByDimension(i)){
                return false;
            }
        }

        return true;
    }

    /**
     * Get the value for the specified dimension i
     */
    public int getValueByDimension(int i) {
        return valuesByDimension[i];
    }

    /**
     * Retrieve a read-only list of the dimensions
     */
    public List<Dimension> getDimensions(){
        try{
            lock.readLock().lock();
            return dimensions;
        }finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Only sets dimensions if it is not already set. Dimensions
     * will be sorted by Dimensions.getOrdinal()
     *
     * Creates a defensive copy of dims
     *
     */
    public static void setDimensions(List<Dimension> dims){
        List<Dimension> dimsCopy = new ArrayList<>(dims);

        // sort by Dimensions.getOrdinal()
        Collections.sort(dimsCopy);

        try{
            lock.writeLock().lock();
            if(dimensions == null){
                dimensions = Collections.unmodifiableList(dimsCopy);
            }
        }finally {
            lock.writeLock().unlock();
        }
    }

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public int compareTo(Card o) {
        return hashCode() - o.hashCode();
    }
}
