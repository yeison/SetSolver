package set.dimensions.type;

import java.util.List;

/**
 * Created by yeison on 3/13/16.
 */
public class Dimension implements Comparable<Dimension> {

    private final int ordinal;
    private final int valuesSize;

    public Dimension(int ordinal, int valuesSize) {
        this.ordinal = ordinal;
        this.valuesSize = valuesSize;
    }

    public int getOrdinal(){
        return ordinal;
    }

    public int getValuesSize(){
        return valuesSize;
    }

    @Override
    public int compareTo(Dimension o) {
        return getOrdinal() - o.getOrdinal();
    }
}