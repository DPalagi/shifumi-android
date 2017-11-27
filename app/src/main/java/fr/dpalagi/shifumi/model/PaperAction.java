package fr.dpalagi.shifumi.model;

/**
 * PaperAction is the representation of "Paper" in the game.
 * Created : 27/11/2017.
 *
 * @author DPalagi
 */

public class PaperAction extends GameAction
{
    // region CONSTRUCTOR
    public PaperAction()
    {
        super("paper");
    }
    // endregion

    // region METHODS

    @Override
    public FightEnum versus(GameAction action)
    {
        // Paper > Rock : win
        if (action instanceof RockAction)
        {
            return FightEnum.WIN;
        }
        // Paper = Paper : draw
        else if (action instanceof PaperAction)
        {
            return FightEnum.DRAW;
        }

        // By default : lose
        return FightEnum.LOSE;
    }

    // endregion
}
