//TODO poll in black bag
public class BlackBag extends Bag {
    private WhiteBag whiteBag;
    
    public synchronized Pebble takePebble() {
        Pebble[] pebbles = getPebbles();
        int length = getPebbles().length;
        int pebbleIndex = PebbleGame.getRandomInt(0, length);
        // TODO - CHECK IF EMPTY AND DEAL WITH IT
        return pebbles[pebbleIndex];
    }
}
