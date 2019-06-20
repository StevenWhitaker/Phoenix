package games.steventwhitaker.phoenix.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;

import games.steventwhitaker.phoenix.R;
import games.steventwhitaker.phoenix.State.GameData;
import games.steventwhitaker.phoenix.State.ScreenData;
import games.steventwhitaker.phoenix.Utilities.ContentManager;

public class StartFragment extends Fragment
{
    private Button mNewGameButton;
    private Button mResumeButton;
    private Button mOptionsButton;

    public static StartFragment newInstance()
    {
        return new StartFragment();
    }

    // TODO: In database, have field to indicate whether the save was in the shop menu, at the
    // beginning of a level, or in the middle of a level
    // TODO: Have database save automatically when GameFragment ends
    // TODO: Have database save automatically when ShopFragment ends (pressing save and quit will
    // just end the ShopFragment)
    // TODO: Have database clear itself when HighScoresFragment ends
    // TODO: Go to HighScoresFragment after death or beating the final level (this way the
    // database will be cleared to prevent continuing when the player died)
    // TODO: Clearing database will just set GameData.sSaveState to "none"
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View v = inflater.inflate(R.layout.fragment_start, container, false);
        ViewTreeObserver observer = v.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                ScreenData.sWidth = v.getWidth();
                ScreenData.sHeight = v.getHeight();
            }
        });

        GameData.initialize();

        mNewGameButton = v.findViewById(R.id.button_new_game);
        mNewGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                GameData.sLevel = 1;
                startActivity(GameActivity.newIntent(getActivity(), false));
            }
        });

        mResumeButton = v.findViewById(R.id.button_resume);
        mResumeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(GameActivity.newIntent(getActivity(), true));
            }
        });
        mResumeButton.setEnabled(!GameData.sSaveState.equalsIgnoreCase("none"));

        mOptionsButton = v.findViewById(R.id.button_options);

        ContentManager.setAssets(getActivity().getAssets());
        ContentManager.load();

        return v;
    }
}
