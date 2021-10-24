import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;



public class BlackBagTest {
    ArrayList<Bag.Pebble> whitePebbles = new ArrayList<>();
    BlackBag blackBag = new BlackBag("testBlackBag");

    @Before
    public void setupTest(){
        WhiteBag whiteBag = new WhiteBag("testWhiteBag");

        whitePebbles.add(new Bag.Pebble(100));
        whitePebbles.add(new Bag.Pebble(20));
        whitePebbles.add(new Bag.Pebble(420));
        whiteBag.setPebbles(whitePebbles);

        ArrayList<Bag.Pebble> blackPebbles = new ArrayList<>();
        blackPebbles.add(new Bag.Pebble(9001));
        blackBag.setPebbles(blackPebbles);
    }

    @Test
    public void testTakePebbles() {
        blackBag.takePebble();  // Throws exception if fillBlackBag fails
        assertEquals(blackBag.getPebbles(),whitePebbles); // Succeeds if pebbles transferred properly
    }

}
