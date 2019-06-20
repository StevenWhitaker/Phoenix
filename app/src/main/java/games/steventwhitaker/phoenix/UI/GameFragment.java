package games.steventwhitaker.phoenix.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import games.steventwhitaker.phoenix.Engine.GameLoopThread;
import games.steventwhitaker.phoenix.R;
import games.steventwhitaker.phoenix.State.GameData;

public class GameFragment extends Fragment
{
    private static final String ARG_IS_RESUME = "is_resume";

    private GameView mGameView;
    private GameLoopThread mGameLoopThread;

    public static GameFragment newInstance(boolean isResume)
    {
        Bundle args = new Bundle();
        args.putBoolean(ARG_IS_RESUME, isResume);

        GameFragment fragment = new GameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        GameData.loadGame(getArguments().getBoolean(ARG_IS_RESUME));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        mGameView = (GameView) inflater.inflate(R.layout.fragment_game, container, false);

        final GameView gameView = mGameView;
        ViewTreeObserver observer = gameView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                mGameLoopThread = new GameLoopThread((GameActivity) getActivity(), gameView);
                mGameLoopThread.setRunning(true);
                mGameLoopThread.start(); // TODO: mGameLoopThread.join() when GameFragment ends?
            }
        });

        return mGameView;
    }
}
