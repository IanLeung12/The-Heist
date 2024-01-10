import java.awt.Rectangle;

public class Card extends Rectangle {
    public int val;
    public String suit;

    public Card(int val, String suit) {
        super(50, 50, 150, 210);
        this.val = val;
        this.suit = suit;

    }

    public String valToString() {
        switch (val) {
            case 1:
                return "A";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
        }
        return String.valueOf(val);
    }


}
