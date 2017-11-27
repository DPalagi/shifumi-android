package fr.dpalagi.shifumi.model;

/**
 * GameAction is the base class of what a user can play during the game. Subclasses have to implements base methods
 * in order to be used in game.
 * Created : 27/11/2017.
 *
 * @author DPalagi
 */

public abstract class GameAction
{
    // region PROPERTIES

    /**
     * Name of the action to play
     */
    protected String name;

    // endregion

    // region CONSTRUCTOR
    public GameAction(String name)
    {
        this.name = name;
    }
    // endregion

    // region METHODS

    /**
     * Defined in each subclass of {@link GameAction}. Let know if the current action is stronger, equal
     * or weaker than the one proposed. If no rules is implemented, return -1 (loose) by default.
     *
     * @param action the action proposed played against the current one.
     * @return {@link FightEnum#WIN}, {@link FightEnum#DRAW} {@link FightEnum#LOSE}
     */
    public abstract FightEnum versus(GameAction action);

    public String getName()
    {
        return name;
    }

    // endregion

    // region STATUS ENUM

    public enum FightEnum
    {
        WIN,
        DRAW,
        LOSE
    }

    // endregion
}
