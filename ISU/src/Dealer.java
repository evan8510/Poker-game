
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author evan8510
 */
// Dealer class extends from player  and is the opponent of the game
// dealer makes decisions in order to win the game
public class Dealer extends Player {

    private String dMove; // to say what the dealer did

    // sends x&y, name, and money to the parent constructor
    // declares that dealericon is the img
    public Dealer(String n, int m) throws SlickException {
        super(n, m, 10, 50);
        img = new Image("data/dealericon.jpg");
        dMove = "";
    }

    // uses the parent draw to draw itself
    // adds on the font to say what it did last move
    public void draw() {
        super.draw();
        font.drawString(10, 350, dMove);
    }

    //  used to display the face of the cards in the hand
    public void showAll() {
        for (Card card : hand) {
            card.showFace();

        }
    }

    // used to get the smalllest possible value of the hand
    // ie - making ace = 1
    public int getMinVal() {
        int i = 0;
        for (Card card : hand) {
            i += card.getVal();
        }

        return i;
    }

    // method used to reset dMove
    public void erasedMove() {
        dMove = "";
    }

    // method used to add text to dMove
    public void appenddMove(String s) {
        dMove += s;
    }

    // method to decide when the bot will hit or not
    public boolean willHit() {
        boolean b = (getMinVal() < 15 && getMaxVal() < 17);
        if (b) {
            dMove += "I will hit! ";
        } else {
            dMove += "I will not hit! ";
        }
        return b;
    }

    // method to decide when the bot will raise or not
    public boolean willRaise(int i) {
        if (i > 0) {

            if ((getMaxVal() + money >= 28 && money > 0 && getMaxVal() < 22) || getMaxVal() == 21) {
                dMove += "I will raise! ";
                return true;
            }

        }
        dMove += "I will not raise! ";
        return false;
    }

    // method to decide whether or not the dealer will fold against the player or match their bet
    public boolean willMatch() {
        boolean b = (getMaxVal() > 17 || getMinVal() < 12 || getMaxVal() < 12);
        if (b) {
            dMove += "I will match! ";
        } else {
            dMove += "I will fold! Redealt, Press enter to continue! ";
        }
        return b;
    }

    //custom method to show the hand but only the face of the first card as this is how blackjack is played.
    @Override
    public void showHand() {
        int xp = 250;
        hand.get(0).showFace();
        for (Card card : hand) {
            card.move(xp, 50);
            card.draw();
            xp += 100;
        }
    }

}
