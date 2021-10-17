import java.util.Random;
public class PebbleGame {

    public static class Player extends Thread {

    }

    public static int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

    public static void main(String[] args) {

    }
}
