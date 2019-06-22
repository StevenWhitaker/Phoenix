package games.steventwhitaker.phoenix.UI;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;

public class GameActivity extends SingleFragmentActivity
{
    private static final String EXTRA_IS_RESUME = "games.steventwhitaker.phoenix.UI.is_resume";

    public static Intent newIntent(Context packageContext, boolean isResume)
    {
        Intent intent = new Intent(packageContext, GameActivity.class);
        intent.putExtra(EXTRA_IS_RESUME, isResume);
        return intent;
    }

    @Override
    protected Fragment createFragment()
    {
        boolean isResume = getIntent().getBooleanExtra(EXTRA_IS_RESUME, false);
        return GameFragment.newInstance(isResume);
    }
}
