package games.steventwhitaker.phoenix.Model;

/**
 * ProjectileType represents the types of Projectiles in the game.
 *
 * @author Steven Whitaker
 * @version 2018-06-11
 */
public class ProjectileType extends GameObjectType
{
    /** The strength of the ProjectileType */
    private int mPower;
    /** How long until the ProjectileType can be fired again */
    private int mRechargeTime;

    /**
     * Makes a ProjectileType.
     * @see GameObjectType
     */
    public ProjectileType(Types type)
    {
        super(type);
        // TODO: Fill out private fields according to type
        switch(type)
        {
            case PROJECTILE_VOMIT:
                mPower = 1;
                mRechargeTime = 60;
                break;
            default: // This should never happen
                break;
        }
    }

    public int getPower()
    {
        return mPower;
    }

    public int getRechargeTime()
    {
        return mRechargeTime;
    }
}
