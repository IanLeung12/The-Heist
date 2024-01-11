
import java.util.ArrayList;
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

        //deck.add(-1);
        for (int i = 1; i < 13; i ++) {
            deck.add(i);

        }

        Visualizer visualizer = new Visualizer(deck);
        while (true) {
            visualizer.refresh();
            Thread.sleep(1);
        }
//
//        boolean inPlay = true;
//        int sum = 0;
//        Scanner input = new Scanner(System.in);
//        while (inPlay) {
//            int pos = (int) (Math.random() * deck.size());
//            int pull = deck.remove(pos);
//            if (pull == -1) {
//                inPlay = false;
//                sum = 0;
//                System.out.println("You pulled a bomb and died.");
//            } else {
//                sum += pull;
//                System.out.println("You pulled a " + pull + ".");
//                System.out.println("Total Sum: " + sum);
//                System.out.println("Cards left: " + deck.size());
//                System.out.println("Do you wish to keep playing? (y/n)");
//                if (!input.next().equals("y")) {
//                    inPlay = false;
//                }
//            }
//        }
//
//        System.out.println("You left with $" + sum);
//



    }
}