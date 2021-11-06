import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class PebbleGameTest {
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
    public void testAssignBags() {
        PebbleGame.assignBags();


    }
    @Test
    public void testRandomInt() {
        int randTest = 100;
        int count = 0;
        for(int i=0;i<randTest;i++){
            count += PebbleGame.getRandomInt(0,100);
        }
        double average = (double)count / (double)randTest; // t
        assertEquals(average, 50, 10); // Fails if random numbers aren't generated fairly
    }

    @Test
    public void testCsvToPebbles() {
        ArrayList<Bag.Pebble> pebble_check = new ArrayList<>();
        ArrayList<String> pebbles = new ArrayList<>();
        pebbles.add("2");
        pebbles.add("200");
        pebbles.add("2048");
        pebble_check.add(new Bag.Pebble(2));
        pebble_check.add(new Bag.Pebble(200));
        pebble_check.add(new Bag.Pebble(2048));
        ArrayList<Bag.Pebble> pebble_output = PebbleGame.csvToPebbleList(pebbles);

        assertEquals(pebble_check , pebble_output);
    }


}
