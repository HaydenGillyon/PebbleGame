import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WhiteBagTest {

    @Test
    public void testDiscardPebble() {
        WhiteBag whiteBag = new WhiteBag("testWhiteBag");
        whiteBag.setPebbles(new ArrayList<>());
        Bag.Pebble pebble = new Bag.Pebble(500);

        ArrayList<Bag.Pebble> testPebblesList = new ArrayList<>();
        testPebblesList.add(pebble);

        whiteBag.discardPebble(pebble);

        assertEquals(testPebblesList, whiteBag.getPebbles());
    }
}