package games.steventwhitaker.phoenix.UI;

import androidx.fragment.app.Fragment;

public class StartActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        return StartFragment.newInstance();
    }
}
