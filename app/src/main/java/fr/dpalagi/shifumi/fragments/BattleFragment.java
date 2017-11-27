package fr.dpalagi.shifumi.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import fr.dpalagi.shifumi.R;
import fr.dpalagi.shifumi.model.GameAction;
import fr.dpalagi.shifumi.model.IAPlayer;
import fr.dpalagi.shifumi.model.PaperAction;
import fr.dpalagi.shifumi.model.Player;
import fr.dpalagi.shifumi.model.RockAction;
import fr.dpalagi.shifumi.model.ScissorsAction;

/**
 * BattleFragment is where the battle happens. Contains all code to interact with user during the game: UI, animations etc...
 * Created : 25/11/2017.
 *
 * @author DPalagi
 */

public class BattleFragment extends Fragment
{
    // region PROPERTIES

    /**
     * Actions available for first player if human
     */
    private ImageButton firstPlayerRock, firstPlayerPaper, firstPlayerScissors;

    /**
     * Actions available for second player (IA)
     */
    private ImageButton secondPlayerRock, secondPlayerPaper, secondPlayerScissors;

    /**
     * Allow user to start/re-start a game
     */
    private ImageButton gameControlButton;

    /**
     * Icon representing first player
     */
    private ImageView firstPlayerIcon;

    /**
     * Icons to display at end of the game
     */
    private ImageView drawIcon, firstCup, secondCup;

    /**
     * Text to display as timer
     */
    private TextView timerText;

    /**
     * Players of the game; can be human or IA.
     */
    private Player firstPlayer, secondPlayer;

    /**
     * Tell at config time if this fragment will handle a human player or not
     */
    private boolean hasHuman;

    private CountDownTimer timer;

    /* Keys to pass data into fragment instance */
    private static final String HAS_HUMAN_KEY = "has_human_in_game";
    private static final int TIMER_MILLI_SEC = 3000;

    // endregion

