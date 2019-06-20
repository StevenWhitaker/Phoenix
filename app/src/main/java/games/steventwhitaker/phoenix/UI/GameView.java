package games.steventwhitaker.phoenix.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import games.steventwhitaker.phoenix.State.GameData;
import games.steventwhitaker.phoenix.State.ScreenData;

/**
 * GameView will display all game objects and detect touch input.
 *
 * @author Steven Whitaker
 * @version 2018-06-09
 */
public class GameView extends View
{
    private static final String TAG = "GameView";

    /**
     * Creates a GameView from code.
     */
    public GameView(Context context)
    {
        this(context, null);
    }

    /**
     * Creates a GameView from a layout.
     */
    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // TODO: Store event location and action; see p.533
        String action = "";
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                ScreenData.sScreenTouched = true;
                ScreenData.sTouchXPos = event.getX();
                action = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                ScreenData.sTouchXPos = event.getX();
                action = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                ScreenData.sScreenTouched = false;
                ScreenData.sTouchXPos = -1f;
                action = "ACTION_UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                ScreenData.sScreenTouched = false;
                ScreenData.sTouchXPos = -1f;
                action = "ACTION_CANCEL";
                break;
        }
        Log.i(TAG, action + " at x = " + event.getX() + ", y = " + event.getY());
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        GameData.draw(canvas);
        // TODO: Also update status on bottom of screen (e.g., health, money, etc.)
    }
}
