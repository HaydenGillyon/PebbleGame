import java.util.ArrayList;

/**
 * Represents a bag of pebbles.
 *
 * @author Hayden Gillyon
 * @author Jorel Coutinho
 */
public class Bag {

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
    }

    // Fields
    private ArrayList<Pebble> pebbles;
    String name;

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

}
