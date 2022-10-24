
import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

// object to manage all of the cards in the deck
public class Deck {

    static SpriteSheet csprite; //spritesheet of the images of the cards
    ArrayList<Card> cards; // arraylist of all cards in deck

    // constructs all of the cards by sending in the subImage from the sprite sheet using a nested for loop
    // also set the static image that is the back of the cards
    public Deck() throws SlickException {
        cards = new ArrayList<Card>();
        csprite = new SpriteSheet("data/cards.png", 72, 96);
        csprite.startUse();
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                cards.add(new Card(0, 0, j, i, csprite.getSubImage(i, j)));
            }
        }
        Card.setBack(csprite.getSubImage(13, 0));
        csprite.endUse();
    }

    // draws all of the cards in the deck at that time
    public void drawCards() {

        for (Card card : cards) {
            card.move(1100, 50);
            card.draw();

        }
    }

    // adds a card to the deck
    public void add(Card c) {
        cards.add(c);
    }

    // shuffles the cards in the deck by swapping the locations of two random cards
    public void shuffle(int x) {
        for (int i = 0; i < x; i++) {
            swap((int) (Math.random() * 52), (int) (Math.random() * 52), cards);
        }
    }

    // deals out a number of cards to all players in teh game
    public void deal(int numCards, ArrayList<Player> players) {
        for (Player player : players) {
            for (int i = 0; i < numCards; i++) {
                player.give(cards.remove(0));
            }
        }
    }

    //method to give a card to the player so they can keep it in their hand
    public void hit(Player p) {
        p.give(cards.remove(0));
    }

    //method used to swap the cards
    void swap(int a, int b, ArrayList<Card> arr) {
        // sends in two random locations in the array and swaps the cards in them
        Card temp = arr.get(a);
        arr.set(a, arr.get(b));
        arr.set(b, temp);
    }

}
