package fr.dpalagi.shifumi.model;

/**
 * RockAction is the representation of "Rock" in the game.
 * Created : 27/11/2017.
 *
 * @author DPalagi
 */

public class RockAction extends GameAction
{
    // region CONSTRUCTOR
    public RockAction()
    {
        super("rock");
    }
    // endregion

    // region METHODS

    @Override
    public FightEnum versus(GameAction action)
    {
        if (action == null)
        {
            return FightEnum.WIN;
        }

        // Win
        if (action instanceof ScissorsAction)
        {
            return FightEnum.WIN;
        }
        // Draw
        else if (action instanceof RockAction)
        {
            return FightEnum.DRAW;
        }

        // By default : lose
        return FightEnum.LOSE;
    }

    // endregion
}
