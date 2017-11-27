package fr.dpalagi.shifumi;

import org.junit.Before;
import org.junit.Test;

import fr.dpalagi.shifumi.model.GameAction;
import fr.dpalagi.shifumi.model.PaperAction;
import fr.dpalagi.shifumi.model.RockAction;
import fr.dpalagi.shifumi.model.ScissorsAction;

import static org.junit.Assert.assertEquals;

/**
 * Local unit testing about {@link GameAction} class and comparison during game
 */
public class GameActionTests
{
    /* Actions to compare */
    RockAction rockAction;
    PaperAction paperAction;
    ScissorsAction scissorsAction;

    @Before
    public void setup() throws Exception
    {
        rockAction = new RockAction();
        paperAction = new PaperAction();
        scissorsAction = new ScissorsAction();
    }

    @Test
    public void testNaturalWins() throws Exception
    {
        assertEquals(rockAction.versus(scissorsAction), GameAction.FightEnum.WIN);
        assertEquals(paperAction.versus(rockAction), GameAction.FightEnum.WIN);
        assertEquals(scissorsAction.versus(paperAction), GameAction.FightEnum.WIN);
    }

    @Test
    public void testNaturalLoses() throws Exception
    {
        assertEquals(rockAction.versus(paperAction), GameAction.FightEnum.LOSE);
        assertEquals(paperAction.versus(scissorsAction), GameAction.FightEnum.LOSE);
        assertEquals(scissorsAction.versus(rockAction), GameAction.FightEnum.LOSE);
    }

    @Test
    public void testDrawCases() throws Exception
    {
        assertEquals(rockAction.versus(rockAction), GameAction.FightEnum.DRAW);
        assertEquals(paperAction.versus(paperAction), GameAction.FightEnum.DRAW);
        assertEquals(scissorsAction.versus(scissorsAction), GameAction.FightEnum.DRAW);
    }
}