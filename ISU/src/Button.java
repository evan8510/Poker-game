
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author evan8510
 */
//button class for player to make choices with
public class Button extends GameItem {

    // text of the button
    String name;
    //location of the text
    int xPos;

    //constructor  that creates the button, names it, and specifies where the text should be
    public Button(int xLoc, int yLoc, Image i, String n) {
        super(xLoc, yLoc, i);
        name = n;
        xPos = (300 - name.length() * 12) / 2;
    }

    //Same constructor as above however this one has the image data by default
    public Button(int xLoc, int yLoc, String n) throws SlickException {
        super(xLoc, yLoc, new Image("data/button.png"));
        name = n;
        xPos = (300 - name.length() * 12) / 2;
    }

    // overridden draw method that draws the image and text using the font
    @Override
    public void draw() {
        img.draw(x, y);
        font.drawString(x + xPos, y + (img.getHeight() / 2) - 10, name);
    }

    // custom methond to see whether or not the button is pressed or not
    public boolean isPressed(int mx, int my) {
        return (mx > x && mx < (x + img.getWidth()) && my > y && my < (y + img.getHeight()));
    }
}
