package fr.dpalagi.shifumi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import fr.dpalagi.shifumi.R;
import fr.dpalagi.shifumi.model.Player;

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
     * Players of the game; can be human or IA.
     */
    private Player firstPlayer, secondPlayer;

    /**
     * Tell at config time if this fragment will handle a human player or not
     */
    private boolean hasHuman;

    /* Keys to pass data into fragment instance */
    private static final String HAS_HUMAN_KEY = "has_human_in_game";

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

        // Extract config params ==> consider that user can play by default
        Bundle args = getArguments();
        hasHuman = args.getBoolean(HAS_HUMAN_KEY, true);

        // Setup UI and event listeners
        setupUI(hasHuman);

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
            // TODO listeners
        }
        else
        {
            firstPlayerIcon.setImageResource(R.drawable.ic_robot);
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
