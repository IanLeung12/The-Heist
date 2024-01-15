
import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InterruptedException {

//        MenuVis mv = new MenuVis();
//        while (!mv.start) {
//            mv.refresh();
//            Thread.sleep(10);
//        }
//        mv.refresh();

        ArrayList<Integer> deck = new ArrayList<>(13);



//        Visualizer visualizer = new Visualizer(deck);
//        while (true) {
//            visualizer.refresh();
//            Thread.sleep(1);
//        }

        int TRIALS = 1000000;
        int totalWinnings = 0;

        for (int i = 0; i < TRIALS; i++) {
            deck.clear();
            deck.add(-1);
            for (int j = 1; j < 13; j++) {
                deck.add(j);
            }
            boolean playing = true;
            int sum = 0;
            int pulls = 0;
            while (playing) {
                int pos = (int) (Math.random() * deck.size());
                int pull = deck.remove(pos);
                if (pull == -1) {
                    playing = false;
                } else {
                    sum += pull;
                }
                pulls++;
                if (pulls >= 5) {
                    totalWinnings += sum;
                    playing = false;
                }
            }
        }
        int totalSpent = 20 * TRIALS;
        System.out.println("Total Winnings: $" + totalWinnings);
        System.out.println("Total Spent: $" + totalSpent);
        System.out.println("Profit Margin: " + Math.round((double) totalWinnings/totalSpent * 10000.0)/10000.0 + "x");




    }
}