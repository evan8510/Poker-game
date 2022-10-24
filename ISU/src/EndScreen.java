
import org.newdawn.slick.BigImage;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class EndScreen extends BasicGameState {

    Image img;
    static TrueTypeFont font;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        img = new Image("data/endscreen.jpg"); // endscreen image gets shown
        font = new TrueTypeFont(new java.awt.Font("Elephant", 0, 40), true);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        img.draw(); // draws the endscreen image
        if (MainGame.whoWins) {
            font.drawString(400, 300, "YOU LOST TO THE BOT!");
        } else // if user wins it says congrats you beat the bot
        // if user loses it says you lost to the bot
        {
            font.drawString(400, 200, "CONGRATS YOU BEAT THE BOT!");
        }

    }

    public int getID() {
        return 2;  //this id will be different for each screen
    }

}
