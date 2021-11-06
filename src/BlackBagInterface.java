import java.util.ArrayList;

public interface BlackBagInterface extends BagInterface{
    void setWhiteBag(WhiteBag whiteBag);
    Bag.Pebble takePebble();
    ArrayList<Bag.Pebble> takeTenPebbles();
    int getPebbleAmount();
}
