import java.util.ArrayList;

public class Bag {

    public static class Pebble {
        private int weight;

        public int getWeight() {
            return weight;
        }
    }

    // Fields
    private ArrayList<Pebble> pebbles;
    String name;

    // Method
    public ArrayList<Pebble> getPebbles() {
        return pebbles;
    }
    public void setPebbles(ArrayList<Pebble> pebbles) {
        this.pebbles = pebbles;
    }

}
