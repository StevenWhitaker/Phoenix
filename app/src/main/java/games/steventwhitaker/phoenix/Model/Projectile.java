package games.steventwhitaker.phoenix.Model;

import android.graphics.PointF;
import android.util.Log;

/**
 * A Projectile is what Fighters fire to attack their foes.
 *
 * @author Steven Whitaker
 * @version 2018-06-09
 */
public class Projectile extends GameObject
{
    private static final String TAG = "Projectile";

    /** The direction the Projectile should travel; 0 is up, 90 is right, 180 is down, 270 is left*/
    private double mDirection;
    /** Whether the player fired the Projectile or not; used to make sure enemies don't hurt
     * fellow enemies */
    private boolean mFiredByPlayer;

    /**
     * Creates a Projectile.
     * @param direction Direction to travel (angle in degrees)
     * @param firedByPlayer Whether the player fired the Projectile
     * @see GameObject
     */
    public Projectile(ProjectileType type, double direction, boolean firedByPlayer)
    {
        super(type);
        mDirection = Math.toRadians(direction);
        mFiredByPlayer = firedByPlayer;
    }

    public boolean firedByPlayer()
    {
        return mFiredByPlayer;
    }

    @Override
    public ProjectileType getType()
    {
        return (ProjectileType) super.getType();
    }

    @Override
    public void setPos(PointF pos)
    {
        getPos().x = pos.x;
        getPos().y = pos.y;

        getBounds().left = getPos().x;
        getBounds().top = getPos().y;
        getBounds().right = getPos().x + getType().getWidth();
        getBounds().bottom = getPos().y + getType().getHeight();
    }

    @Override
    public void update(double timeElapsed)
    {
        // TODO: Make Projectile move in mDirection
        PointF newPos = new PointF();
        newPos.x = (float) (getPos().x + getType().getSpeed() * timeElapsed * Math.sin(mDirection));
        newPos.y = (float) (getPos().y - getType().getSpeed() * timeElapsed * Math.cos(mDirection));
        setPos(newPos);
    }
}
