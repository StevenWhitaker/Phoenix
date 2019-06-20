package games.steventwhitaker.phoenix.Model;

import android.graphics.PointF;

import games.steventwhitaker.phoenix.State.ScreenData;

/**
 * Player represents the user ship.
 *
 * @author Steven Whitaker
 * @version 2018-06-09
 */
public class Player extends Fighter
{
    /** The maximum health the player can have */
    public static final int MAX_HEALTH = 10;

    /** How far away from the ship the player has to press the screen to move */
    private static final float BUFFER = 10f;

    /**
     * Creates the Player ship.
     * @see Fighter
     */
    public Player(GameObjectType type, ProjectileType projectile)
    {
        super(type, projectile);
    }

    @Override
    public void update(double timeElapsed)
    {
        super.update(timeElapsed);
        float centerX = getPos().x + getType().getWidth() / 2f;
        if(ScreenData.sTouchXPos >= 0f && Math.abs(ScreenData.sTouchXPos - centerX) >= BUFFER)
        {
            double newX = ScreenData.sTouchXPos - centerX > 0 ? getPos().x + getType().getSpeed() *
                    timeElapsed : getPos().x - getType().getSpeed() * timeElapsed;
            setPos(new PointF((float) newX, getPos().y));
        }
    }
}
