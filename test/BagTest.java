import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class BagTest {

    @Test
    public void testPebbles() {
        Bag bag = new BlackBag("TestBag");
        ArrayList<Bag.Pebble> pebbles = new ArrayList<>();
        pebbles.add(new Bag.Pebble(5));
        pebbles.add(new Bag.Pebble(13));

        bag.setPebbles(pebbles);
        assertEquals(bag.getPebbles(), pebbles);
        assertEquals("TestBag", bag.getName());
    }
}