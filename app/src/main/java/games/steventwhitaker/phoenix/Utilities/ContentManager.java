package games.steventwhitaker.phoenix.Utilities;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ContentManager, as the name implies, manages the content of Phoenix, specifically the files in
 * the assets folder. It will hold the images to be rendered and the sounds to be played.
 *
 * @author Steven Whitaker
 * @version 2018-06-09
 */
public class ContentManager
{
    private static final String TAG = "ContentManager";
    private static final String IMAGES_FOLDER = "images";
    private static final String ENEMY_FOLDER = "enemy";
    private static final String PLAYER_FOLDER = "player";
    private static final String PROJECTILE_FOLDER = "projectile";

    private static AssetManager sAssets;
    private static Map<String, Bitmap> sImages;
    // TODO: Load sounds

    /**
     * Set the AssetManager for the app.
     * @param assets The AssetManager
     */
    public static void setAssets(AssetManager assets)
    {
        sAssets = assets;
    }

    /**
     * Load all the image and sound files needed for the app.
     */
    public static void load()
    {
        String[] enemyImages;
        String[] playerImages;
        String[] projectileImages;
        Map<String, Bitmap> images = new HashMap<>();
        try
        {
            enemyImages = sAssets.list(IMAGES_FOLDER + "/" + ENEMY_FOLDER);
            playerImages = sAssets.list(IMAGES_FOLDER + "/" + PLAYER_FOLDER);
            projectileImages = sAssets.list(IMAGES_FOLDER + "/" + PROJECTILE_FOLDER);
            for(String s : enemyImages)
            {
                String filepath = IMAGES_FOLDER + "/" + ENEMY_FOLDER + "/" + s;
                Bitmap bitmap = BitmapFactory.decodeStream(sAssets.open(filepath));
                images.put(filepath, bitmap);
                Log.i(TAG, "Loaded " + filepath);
            }
            for(String s : playerImages)
            {
                String filepath = IMAGES_FOLDER + "/" + PLAYER_FOLDER + "/" + s;
                Bitmap bitmap = BitmapFactory.decodeStream(sAssets.open(filepath));
                images.put(filepath, bitmap);
                Log.i(TAG, "Loaded " + filepath);
            }
            for(String s : projectileImages)
            {
                String filepath = IMAGES_FOLDER + "/" + PROJECTILE_FOLDER + "/" + s;
                Bitmap bitmap = BitmapFactory.decodeStream(sAssets.open(filepath));
                images.put(filepath, bitmap);
                Log.i(TAG, "Loaded " + filepath);
            }
            sImages = images;
        }
        catch(IOException ioe)
        {
            Log.e(TAG, "Could not get assets/images/", ioe);
        }
    }

    public static Bitmap getImage(String filepath)
    {
        return sImages.get(filepath);
    }
}
