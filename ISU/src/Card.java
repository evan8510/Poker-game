
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author evan8510
 */
// a class to represent the cards on the screen
public class Card extends GameItem {

    protected boolean faceDown = true;
    //boolean to represent whether or not the back or front is showing of the card

    protected int suit;
    // 0 = Clubs, 1 = Spades, 2 = Hearts, 3 = Diamonds
    protected int val;
    // 0 = Ace, 1 = 2, 2 = 3, 3 = 4, 4 = 5, 5 = 6, 6 = 7, 7 = 8, 8 = 9, 9 = 10, 10 = Jack, 11 = Queen, 12 = King
    static Image back;
    //static image representing the back of the card

    //constructor to initialize the card...
    // sends the image through the parent constructor and sets the back to show by default
    public Card(int xLoc, int yLoc, int s, int v, Image i) throws SlickException {
        super(xLoc, yLoc, i);
        val = v;
        suit = s;
        faceDown = true;
    }

    //sets the back of the card to an image
    public static void setBack(Image i) throws SlickException {
        back = i;
    }

    //returns the value of a card in the perspective of blackjack
    //in which anything that is a 10 or higher is a 10 
    public int getVal() {
        if (val > 9) {
            return 10;
        } else {
            return val + 1;
        }
    }

    //overriding the draw method from the parent to draw the back of the card if facefown and the front if faceup
    @Override
    public void draw() {
        if (faceDown) {

            back.draw(x, y);
        } else {
            img.draw(x, y);
        }
    }

    //method to show card's face
    public void showFace() {
        faceDown = false;
    }

    //method to hide card's face
    public void hideFace() {
        faceDown = true;
    }

}
