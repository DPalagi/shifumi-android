package fr.dpalagi.shifumi.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fr.dpalagi.shifumi.R;
import fr.dpalagi.shifumi.fragments.BattleFragment;

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

    /**
     * Help to handle the user that want to quit app
     */
    private boolean userWantToQuit = false;

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

        // Display battle frag based on user selection
        final FragmentManager fragmentManager = getSupportFragmentManager();

        firstModeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_up, android.R.anim.fade_out,
                                             android.R.anim.fade_in, R.anim.slide_out_down)
                        .add(R.id.select_root,
                             BattleFragment.newInstance(true))
                        .addToBackStack(null)
                        .commit();
            }
        });
        secondModeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_up, android.R.anim.fade_out,
                                             android.R.anim.fade_in, R.anim.slide_out_down)
                        .add(R.id.select_root,
                             BattleFragment.newInstance(false))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }


    // endregion

    // region EVENTS

    @Override
    public void onBackPressed()
    {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
        {
            getSupportFragmentManager().popBackStack();
        }
        else if (!userWantToQuit)
        {
            Toast.makeText(this, R.string.press_to_quit, Toast.LENGTH_SHORT).show();
            userWantToQuit = true;

            // After some delay, if user doesn't click a second time, reset message mechanism
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    userWantToQuit = false;
                }
            }, 2000);
        }
        else
        {
            super.onBackPressed();
        }
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
