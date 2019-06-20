package games.steventwhitaker.phoenix.Model;

/**
 * GameObjectType represents the data associated with each type of GameObject that is not unique
 * to a particular instance of a GameObject (e.g., the image to display).
 *
 * @author Steven Whitaker
 * @version 2018-06-11
 */
public class GameObjectType
{
    /** All possible types of GameObjects */
    // TODO: Fill out Types; examples given already
    public enum Types
    {
        PLAYER_BLUE,
        PLAYER_RED,
        ENEMY_GRUNT,
        ENEMY_BOSS1,
        PROJECTILE_VOMIT
    }

    /** The file path for the image */
    private String mImageFilePath;
    /** The width of the image */
    private int mWidth;
    /** The height of the image */
    private int mHeight;
    /** The speed of the GameObjectType */
    private int mSpeed;
    /** The starting health of the GameObjectType; unfortunately, this is not used for
     * ProjectileTypes */
    private int mStartingHealth;

    /**
     * Makes a GameObjectType. The private fields are filled out according to the type of the
     * GameObject.
     * @param type Type of GameObject
     */
    public GameObjectType(Types type)
    {
        // TODO: Fill out private fields according to type
        switch(type)
        {
            case PLAYER_BLUE:
                mImageFilePath = "images/player/player_blue.png";
                mWidth = 50;
                mHeight = 40;
                mSpeed = 100;
                mStartingHealth = Player.MAX_HEALTH;
                break;
            case PROJECTILE_VOMIT:
                mImageFilePath = "images/projectile/projectile_vomit.png";
                mWidth = 5;
                mHeight = 15;
                mSpeed = 300;
                mStartingHealth = -1;
                break;
            default: // This should never happen
                break;
        }
    }

    public static boolean isProjectile(Types type)
    {
        return type == Types.PROJECTILE_VOMIT;
    }

    public String getImageFilePath()
    {
        return mImageFilePath;
    }

    public int getWidth()
    {
        return mWidth;
    }

    public int getHeight()
    {
        return mHeight;
    }

    public int getSpeed()
    {
        return mSpeed;
    }

    public int getStartingHealth()
    {
        return mStartingHealth;
    }
}
