import java.util.ArrayList;

/**
 * Represents a bag of pebbles.
 *
 * @author Hayden Gillyon
 * @author Jorel Coutinho
 */
public class Bag implements BagInterface{

    /**
     * Constructs a bag with the specified name.
     * @param name name of bag
     */
    Bag(String name){
        this.name = name;
    }

    /**
     * Represents a single pebble.
     */
    public static class Pebble {
        /**
         * Constructs a pebble.
         */
        public Pebble(int weight) {
            this.weight = weight;
        }

        private int weight;

        /**
         * @return weight of the pebble
         */
        public int getWeight() {
            return weight;
        }

        /**
         * Calculates and returns whether this pebble and the given object are equal.
         * @param o object to check
         * @return true if this pebble and o are the same, or have the same weight
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null) {
                return false;
            }
            if (getClass() != o.getClass()) {
                return false;
            }

            Pebble pebble = (Pebble) o;
            return weight == pebble.weight;
        }
    }

    // Fields
    private ArrayList<Pebble> pebbles;
    private String name;

    // Methods
    /**
     * @return pebbles in bag
     */
    public ArrayList<Pebble> getPebbles() {
        return pebbles;
    }

    /**
     * Sets the list of pebbles the bag holds.
     * @param pebbles pebbles the bag will hold
     */
    public void setPebbles(ArrayList<Pebble> pebbles) {
        this.pebbles = pebbles;
    }

    /**
     * Gets the name of this bag.
     * @return bag name
     */
    public String getName() {
        return name;
    }
}
