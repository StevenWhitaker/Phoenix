package games.steventwhitaker.phoenix.State;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import games.steventwhitaker.phoenix.Model.Enemy;
import games.steventwhitaker.phoenix.Model.GameObjectType;
import games.steventwhitaker.phoenix.Model.Player;
import games.steventwhitaker.phoenix.Model.Projectile;
import games.steventwhitaker.phoenix.Model.ProjectileType;

/**
 * GameData will contain all of the data associated with the current game state.
 *
 * @author Steven Whitaker
 * @version 2018-06-11
 */
public class GameData
{
    private static final String TAG = "GameData";

    public static int sLevel;
    public static String sSaveState; // TODO: none, shop, game; save and quit will have shop state
    public static Map<GameObjectType.Types, GameObjectType> sGameObjectTypes;
    public static Player sPlayer;
    public static int sPlayerHealth;
    public static boolean sRapidFire;
    public static List<Enemy> sEnemies;
    public static List<Projectile> sProjectiles;

    public static void initialize()
    {
        // TODO: Replace with something fetched from database
        sLevel = 0;
        sSaveState = "none";
        sPlayerHealth = Player.MAX_HEALTH;
        sRapidFire = false;
        sEnemies = new ArrayList<>();
        sProjectiles = new ArrayList<>();

        // Create all GameObjectTypes
        sGameObjectTypes = new HashMap<>();
        for(GameObjectType.Types type : GameObjectType.Types.values())
        {
            if(GameObjectType.isProjectile(type))
            {
                sGameObjectTypes.put(type, new ProjectileType(type));
            }
            else
            {
                sGameObjectTypes.put(type, new GameObjectType(type));
            }
        }
    }

    public static void loadGame(boolean isResume)
    {
        if(isResume)
        {
            // TODO: Load game objects from database
        }
        else
        {
            // TODO: Load game objects based on sLevel
            switch(sLevel)
            {
                case 1: loadLevel1(); break;
                default: // This should never happen
                    break;
            }
        }
    }

    private static void loadLevel1()
    {
        sPlayer = new Player(sGameObjectTypes.get(GameObjectType.Types.PLAYER_BLUE),
                (ProjectileType) sGameObjectTypes.get(GameObjectType.Types.PROJECTILE_VOMIT));
        sPlayer.setPos(new PointF((ScreenData.sWidth - sPlayer.getType().getWidth()) / 2,
                ScreenData.sHeight * ScreenData.PLAYER_Y));

        Enemy enemy = new Enemy(sGameObjectTypes.get(GameObjectType.Types.PLAYER_BLUE),
                (ProjectileType) sGameObjectTypes.get(GameObjectType.Types.PROJECTILE_VOMIT),
                new RectF(ScreenData.sWidth * ScreenData.X_MIN,
                        ScreenData.sHeight * ScreenData.Y_MIN,
                        ScreenData.sWidth * ScreenData.X_MAX - sGameObjectTypes.get
                                (GameObjectType.Types.PLAYER_BLUE).getWidth(),
                        ScreenData.sHeight * ScreenData.Y_MAX - sGameObjectTypes.get
                                (GameObjectType.Types.PLAYER_BLUE).getHeight()));
        enemy.setPos(new PointF(ScreenData.sWidth * ScreenData.X_MIN,
                ScreenData.sHeight * ScreenData.Y_MIN));
        sEnemies.add(enemy);
    }

    public static void update(double timeElapsed)
    {
        sPlayer.update(timeElapsed);
        if(ScreenData.sScreenTouched)
        {
            ScreenData.sScreenTouched = sRapidFire;
            if(sPlayer.canShoot())
            {
                sPlayer.resetTimeSinceFiring();
                Projectile p = new Projectile((ProjectileType) sGameObjectTypes.get(
                        GameObjectType.Types.PROJECTILE_VOMIT), 0.0, true);
                p.setPos(new PointF(sPlayer.getPos().x + (sPlayer.getType().getWidth() - p.getType()
                        .getWidth()) / 2f, sPlayer.getPos().y - p.getType().getHeight()));
                sProjectiles.add(p);
            }
        }
        for(Enemy e : sEnemies)
        {
            e.update(timeElapsed);
            if(e.shoot())
            {
                Projectile p = new Projectile((ProjectileType) sGameObjectTypes.get(
                        GameObjectType.Types.PROJECTILE_VOMIT), 180.0, false);
                p.setPos(new PointF(e.getPos().x + (e.getType().getWidth() - p.getType()
                        .getWidth()) / 2f, e.getPos().y + e.getType().getHeight()));
                sProjectiles.add(p);
            }
        }
        for(int i = 0; i < sProjectiles.size(); i++)
        {
            Projectile p = sProjectiles.get(i);
            p.update(timeElapsed);
            if(p.getPos().x + p.getType().getWidth() < 0f || p.getPos().x > ScreenData.sWidth ||
                    p.getPos().y + p.getType().getHeight() < 0f || p.getPos().y > ScreenData.sHeight)
            {
                sProjectiles.remove(i);
                i--;
            }
        }
        checkCollisions();
    }

    private static void checkCollisions()
    {
        for(int i = 0; i < sProjectiles.size(); i++)
        {
            Projectile p = sProjectiles.get(i);
            if(p.firedByPlayer())
            {
                for(int j = 0; j < sEnemies.size(); j++)
                {
                    Enemy e = sEnemies.get(j);
                    if(RectF.intersects(e.getBounds(), p.getBounds()))
                    {
                        e.loseHealth(p.getType().getPower());
                        if(e.getHealth() <= 0)
                        {
                            sEnemies.remove(j);
                        }
                        sProjectiles.remove(i);
                        i--;
                        break;
                    }
                }
            }
            else
            {
                if(RectF.intersects(sPlayer.getBounds(), p.getBounds()))
                {
                    sPlayer.loseHealth(p.getType().getPower());
                    sPlayerHealth = sPlayer.getHealth();
                    if(sPlayerHealth <= 0)
                    {
                        // TODO: Game Over
                    }
                    sProjectiles.remove(i);
                    i--;
                }
            }
        }
    }

    public static void draw(Canvas canvas)
    {
        sPlayer.draw(canvas);
        for(Enemy e : sEnemies)
        {
            e.draw(canvas);
        }
        for(Projectile p : sProjectiles)
        {
            p.draw(canvas);
        }
    }
}
