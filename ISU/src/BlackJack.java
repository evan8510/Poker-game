
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class BlackJack extends BasicGameState {

    // declaring deck, players, buttons, etc.
    Deck d;
    User u;
    Dealer op;
    ArrayList<Player> players;

    ArrayList<Button> buttons;

    int pot; // amount of money up for grabs
    Image table;
    Button hitButton;
    Button betButton;
    Button foldButton;
    Button standButton;
    Button matchButton;
    Button endButton;

    boolean matchState;
    boolean userTurn;
    boolean standState;
    boolean contState;
    int raise;

    Input in;

    // Initializes all of the variables and oragnizes buttons and players into two seperate ArrayLists
    // also deals the first to cards to the players and sets the pot to two
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        buttons = new ArrayList<Button>();
        d = new Deck();
        u = new User("User", 9);
        op = new Dealer("Dealer", 9);
        table = new Image("data/table.jpg");

        matchState = false;
        userTurn = true;
        contState = false;
        d.shuffle(200);
        pot = 2;
        players = new ArrayList<Player>();
        players.add(u);
        players.add(op);
        d.deal(2, players);

        buttons.add(hitButton = new Button(375, 615, new Image("data/button.png"), "Hit"));
        buttons.add(betButton = new Button(375, 700, new Image("data/button.png"), "Raise"));
        buttons.add(endButton = new Button(790, 700, new Image("data/button.png"), "End Turn"));
        buttons.add(standButton = new Button(790, 615, new Image("data/button.png"), "Stand"));
        buttons.add(matchButton = new Button(375, 660, new Image("data/button.png"), "Match?"));
        buttons.add(foldButton = new Button(790, 660, new Image("data/button.png"), "Fold"));
        standButton.setShown(true); //sets visible

    }
    //the logic of the game

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        // checks to see if any player is bankrupt and ends the game once they do
        // endscreen is different depending on who wins
        if (u.isBankrupt()) {
            MainGame.whoWins = true;
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }
        if (op.isBankrupt()) {
            MainGame.whoWins = false;
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }

        // gets input and checks where the mouse is
        in = gc.getInput();
        int mx = in.getMouseX();
        int my = in.getMouseY();

        // sets it so these buttons are only visible once the dealer has raised
        matchButton.setShown(matchState);
        foldButton.setShown(matchState);

        // sets it so these buttons are invinsible once the dealer has raised
        // also sets it so that once people are at 0 they can't keep betting
        // thus no negatuve chips
        //stand is scattered as it has the interesting feature that it should only be visible for the first move of a turn
        hitButton.setShown(!matchState);
        betButton.setShown(!matchState && op.getMoney() > 0 && u.getMoney() > 0);
        endButton.setShown(!matchState);

        //so that one can see the outcome of the hand and press enter when they are ready to proceed
        // also resets the table & pot
        if (contState) {

            pot = 0;
            if (in.isKeyPressed(Input.KEY_ENTER)) {
                contState = false;
                standButton.setShown(true);
                reset();
            }
            // takes in input for the left mouse button
        } else if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            op.erasedMove(); // resets dMove to ""

            // if the player must respond to the dealers raise only those buttons are shown.
            // takes input on if the player folds or matches and adjusts the accounts and pot accordingly
            // it also resets the board and changes the state away from matchstate
            if (matchState) {
                standButton.setShown(false);
                if (matchButton.isPressed(mx, my)) {
                    pot += u.bet();
                    standButton.setShown(true);
                } else if (foldButton.isPressed(mx, my)) {
                    op.win(pot);
                    reset();
                    standButton.setShown(true);
                }
                matchState = false;

            } else {
                // for when it is the users turn it looks to see which button the user presses and acts accordingly
                if (userTurn) {
                    if (hitButton.isPressed(mx, my)) {
                        // adds a card and hides stand
                        d.hit(u);
                        standButton.setShown(false);
                    }
                    if (betButton.isPressed(mx, my)) {
                        //increases bet and hides stand
                        pot += u.bet();
                        raise += 1;
                        standButton.setShown(false);
                    }
                    if (standButton.isPressed(mx, my)) {
                        // makes the dealer reveal their cards...
                        op.showAll();
                        // if they are both below 21  whoever has a higher hand takes the pot
                        // if it is the same value they split the pot
                        if (!op.isBusted() && !u.isBusted()) {
                            if (op.getMaxVal() > u.getMaxVal()) {
                                op.win(pot);

                                op.appenddMove("I won the pot! ");
                            } else if (u.getMaxVal() > op.getMaxVal()) {
                                u.win(pot);
                                op.appenddMove("You won the pot! ");

                            } else {
                                op.appenddMove("We got the same value... We'll split the pot ");
                                u.win(pot / 2);
                                op.win(pot / 2);

                            }
                            // if both players bust they split the pot
                        } else if (op.isBusted() && u.isBusted()) {
                            op.appenddMove("We both busted... We'll split the pot ");
                            u.win(pot / 2);
                            op.win(pot / 2);
                            // if the dealer busts user wins the pot
                        } else if (op.isBusted()) {
                            u.win(pot);
                            op.appenddMove("You won the pot! ");
                            // if the user busts the dealer gets the pot
                        } else if (u.isBusted()) {
                            op.win(pot);
                            op.appenddMove("I won the pot! ");

                        }

                        // enters the continue state so that the player can reflect on the results
                        contState = true;
                        op.appenddMove("Press enter to continue");

                    }
                    //if the end turn button is pressed it becomes the dealers turn
                    if (endButton.isPressed(mx, my)) {
                        userTurn = false;
                    }
                }
            }
        }

        // the dealers turn
        // evaluates if the dealer will match the raise of the player or if they will fold
        if (!userTurn) {
            standButton.setShown(true);
            userTurn = true;
            if (raise > 0) {
                if (op.willMatch()) {
                    for (i = 0; i < raise; i++) {
                        pot += op.bet();
                    }
                    raise = 0;
                } else {
                    contState = true;
                    u.win(pot);
                    pot = 0;
                    return;

                }

                // evaluates if the dealer will hit or not
            }
            if (op.willHit()) {
                d.hit(op);

            }
            // evaulates if the dealer will raise or not
            if (op.willRaise(u.getMoney())) {
                pot += op.bet();
                matchState = true;

                standButton.setShown(false);

            }

        }
    }

    // draws alll of the game items
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        table.draw(0, 0); // draws the background that is the table

        d.drawCards(); // deck draws all the cards

        for (Player player : players) { // players draws both players, they draw their hands
            player.draw();
        }

        for (GameItem item : buttons) { // buttons draws all of the buttons that are shown at that time
            if (item.isShown()) {
                item.draw();
            }
        }

        GameItem.getFont().drawString(10, 400, "Pot: " + pot); // gets the font from gameItem to draw how much money is in the pot
    }

    // method to reset the board...
    public void reset() {
        Card temp;
        // sends the players cards back to the deck
        for (Player player : players) {
            int i = player.getNumCards();

            while (i > 0) {
                i -= 1;
                temp = player.remove(i);
                temp.hideFace();
                d.add(temp);
            }
            pot += player.bet(); // makes the players each bet once in order to play
        }
        d.deal(2, players); // deals 2 new cards to the players
        raise = 0;

    }

    public int getID() {
        return 1;  //this id will be different for each screen
    }

}
