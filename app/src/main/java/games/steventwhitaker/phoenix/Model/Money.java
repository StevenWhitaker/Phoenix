package games.steventwhitaker.phoenix.Model;

import android.graphics.PointF;

/**
 * Money is randomly generated when an enemy is destroyed. When the Player intersects a Money object
 * money is added to the player's total.
 *
 * @author Steven Whitaker
 * @version 2019-06-29
 */
public class Money extends GameObject
{
    /** How much money the player gets */
    private int mValue;

    /**
     * Creates Money.
     * @see GameObject
     */
    public Money(GameObjectType type)
    {
        super(type);
        mValue = 100; // TODO: Change if desired (based on Enemy type?)
    }

    public int getValue()
    {
        return mValue;
    }

    @Override
    public void update(double timeElapsed)
    {
        PointF newPos = new PointF();
        newPos.x = getPos().x;
        newPos.y = (float) (getPos().y + getType().getSpeed() * timeElapsed);
        setPos(newPos);
    }
}
