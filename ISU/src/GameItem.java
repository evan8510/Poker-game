
import org.newdawn.slick.Image;
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
//object that represents all Items in the game
public abstract class GameItem {

    protected int x, y; // x & y location of the object
    protected Image img; // Image file to be drawn
    static TrueTypeFont font; // a font to draw strings
    protected boolean shown; // whether or not one can see the item at that point

    //constructor that sends in the image file as well as the location
    public GameItem(int xLoc, int yLoc, Image i) {
        x = xLoc;
        y = yLoc;
        img = i;
        font = new TrueTypeFont(new java.awt.Font("Elephant", 0, 20), true);
        shown = true;
    }

    //constructor that only sents in teh location
    public GameItem(int xLoc, int yLoc) {
        x = xLoc;
        y = yLoc;
    }

    //static method to send the font
    public static TrueTypeFont getFont() {
        return font;
    }

    //Drawing the items
    public abstract void draw();

    //Moving the items to a location
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //to get whether or not the item is shown
    public boolean isShown() {
        return shown;
    }

    // to set whether or not the item is shown
    public void setShown(boolean b) {
        shown = b;
    }
}
