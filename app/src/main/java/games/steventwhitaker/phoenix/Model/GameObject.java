package games.steventwhitaker.phoenix.Model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

import games.steventwhitaker.phoenix.State.ScreenData;
import games.steventwhitaker.phoenix.Utilities.ContentManager;

/**
 * GameObject represents the different objects that will be on the screen. Each GameObject has an
 * associated type, which will be used to know what to draw, how fast the GameObject moves, etc.
 * Each GameObject has its own position to keep track of.
 *
 * @author Steven Whitaker
 * @version 2018-06-09
 */
public abstract class GameObject
{
    /** The type of the GameObject */
    private GameObjectType mType;
    /** The position (top-left corner) of the GameObject */
    private PointF mPos;
    /** The bounding rectangle of the GameObject */
    private RectF mBounds;

    /**
     * Creates a GameObject.
     * @param type Type of GameObject being created
     */
    public GameObject(GameObjectType type)
    {
        mType = type;
        mPos = new PointF();
        mBounds = new RectF();
    }

    public void setPos(PointF pos)
    {
        mPos = pos;
        if(mPos.x < ScreenData.sWidth * ScreenData.X_MIN)
        {
            mPos.x = ScreenData.sWidth * ScreenData.X_MIN;
        }
        else if(mPos.x > ScreenData.sWidth * ScreenData.X_MAX - mType.getWidth())
        {
            mPos.x = ScreenData.sWidth * ScreenData.X_MAX - mType.getWidth();
        }
        if(mPos.y < ScreenData.sHeight * ScreenData.Y_MIN)
        {
            mPos.y = ScreenData.sHeight * ScreenData.Y_MIN;
        }
        else if(mPos.y > ScreenData.sHeight * ScreenData.Y_MAX - mType.getHeight())
        {
            mPos.y = ScreenData.sHeight * ScreenData.Y_MAX - mType.getHeight();
        }

        mBounds.left = mPos.x;
        mBounds.top = mPos.y;
        mBounds.right = mPos.x + mType.getWidth();
        mBounds.bottom = mPos.y + mType.getHeight();
    }

    public PointF getPos()
    {
        return mPos;
    }

    public RectF getBounds()
    {
        return mBounds;
    }

    public GameObjectType getType()
    {
        return mType;
    }

    /**
     * update(...) is called every 1/60 seconds. When called, the state of the GameObject will be
     * updated according to how many time steps have elapsed. Normally, the number of time steps
     * that elapse between each call to update(...) is determined by the game speed option that
     * the user selects.
     * @param timeElapsed Time that has elapsed since the last call to update(...)
     */
    public abstract void update(double timeElapsed);

    /**
     * draw() is called after every call to update(...). It simply redraws the GameObject
     * according to its updated state.
     * @param canvas Canvas on which to draw the GameObject
     */
    public void draw(Canvas canvas)
    {
        Bitmap image = ContentManager.getImage(mType.getImageFilePath());
        canvas.drawBitmap(image, null, mBounds, null);
    }
}
