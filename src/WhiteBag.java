import java.util.ArrayList;

/**
 * Represents a white bag.
 * @author Hayden Gillyon
 * @author Jorel Coutinho
 */
public class WhiteBag extends Bag implements WhiteBagInterface {
    /**
     * Constructs a white bag with the provided name.
     * @param name - assigns the name of the white bag
     */
    public WhiteBag(String name) {
        super(name);
    }

    /**
     * Synchronized method to handle discarding of pebbles by a player
     * @param pebble pebble to be discarded to this white bag
     */
    public synchronized void discardPebble(Pebble pebble) {
        ArrayList<Pebble> pebbles = getPebbles();
        pebbles.add(pebble);
    }
}
