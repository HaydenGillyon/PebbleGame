import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the pebble game as a whole.
 * @author Hayden Gillyon
 * @author Jorel Coutinho
 */
public class PebbleGame {

    static BlackBag[] blackBags = {new BlackBag("X"), new BlackBag("Y"), new BlackBag("Z")};
    static WhiteBag[] whiteBags = {new WhiteBag("A"), new WhiteBag("B"), new WhiteBag("C")};
    static boolean gameOver = false;

    /**
     * Represents a player in the pebble game.
     */
    public static class Player extends Thread {
        private int bagIndex;
        private ArrayList<Bag.Pebble> pebbles;

        public void run() {
            pebbles = new ArrayList<>();


            boolean flag = false; // Stop playing when flag is true
            while (true) {
                bagIndex = getRandomInt(0,3);
                // TAKE PEBBLE
                pebbles.add(blackBags[bagIndex].takePebble());

                // AFTER PEBBLE TAKE
                boolean canWin = getTotalWeight(pebbles) == 100;
                if (interrupted()) {
                    break;
                } else if (canWin) {
                    synchronized (this) {
                        if (gameOver) {
                            // A player has already won
                            break;
                        }

                        gameOver = true;
                        // TODO - Write to screen and file that the player won
                        break;
                    }
                }

                // TODO - DISCARD PEBBLE

            }
        }

        // Methods
        private int getTotalWeight(ArrayList<Bag.Pebble> p) {
            int countWeight = 0;
            for (Bag.Pebble pebble : p) {
                countWeight += pebble.getWeight();
            }
            return countWeight;
        }
    }

    /**
     * Generates a random integer between min and max.
     * @param min the minimum value (inclusive)
     * @param max the maximum value (exclusive)
     * @return random integer
     */
    public static int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

    // IO file

    /**
     * Main class where program executes.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Assigning white bags to black bags
        assignBags();
    }

    /**
     * Assigns each black bag to it's corresponding white bag.
     */
    public static void assignBags() {
        for(int i = 0; i < blackBags.length; i++) {
            blackBags[i].setWhiteBag(whiteBags[i]);
        }
    }
}
