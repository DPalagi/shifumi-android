package fr.dpalagi.shifumi.model;

import android.support.annotation.Nullable;

/**
 * Player is the base class for every potential game player : human or IA. Can be used directly
 * as a human player.
 * Created : 27/11/2017.
 *
 * @author DPalagi
 */

public class Player
{
    // region PROPERTIES

    /**
     * Last action played by this player
     */
    protected GameAction lastAction;

    // endregion

    // region METHODS

    /**
     * Save the action as the last one played by user.
     *
     * @param action the action to play and save.
     */
    public void play(GameAction action)
    {
        if (!hasAlreadyPlayed())
        {
            lastAction = action;
        }
    }

    /**
     * Remove the last played action by this player
     */
    public void resetLastAction()
    {
        lastAction = null;
    }

    /**
     * Tell if player has already selected an action to play at this round
     *
     * @return true if already something selected, false otherwise.
     */
    public boolean hasAlreadyPlayed()
    {
        return (lastAction != null);
    }

    /**
     * Let system knows what player has played for this round
     *
     * @return the last action played by player, or null if none.
     */
    @Nullable
    public GameAction getLastAction()
    {
        return lastAction;
    }


    // endregion
}
