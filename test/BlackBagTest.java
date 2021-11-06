import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;



public class BlackBagTest {
    ArrayList<Bag.Pebble> whitePebbles;
    BlackBag blackBag;

    @Before
    public void setupTest(){
        whitePebbles = new ArrayList<>();
        blackBag = new BlackBag("testBlackBag");
        WhiteBag whiteBag = new WhiteBag("testWhiteBag");
        blackBag.setWhiteBag(whiteBag);
    }

    @Test
    public void testTakePebbles() {
        ArrayList<Bag.Pebble> blackPebbles = new ArrayList<>();
        blackPebbles.add(new Bag.Pebble(9001));
        blackBag.setPebbles(blackPebbles);

        whitePebbles.add(new Bag.Pebble(100));
        whitePebbles.add(new Bag.Pebble(20));
        whitePebbles.add(new Bag.Pebble(420));
        WhiteBag whiteBag = blackBag.getWhiteBag();
        whiteBag.setPebbles(whitePebbles);
        blackBag.takePebble();  // Throws exception if fillBlackBag fails
        assertEquals(blackBag.getPebbles(),whitePebbles); // Succeeds if pebbles transferred properly
    }
    @Test
    public void testTakeTenPebbles() {
        ArrayList<Bag.Pebble> blackPebbles =  new ArrayList<>();
        for (int i =0 ; i < 11; i++) {
            blackPebbles.add(new Bag.Pebble(3*i));
        }
        blackBag.setPebbles(blackPebbles);
        ArrayList<Bag.Pebble> test = blackBag.takeTenPebbles();

        assertEquals(10, test.size());
        assertEquals(1, blackBag.getPebbleAmount());
    }

}
