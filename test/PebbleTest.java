import org.junit.Test;
import static org.junit.Assert.*;

public class PebbleTest {
    @Test
    public void testGetWeight() {
        Bag.Pebble pebble1 = new Bag.Pebble(999999);
        Bag.Pebble pebble2 = new Bag.Pebble(1);
        assertEquals(pebble1.getWeight(), 999999);
        assertEquals(pebble2.getWeight(), 1);
    }
}

