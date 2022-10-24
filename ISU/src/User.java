
import javax.swing.plaf.basic.BasicGraphicsUtils;
import net.java.games.input.Component;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
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
//extension of the player class
public class User extends Player {

    //Sends the name, amount of money, and x&y location to the parent constructor
    // sets the image to the playericon
    public User(String n, int m) throws SlickException {
        super(n, m, 10, 500);
        img = new Image("data/playericon.jpg");
    }

    //overriding the showHand method so that it shows the face of all of the cards
    @Override
    public void showHand() {
        int x = 250;
        for (Card card : hand) {
            card.showFace();
            card.move(x, 500);
            x += 100;
            card.draw();
        }
    }

    // extension of the parent's draw method adding on an indicator to see what yout maximum card value is without going over 21
    public void draw() {
        super.draw();
        //uses the static 'font' variable from GameItem to accomplish this
        if (!isBusted()) {
            font.drawString(x, y + 265, "TOTAL:                  " + getMaxVal());
        } else {
            font.drawString(x, y + 265, "TOTAL:           BUSTED");
        }
    }

}
