package fr.dpalagi.shifumi.model;

import java.util.Calendar;
import java.util.Random;

/**
 * IAPlayer is a dedicated class to IA players in the game. Handle auto play and random part of the game.
 * Created : 27/11/2017.
 *
 * @author DPalagi
 */

public class IAPlayer extends Player
{
    // region PROPERTIES

    /* Help us handle the number of available GameAction for IA */
    private final static int MAX_ACTION_NUMBER = 2;
    private final static int MIN_ACTION_NUMBER = 0;

    // endregion

    // region METHODS

    /**
     * Select randomly one of the {@link GameAction} available in the game.
     */
    public void playRandom()
    {
        // Random part ==> seed based on current time
        Random random = new Random(Calendar.getInstance().getTimeInMillis());
        int randNumber = random.nextInt((MAX_ACTION_NUMBER - MIN_ACTION_NUMBER) + 1)
                         + MIN_ACTION_NUMBER;
        // Keep it as the one selected
        play(getAction(randNumber));
    }

    /**
     * Select a new action to do in the game based on the given number.
     *
     * @param number random number in the range of available actions counter.
     * @return a {@link RockAction} by default, or the action associated to the number.
     */
    private GameAction getAction(int number)
    {
        switch (number)
        {
            case 1:
                return new PaperAction();
            case 2:
                return new ScissorsAction();
            default:
                return new RockAction();
        }
    }

    // endregion
}
