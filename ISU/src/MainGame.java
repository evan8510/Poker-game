
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainGame extends StateBasedGame {

    static boolean whoWins; // static variable used to indicate which screen to show in the endscreen

    public MainGame(String title) {
        super(title);
    }

    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new OpenScreen()); // introscreen 
        this.addState(new BlackJack()); // actual game
        this.addState(new EndScreen()); // endscreen
    }

    public static void main(String args[]) throws SlickException {
        MainGame game = new MainGame("BlackJack");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(1200, 800, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(100);
        app.start();
    }

}
