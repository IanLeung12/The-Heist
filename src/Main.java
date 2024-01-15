
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InterruptedException {

        MenuVis mv = new MenuVis();
        while (!mv.start) {
            mv.refresh();
            Thread.sleep(10);
        }
        mv.refresh();

        ArrayList<Integer> deck = new ArrayList<>(13);

        Visualizer visualizer = new Visualizer(deck);
        while (true) {
            visualizer.refresh();
            Thread.sleep(1);
        }
//        HashMap<Integer, Long> outcomes = new HashMap<>();
//        outcomes.put(0, 0L);
//        Random random = new Random();
//        for (int i = 15; i < 61; i ++) {
//            outcomes.put(i, 0L);
//        }
//        int TRIALS = 100;
//        long totalWinnings = 0L;
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < TRIALS; i++) {
//            deck.clear();
//            for (int j = 1; j < 13; j++) {
//                deck.add(j);
//            }
//            deck.add(random.nextInt(deck.size() + 1), -1);
//
//            boolean playing = true;
//            int sum = 0;
//            int pulls = 0;
//            while (playing) {
//                int pull = deck.get(random.nextInt(deck.size()));
//
//                if (pull == -1) {
//                    playing = false;
//                    outcomes.put(0, outcomes.get(0) + 1L);
//
//                } else {
//                    sum += pull;
//                    pulls++;
//                    deck.remove((Integer) pulls);
//                    if (pulls >= 5) {
//                        totalWinnings += sum;
//                        outcomes.put(sum, outcomes.get(sum) + 1L);
//
//                        playing = false;
//                    }
//                }
//
//            }
//        }
//        int totalSpent = 30 * TRIALS;
//        System.out.println("Total Winnings: $" + totalWinnings);
//        System.out.println("Total Spent: $" + totalSpent);
//        System.out.println("Profit Margin: " + Math.round((double) totalWinnings / totalSpent * 10000.0) / 10000.0 + "x");
//        System.out.printf("Trials: %,d%n", TRIALS);
//        System.out.println("Time: " + (System.currentTimeMillis() - start) / 1000.0 + "s");
//        System.out.println(outcomes);
//        System.out.println(outcomes.keySet());
//        System.out.println(outcomes.values());

    }
}