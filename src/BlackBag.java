import java.util.ArrayList;

/**
 * Represents a black bag.
 * @author Hayden Gillyon
 * @author Jorel Coutinho
 */
public class BlackBag extends Bag implements BlackBagInterface {
    private WhiteBag whiteBag;
    public BlackBag(String name) {
        super(name);
    }

    /**
     * Synchronized method of handling picking pebbles by player.
     * @return pebble to player
     */
    public synchronized Pebble takePebble() {
        ArrayList<Pebble> pebbles = getPebbles();
        int length = getPebbles().size();
        int pebbleIndex = PebbleGame.getRandomInt(0,length);
        Pebble nextPebble = pebbles.remove(pebbleIndex);
        if (pebbles.size() == 0) {
            fillBlackBag();
        }
        return nextPebble;
    }

    /**
     * Synchronized method to get the amount of pebbles in this bag.
     * @return length of bag's pebbles arraylist
     */
    public synchronized int getPebbleAmount() {
        return getPebbles().size();
    }

    /**
     * Sets the related white bag.
     * @param whiteBag the white bag to be linked
     */
    public void setWhiteBag(WhiteBag whiteBag) {
        this.whiteBag = whiteBag;
    }

    /**
     * Swaps the contents of pebble from black and white bag.
     */
    private void fillBlackBag() {
        ArrayList<Pebble> newWhitePebbles = this.getPebbles();  // empty white bag
        setPebbles(whiteBag.getPebbles());  // import from white bag to black bag
        whiteBag.setPebbles(newWhitePebbles);   // set white bag to empty white bag
    }


}
