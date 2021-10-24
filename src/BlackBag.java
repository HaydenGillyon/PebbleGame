import java.util.ArrayList;

//TODO poll in black bag

/**
 * Represents a black bag.
 * @author Hayden Gillyon
 * @author Jorel Coutinho
 */
public class BlackBag extends Bag implements BlackBagInterface {
    private WhiteBag whiteBag;
    public BlackBag(String name) {
        this.name = name;
    }

    /**
     *  synchronized method of handling picking pebbles by player
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
