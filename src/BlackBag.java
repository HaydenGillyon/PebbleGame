import java.util.ArrayList;

//TODO poll in black bag
public class BlackBag extends Bag {
    private WhiteBag whiteBag;
    public BlackBag(String name) {
        this.name = name;
    }
    
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

    public void setWhiteBag(WhiteBag whiteBag) {
        this.whiteBag = whiteBag;
    }

    private void fillBlackBag() {
        ArrayList<Pebble> newWhitePebbles = this.getPebbles(); // empty list
        setPebbles(whiteBag.getPebbles()); // import from white bag to black bag
        whiteBag.setPebbles(newWhitePebbles); // set white bag to empty white bag
    }
}
