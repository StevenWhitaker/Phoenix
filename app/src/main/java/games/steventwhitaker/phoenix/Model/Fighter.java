package games.steventwhitaker.phoenix.Model;

/**
 * A Fighter is a GameObject that can fight.
 *
 * @author Steven Whitaker
 * @version 2018-06-09
 */
public abstract class Fighter extends GameObject
{
    /** How much health the Fighter has before dying */
    private int mHealth;
    /** The type of projectile that the Fighter shoots */
    private ProjectileType mProjectile;
    /** How long since the Fighter last fired his/her/its projectile */
    private int mTimeSinceFiring;

    /**
     * Creates a Fighter.
     * @param projectile Type of projectile to fire
     * @see GameObject
     */
    public Fighter(GameObjectType type, ProjectileType projectile)
    {
        super(type);
        mHealth = type.getStartingHealth();
        mProjectile = projectile;
        mTimeSinceFiring = 0;
    }

    public int getHealth()
    {
        return mHealth;
    }

    /**
     * Restores health to full. Used only by Players.
     */
    public void restoreHealth()
    {
        mHealth = getType().getStartingHealth();
    }

    /**
     * Decrements the health of the Fighter.
     * @param damage Damage dealt
     */
    public void loseHealth(int damage)
    {
        mHealth -= damage;
    }

    public void resetTimeSinceFiring()
    {
        mTimeSinceFiring = 0;
    }

    /**
     * Can the Fighter shoot?
     * @return Whether Fighter can shoot
     */
    public boolean canShoot()
    {
        return mTimeSinceFiring >= mProjectile.getRechargeTime();
    }

    @Override
    public void update(double timeElapsed)
    {
        // Avoid overflow by never going greater than the recharge time
        if(++mTimeSinceFiring > mProjectile.getRechargeTime())
        {
            mTimeSinceFiring = mProjectile.getRechargeTime();
        }
    }
}
