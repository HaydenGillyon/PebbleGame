import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
public class PebbleGame {

    static BlackBag[] blackBags = {new BlackBag("X"), new BlackBag("Y"), new BlackBag("Z")};
    static WhiteBag[] whiteBags = {new WhiteBag("A"), new WhiteBag("B"), new WhiteBag("C")};


    public static class Player extends Thread {
        private int bagIndex;

        public void run() {
            int weight = 0;
            ArrayList<Bag.Pebble> pebbles = new ArrayList<>();

            // TODO - FIRST 10 PEBBLES

            while (weight != 100) {
                bagIndex = getRandomInt(0,2);


                // AFTER PEBBLE DISCARD AND TAKE
                weight = getTotalWeight(pebbles);
            }
        }

        // Methods
        public int getTotalWeight(ArrayList<Bag.Pebble> p) {
            int countWeight = 0;
            for (Bag.Pebble pebble : p) {
                countWeight += pebble.getWeight();
            }
            return countWeight;
        }
    }

    public static int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

    public static void main(String[] args) {
        // Assigning white bags to black bags
        assignBags();
    }
    public static void assignBags() {
        for(int i = 0; i < blackBags.length; i++) {
            blackBags[i].setWhiteBag(whiteBags[i]);
        }
    }
}
