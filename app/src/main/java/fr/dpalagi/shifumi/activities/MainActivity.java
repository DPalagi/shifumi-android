package fr.dpalagi.shifumi.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import fr.dpalagi.shifumi.R;

/**
 * MainActivity is the launcher activity handling the game. Manipulates the fragments
 * that let user play within the app. Let user select the game mode he wants to play.
 * Created : 25/11/2017.
 *
 * @author DPalagi
 */

public class MainActivity extends AppCompatActivity
{
    // region PROPERTIES

    /**
     * Buttons to select the game mode user wants to play
     */
    private Button firstModeButton, secondModeButton;

    // endregion

    // region EVENTS

    @Override
    public void onCreate(
            @Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get UI elements
        firstModeButton = findViewById(R.id.select_first_mode);
        secondModeButton = findViewById(R.id.select_second_mode);

        // TODO click listeners
    }


    // endregion

    // region METHODS

    /**
     * Create and setup a {@link fr.dpalagi.shifumi.fragments.BattleFragment} instance
     * to let user start playing in desired mode
     *
     * @param withHuman if true let user plays; otherwise let IA plays alone
     */
    private void startGame(boolean withHuman)
    {
        // TODO implements
    }
    // endregion
}
