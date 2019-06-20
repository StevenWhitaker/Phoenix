package games.steventwhitaker.phoenix.Model;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

/**
 * An Enemy is just what it seems to be. It represents the opponents that the player will need to
 * destroy.
 *
 * @author Steven Whitaker
 * @version 2018-06-09
 */
public class Enemy extends Fighter
{
    private static final String TAG = "Enemy";

    public enum Direction
    {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    /** The rectangle path around which the enemy moves */
    private RectF mPath;
    /** Which direction the enemy is currently moving */
    private Direction mDirection;

    /**
     * Creates an Enemy.
     * @param path Path to travel
     * @see Fighter
     */
    public Enemy(GameObjectType type, ProjectileType projectile, RectF path)
    {
        super(type, projectile);
        mPath = path;
        mDirection = Direction.RIGHT;
    }

    @Override
    public void update(double timeElapsed)
    {
        super.update(timeElapsed);
        // TODO: Make Enemy randomly shoot
        PointF newPos = new PointF();
        switch(mDirection)
        {
            case RIGHT:
                newPos.x = (float) (getPos().x + getType().getSpeed() * timeElapsed);
                newPos.y = getPos().y;
                if(newPos.x >= mPath.right)
                {
                    newPos.x = mPath.right;
                    mDirection = Direction.DOWN;
                }
                break;
            case LEFT:
                newPos.x = (float) (getPos().x - getType().getSpeed() * timeElapsed);
                newPos.y = getPos().y;
                if(newPos.x <= mPath.left)
                {
                    newPos.x = mPath.left;
                    mDirection = Direction.UP;
                }
                break;
            case UP:
                newPos.x = getPos().x;
                newPos.y = (float) (getPos().y - getType().getSpeed() * timeElapsed);
                if(newPos.y <= mPath.top)
                {
                    newPos.y = mPath.top;
                    mDirection = Direction.RIGHT;
                }
                break;
            case DOWN:
                newPos.x = getPos().x;
                newPos.y = (float) (getPos().y + getType().getSpeed() * timeElapsed);
                if(newPos.y >= mPath.bottom)
                {
                    newPos.y = mPath.bottom;
                    mDirection = Direction.LEFT;
                }
                break;
            default: // This should never happen
                break;
        }
        setPos(newPos);
    }

    public boolean shoot()
    {
        // TODO: Change 0.99 to be dependent on type of enemy
        if(canShoot() && Math.random() > 0.99)
        {
            resetTimeSinceFiring();
            return true;
        }
        else
        {
            return false;
        }
    }
}
