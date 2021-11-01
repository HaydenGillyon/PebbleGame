import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Represents the pebble game as a whole.
 * @author Hayden Gillyon
 * @author Jorel Coutinho
 */
public class PebbleGame {

    static BlackBag[] blackBags = {new BlackBag("X"), new BlackBag("Y"), new BlackBag("Z")};
    static WhiteBag[] whiteBags = {new WhiteBag("A"), new WhiteBag("B"), new WhiteBag("C")};
    static boolean gameOver = false;
    static Player[] players;
    static Player playerWon;

    /**
     * Represents a player in the pebble game.
     */
    public static class Player extends Thread {
        private static final String playerTemplate = "player";
        private int bagIndex;
        private ArrayList<Bag.Pebble> pebbles;
        private String name;
        private int playerIndex;
        private File playerFile;

        Player(int index){
            playerIndex = index;

        }

        public void run() {

            pebbles = new ArrayList<>();
            // Todo - Add first 10 pebbles

            // Create player name and file
            name = playerTemplate + (playerIndex + 1);
            playerFile = new File("game_output/" + name + "_output.txt");

            try {
                playerFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
            try {
                players.wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
            while (true) {
                bagIndex = getRandomInt(0, 3);
                // TAKE PEBBLE
                Bag.Pebble takenPebble = blackBags[bagIndex].takePebble();
                pebbles.add(takenPebble);
                String takePebble = name + " has drawn a " + takenPebble.getWeight() + " from bag " + blackBags[bagIndex].getName();


                // AFTER PEBBLE TAKE
                if (getTotalWeight(pebbles) == 100) {
                    synchronized (this) {
                        if (!gameOver) {
                            gameOver = true;
                            String winner = name + " has won \uD83C\uDF89 \uD83C\uDF89\uD83C\uDF89";
                            // TODO - Write to screen
                            writeFile(playerFile , winner );
                        }
                    }
                }

                // DISCARD PEBBLE
                int discardIndex = getRandomInt(0, pebbles.size());
                whiteBags[bagIndex].discardPebble(pebbles.remove(discardIndex));
                if(gameOver){
                    break;
                }

            }
        }

        // Methods

        /**
         * Returns the total weight of all pebbles in the pebble list.
         * @param p pebble list
         * @return combined weight of all pebbles
         */
        private int getTotalWeight(ArrayList<Bag.Pebble> p) {
            int countWeight = 0;
            for (Bag.Pebble pebble : p) {
                countWeight += pebble.getWeight();
            }
            return countWeight;
        }
    }

    /**
     * Generates a random integer between min and max.
     *
     * @param min the minimum value (inclusive)
     * @param max the maximum value (exclusive)
     * @return random integer
     */
    public static int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

    // IO file

    /**
     * Reads csv file containing weights of pebbles and returns the
     *
     * @param file name of pebbles file to be read
     * @return list of pebble weights as strings
     * @throws IOException when file exceeds 4,000,000 bytes
     */
    public static ArrayList<String> readFile(String file) throws IOException {
        File csv = new File(file);
        ArrayList<String> values = new ArrayList<>();
        String line;

        if (csv.length() > 4000000) {
            throw new IOException("File exceeds 4,000,000 bytes");
        }
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            while ((line = br.readLine()) != null) {
                values.addAll(Arrays.asList(line.split(",")));
            }
            // Throw IOException if input CSV contains non-numeric values
            Pattern pattern = Pattern.compile("\\d+");
            if (!values.stream().allMatch(t -> pattern.matcher(t).matches())){
                throw new IOException("Input Csv contains non-numeric values");
            }
        }
        return values;
    }

