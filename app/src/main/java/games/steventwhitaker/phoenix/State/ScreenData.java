package games.steventwhitaker.phoenix.State;

/**
 * ScreenData will contain all information related to the screen.
 *
 * @author Steven Whitaker
 * @version 2018-06-13
 */
public class ScreenData
{
    // Screen width and height
    public static int sWidth = -1;
    public static int sHeight = -1;

    // User input
    public static boolean sScreenTouched = false;
    public static float sTouchXPos = -1f;

    // Screen drawing constants
    public static final float MARGIN = 0.05f; // fraction of screen width/height
    public static final float X_MIN = MARGIN;
    public static final float X_MAX = 1f - MARGIN;
    public static final float Y_MIN = MARGIN;
    public static final float Y_MAX = 1f - MARGIN;

    // Player drawing constants
    public static final float PLAYER_Y = 0.8f; // fraction of screen height
}
