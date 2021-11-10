import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;
import static org.junit.Assert.*;
import java.io.InputStream;


public class PebbleGameTest {
    ArrayList<Bag.Pebble> whitePebbles = new ArrayList<>();
    BlackBag blackBag = new BlackBag("testBlackBag");
    String testPebbleFile = "testRes/example_file_3.csv";

    @Before
    public void setupTest(){

        PebbleGame.blackBags = new BlackBag[]{new BlackBag("X") , new BlackBag("Y"),new  BlackBag("Z")};
        PebbleGame.whiteBags = new WhiteBag[]{new WhiteBag("A") , new WhiteBag("B"),new  WhiteBag("C")};
        PebbleGame.gameOver = false;

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
        BlackBag blackBag = new BlackBag("testBlackBag");

        PebbleGame.checkCsvInput(testPebbleFile, blackBag);

        assertEquals(30, blackBag.getPebbleAmount());
    }

    @Test
    public void testPlayerRun() throws InterruptedException {
        PebbleGame.assignBags();
        String weightLine = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,"
                + " 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30";
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
        PebbleGame.players = new PebbleGame.Player[1];
        PebbleGame.setupGame();

        PebbleGame.players[0].join(2000);

        assertTrue(PebbleGame.gameOver);
    }

    @Test
    public void testPlayerInput(){
        boolean check = PebbleGame.playerInput("10");
        assertTrue(check);
        assertEquals(10, PebbleGame.players.length);
    }

    @Test
    public void testStartGame() throws InterruptedException {
        InputStream stdin = System.in;  // To reset System.in after test
        InputStream in = new ByteArrayInputStream(
                ("1\ntestRes/example_file_3.csv"
                + "\ntestRes/example_file_3.csv"
                + "\ntestRes/example_file_3.csv").getBytes());
        System.setIn(in);
        PebbleGame.main(new String[0]);
        PebbleGame.players[0].join(2000);
        System.setIn(stdin);

        assertTrue(PebbleGame.gameOver);
    }

    @Test
    public void testWrongStartGameInput() throws InterruptedException {
        boolean check = PebbleGame.playerInput("-1");
        assertFalse(check);
    }
}