    // region EVENTS

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.fragment_battle, container, false);
        // Get UI elements
        firstPlayerRock = layout.findViewById(R.id.battle_fp_rock);
        firstPlayerPaper = layout.findViewById(R.id.battle_fp_paper);
        firstPlayerScissors = layout.findViewById(R.id.battle_fp_scissors);
        secondPlayerRock = layout.findViewById(R.id.battle_sp_rock);
        secondPlayerPaper = layout.findViewById(R.id.battle_sp_paper);
        secondPlayerScissors = layout.findViewById(R.id.battle_sp_scissors);
        firstPlayerIcon = layout.findViewById(R.id.battle_fp_icon);
        gameControlButton = layout.findViewById(R.id.battle_game_action);
        timerText = layout.findViewById(R.id.battle_timer);
        drawIcon = layout.findViewById(R.id.battle_equality_icon);
        firstCup = layout.findViewById(R.id.battle_first_cup);
        secondCup = layout.findViewById(R.id.battle_second_cup);

        // Get game action ready to go
        gameControlButton.setImageResource(R.drawable.ic_play_circle_outline);

        // Extract config params ==> consider that user can play by default
        Bundle args = getArguments();
        hasHuman = args.getBoolean(HAS_HUMAN_KEY, true);

        // Setup UI and event listeners
        setupUI(hasHuman);

        // Create players
        if (hasHuman)
        {
            firstPlayer = new Player();
        }
        else
        {
            firstPlayer = new IAPlayer();
        }
        secondPlayer = new IAPlayer();

        return layout;
    }


    // endregion

    // region METHODS

    /**
     * Put UI into clean state according to params passed at creation time
     *
     * @param forHuman if true, displays human as player and let user interacts with P1 actions
     */
    private void setupUI(boolean forHuman)
    {
        // Icon and listeners actually triggers something
        if (forHuman)
        {
            firstPlayerIcon.setImageResource(R.drawable.ic_person);
            // Listeners
            firstPlayerRock.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    firstPlayer.play(new RockAction());
                }
            });
            firstPlayerPaper.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    firstPlayer.play(new PaperAction());
                }
            });
            firstPlayerScissors.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    firstPlayer.play(new ScissorsAction());
                }
            });
        }
        else
        {
            firstPlayerIcon.setImageResource(R.drawable.ic_robot);
        }

        // Listeners for game action
        gameControlButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startTimer();
            }
        });
    }

    /**
     * Start the chrono of 4 secs and define actions related to him
     */
    private void startTimer()
    {
        if (timer == null)
        {
            timer = new CountDownTimer(TIMER_MILLI_SEC, 1000)
            {
                @Override
                public void onTick(long l)
                {
                    timerText.setText("" + l / 1000);
                }

                @Override
                public void onFinish()
                {
                    // Let P2 play
                    if (secondPlayer instanceof IAPlayer)
                    {
                        ((IAPlayer) secondPlayer).playRandom();
                    }

                    // Hide timer
                    timerText.setVisibility(View.GONE);
                    // Display again
                    gameControlButton.setVisibility(View.VISIBLE);
                    announceRoundResult();
                }
            };
        }

        // Clean UI state
        gameControlButton.setVisibility(View.INVISIBLE);
        timerText.setVisibility(View.VISIBLE);

        drawIcon.setVisibility(View.GONE);
        firstCup.setVisibility(View.GONE);
        secondCup.setVisibility(View.GONE);

        // Reset users actions
        firstPlayer.resetLastAction();
        secondPlayer.resetLastAction();

        // First player plays first
        if (firstPlayer instanceof IAPlayer)
        {
            ((IAPlayer) firstPlayer).playRandom();
        }

        // Start count down
        timer.start();
    }

    /**
     * Compare the GameAction of each player to tell who wins, loses or if there is a draw
     */
    private void announceRoundResult()
    {
        // Last actions of each player, to compare
        GameAction fpAction = firstPlayer.getLastAction();
        GameAction spAction = secondPlayer.getLastAction();

        // First player has played something ==> look at result
        if (firstPlayer.hasAlreadyPlayed())
        {
            switch (fpAction.versus(spAction))
            {
                case WIN:
                    updateUI(true, false);
                    break;
                case DRAW:
                    updateUI(false, true);
                    break;
                case LOSE:
                    updateUI(false, false);
                    break;
            }
        }
        // First player didn't play ==> draw or loose
        else
        {
            // Draw
            if (!secondPlayer.hasAlreadyPlayed())
            {
                updateUI(false, true);
            }
            // Lose
            else
            {
                updateUI(false, false);
            }
        }
    }

    /**
     * Update the UI according to round result
     *
     * @param firstWin if true, first player has won. If false, can be draw or second player won
     * @param drawGame if true, it's a draw game.
     */
    private void updateUI(boolean firstWin, boolean drawGame)
    {
        if (firstWin)
        {
            firstCup.setVisibility(View.VISIBLE);
            secondCup.setVisibility(View.GONE);
            drawIcon.setVisibility(View.GONE);
        }
        else
        {
            if (drawGame)
            {
                firstCup.setVisibility(View.GONE);
                secondCup.setVisibility(View.GONE);
                drawIcon.setVisibility(View.VISIBLE);
            }
            else
            {
                firstCup.setVisibility(View.GONE);
                secondCup.setVisibility(View.VISIBLE);
                drawIcon.setVisibility(View.GONE);
            }
        }
    }

    /**
     * Create and return a viable instance of fragment ready to use.
     *
     * @param hasHuman if true, will let user interacts as "Player 1"; otherwise will put an IA instead.
     * @return a fresh instance of {@link BattleFragment} configured.
     */
    public static BattleFragment newInstance(boolean hasHuman)
    {
        // Params to pass to instance
        Bundle args = new Bundle();
        args.putBoolean(HAS_HUMAN_KEY, hasHuman);

        BattleFragment fragment = new BattleFragment();
        fragment.setArguments(args);

        return fragment;
    }

    // endregion
}
