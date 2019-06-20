package games.steventwhitaker.phoenix.Engine;

import games.steventwhitaker.phoenix.State.GameData;
import games.steventwhitaker.phoenix.UI.GameActivity;
import games.steventwhitaker.phoenix.UI.GameView;

/**
 * GameLoopThread will simply cause the screen to update every 1/60 seconds.
 *
 * @author Steven Whitaker
 * @version 2018-06-14
 */
public class GameLoopThread extends Thread
{
    /** Number of frames per second */
    private static final long FPS = 60;

    /** A pointer to the Activity hosting the GameView */
    private GameActivity mGameActivity;
    /** A pointer to the GameView so updates can occur */
    private GameView mGameView;
    /** Whether the game is running */
    private boolean mRunning;

    /**
     * Creates a GameLoopThread.
     * @param gameView GameView to update every 1/FPS seconds
     */
    public GameLoopThread(GameActivity gameActivity, GameView gameView)
    {
        mGameActivity = gameActivity;
        mGameView = gameView;
        mRunning = false;
    }

    public void setRunning(boolean running)
    {
        mRunning = running;
    }

    @Override
    public void run()
    {
        long TPS = 1000 / FPS; // ticks per second
        long startTime;
        long sleepTime;
        while(mRunning)
        {
            startTime = System.currentTimeMillis();
            GameData.update(TPS / 1000.0); // TODO: Replace with speed from options
            mGameActivity.runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    mGameView.invalidate();
                }
            });
            sleepTime = TPS - (System.currentTimeMillis() - startTime);
            try
            {
                if(sleepTime > 0)
                {
                    sleep(sleepTime);
                }
            }
            catch(InterruptedException ex)
            {

            }
        }
    }
}
