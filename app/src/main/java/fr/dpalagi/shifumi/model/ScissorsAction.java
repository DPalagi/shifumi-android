package fr.dpalagi.shifumi.model;

/**
 * ScissorsAction representation of "Scissors" in the game.
 * Created : 27/11/2017.
 *
 * @author DPalagi
 */

public class ScissorsAction extends GameAction
{
    // region CONSTRUCTOR
    public ScissorsAction()
    {
        super("scissors");
    }
    // endregion

    // region METHODS

    @Override
    public FightEnum versus(GameAction action)
    {
        // Win
        if (action instanceof PaperAction)
        {
            return FightEnum.WIN;
        }
        // Draw
        else if (action instanceof ScissorsAction)
        {
            return FightEnum.DRAW;
        }
        // By default : lose
        return FightEnum.LOSE;
    }

    // endregion
}