    /**
     * Writes the given string to the provided player file and adds a new line
     *
     * @param playerFile  player file to be written to
     * @param output player status update to be written out
     */
    public static void writeFile(File playerFile, String output) {

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(playerFile))) {
            fileWriter.write(output);
            fileWriter.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Converts list of pebble weights as strings to a list of pebbles with these weight values.
     *
     * @param initialPebbles list of pebble weights as strings
     * @return list of pebbles with specified weights
     */
    public static ArrayList<Bag.Pebble> csvToPebbleList(ArrayList<String> initialPebbles) {
        ArrayList<Bag.Pebble> pebbles = new ArrayList<>();
        for (String initialPebble : initialPebbles) {
            Bag.Pebble newPebble = new Bag.Pebble(Integer.parseInt(initialPebble));
            pebbles.add(newPebble);
        }
        return pebbles;
    }

    /**
     * Main class where program executes.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Assigning white bags to black bags
        assignBags();
    }

    /**
     * Assigns each black bag to it's corresponding white bag.
     */
    public static void assignBags() {
        for (int i = 0; i < blackBags.length; i++) {
            blackBags[i].setWhiteBag(whiteBags[i]);
        }
    }

    public static boolean playerInput(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        if (pattern.matcher(input).matches()) {
            int playerCount = Integer.parseInt(input);
            if (playerCount <= 100 ) {
            players = new Player[playerCount];
            return true;
            } else {
                System.out.println(" Player less than or equal to a 100");
                return false;
            }
        } else if (input.equalsIgnoreCase("e")) {
            System.exit(0);
        }
        System.out.println("please enter valid numerical positive input");
        return false;
    }

    public static boolean checkCsvInput(String input, BlackBag bagInput) throws IOException {
        ArrayList<String> csvInput;
        try {
            csvInput = readFile(input);
            if (csvInput.size() <= 11 * players.length){
                ArrayList<Bag.Pebble> pebbles = csvToPebbleList(csvInput);
                bagInput.setPebbles(pebbles);
                return true;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void setupGame() {
        for (int i = 0 ; i < players.length; i++ ){
            players[i] = new Player(i);
            players[i].start();
        }
        Thread object = new Thread();
        object.notifyAll();
    }

    /**
     * Deletes all player output files in the game_output directory.
     */
    private static void cleanGameOutput() {
        File outputPath = new File("game_output/");
        FilenameFilter filter = (path, s) -> s.endsWith("_output.txt");
        File[] filesToDelete = outputPath.listFiles(filter);

        if (filesToDelete == null) {
            System.out.println("An I/O error occurred.");
            System.exit(1);
        }

        for (File f : filesToDelete) {
            f.delete();
        }
    }

    /**
     * Sets up the game, collecting the configuration from the user and instantiating pebbles, bags, and players.
     * @throws IOException when unexpected problem with IO arises
     */
    public static void startGame() throws IOException {
        String Menu = """
               Welcome to the PebbleGame!!
               You will be asked to enter the number of players.
               and then for the location of three files in turn containing comma seperated integer values for the pebble weights.
               The integer values must be strictly positive.
               The game will then be simulated, and output written to files in this directory.
               
               Please enter the number of players:
               """;

        String pleaseEnter1 = """
                Please enter location of bag number 0 to load:
                """;

        String pleaseEnter2 = """
                Please enter location of bag number 1 to load:
                """;

        String pleaseEnter3 = """
                Please enter location of bag number 2 to load:
                """;
        System.out.println(Menu);
        Scanner input = new Scanner(System.in);
        while (true) {
            String players = input.next();
            if (playerInput(players)) break;
        }
        System.out.println(pleaseEnter1);
        while(true) {
            String bag0 = input.next();
            if (checkCsvInput(bag0, blackBags[0])) break;
        }
        System.out.println(pleaseEnter2);
        while(true) {
            String bag1 = input.next();
            if (checkCsvInput(bag1, blackBags[1])) break;
        }
        System.out.println(pleaseEnter3);
        while(true) {
            String bag2 = input.next();
            if (checkCsvInput(bag2, blackBags[2])) break;
        }

        setupGame();

        cleanGameOutput();
    }
}
