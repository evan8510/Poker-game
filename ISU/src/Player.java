
import java.util.ArrayList;
import org.newdawn.slick.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author evan8510
 */
//abstract class that extends to dealer and user...
//a general layout of what a player must do
public abstract class Player extends GameItem {

    protected ArrayList<Card> hand = new ArrayList();
    // arraylist of the cards in the players hand
    protected int money;
    // how much money the player has
    protected String name;
    // the players name

    //uses constructor that sends in just the locations since the images are defined in the children
    //also defines name and amount of money
    public Player(String n, int m, int xLoc, int yLoc) {
        super(xLoc, yLoc);

        name = n;
        money = m;
    }

    // to give a card to a player
    final void give(Card g) {
        hand.add(g);
    }

    // getter to see how many cards the player has
    final int getNumCards() {
        return hand.size();
    }

    // removes a card from a player and returns what it was
    Card remove(int i) {
        return hand.remove(i);
    }

    // checks if the player has gone over 21
    public boolean isBusted() {
        return getMaxVal() > 21;
    }

    // checks if the player has run out of money yet
    public boolean isBankrupt() {
        return money < 0;
    }

    // gets the exact amount a player has
    public int getMoney() {
        return money;
    }

    //abstract method to show hand...
    public abstract void showHand();

    // overriden draw method that shows the hand draws the profile img as well as the name and amount of money
    @Override
    public void draw() {
        showHand();
        img.draw(x, y);
        font.drawString(x, y + 225, name);
        font.drawString(x, y + 245, "MONEY:                 " + money);
    }

    // returns the maximum possible value the cards could represent without going over 21
    public int getMaxVal() {
        int i = 0;
        int n = 0;
        for (Card card : hand) {
            i += card.getVal();
            if (card.getVal() == 1) {
                n += 1;
            }
        }

        while (n > 0) {
            if (i + 10 <= 21) {
                i += 10;
            }
            n--;
        }

        return i;
    }

    //bets once and returns the amount it bets "1"
    final public int bet() {
        money -= 1;
        return 1;
    }

    //adds money to the players account
    final public void win(int p) {
        money += p;
    }

}
