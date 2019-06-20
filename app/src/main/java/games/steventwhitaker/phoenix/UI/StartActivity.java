package games.steventwhitaker.phoenix.UI;

import android.support.v4.app.Fragment;

public class StartActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        return StartFragment.newInstance();
    }
}
