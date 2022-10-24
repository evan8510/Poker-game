
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class OpenScreen extends BasicGameState {

    Image img;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        img = new Image("data/openscreen.jpg"); // opening image
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input in = gc.getInput();
        if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) { // if you click left mouse button you go into the main game
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        img.draw(); // draws image
    }

    public int getID() {
        return 0;  //this id will be different for each screen
    }

}
