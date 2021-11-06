import com.sun.source.tree.AssertTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;
public class PebbleGameTest {
    ArrayList<Bag.Pebble> whitePebbles = new ArrayList<>();
    BlackBag blackBag = new BlackBag("testBlackBag");
    String testPebbleFile = "testRes/example_file_3.csv";

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

    @Test
    public void testCheckCsvInput() throws IOException {
        PebbleGame.players = new PebbleGame.Player[1];
        PebbleGame.players[0] = new PebbleGame.Player(0);

        PebbleGame.checkCsvInput(testPebbleFile, PebbleGame.blackBags[0]);
        PebbleGame.checkCsvInput(testPebbleFile, PebbleGame.blackBags[1]);
        PebbleGame.checkCsvInput(testPebbleFile, PebbleGame.blackBags[2]);

        assertEquals(100, PebbleGame.blackBags[0].getPebbleAmount());
    }

    @Test
    public void testPlayerRun() throws InterruptedException {
        PebbleGame.assignBags();
        String weightLine = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,"
                + " 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34,"
                + " 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49,"
                + " 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64,"
                + " 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80,"
                + " 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96,"
                + " 97, 98, 99, 100";
        weightLine = weightLine.replaceAll(" ", "");
        ArrayList<String> weights = new ArrayList<>(Arrays.asList(weightLine.split(",")));

        ArrayList<Bag.Pebble> testPebbles1 = PebbleGame.csvToPebbleList(weights);
        ArrayList<Bag.Pebble> testPebbles2 = PebbleGame.csvToPebbleList(weights);
        ArrayList<Bag.Pebble> testPebbles3 = PebbleGame.csvToPebbleList(weights);

        PebbleGame.blackBags[0].setPebbles(testPebbles1);
        PebbleGame.blackBags[1].setPebbles(testPebbles2);
        PebbleGame.blackBags[2].setPebbles(testPebbles3);

        PebbleGame.whiteBags[0].setPebbles(new ArrayList<>());
        PebbleGame.whiteBags[1].setPebbles(new ArrayList<>());
        PebbleGame.whiteBags[2].setPebbles(new ArrayList<>());

        PebbleGame.cleanGameOutput();

        PebbleGame.Player testPlayer = new PebbleGame.Player(0);
        testPlayer.start();

        testPlayer.join(2000);

        assertTrue(PebbleGame.gameOver);
    }


}
