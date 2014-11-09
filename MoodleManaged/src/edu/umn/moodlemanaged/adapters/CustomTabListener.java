package edu.umn.moodlemanaged.adapters;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * @author Alex Schillinger
 * 
 *         Main application frame. Overlays action bar for tabbed navigation
 *         between grades, courses, and assignments
 * 
 */
public class CustomTabListener<T extends Fragment> implements TabListener {
	private Fragment mFragment;
    private final Activity mActivity;
    private final String mTag;
    private final Class<T> mClass;
 
    public CustomTabListener(Activity activity, String tag, Class<T> clz){
        mActivity = activity;
        mTag = tag;
        mClass = clz;
    }
 
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {

    }


    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        if(mFragment == null)
        {
            mFragment = Fragment.instantiate(mActivity, mClass.getName());
            ft.add(android.R.id.content, mFragment, mTag);
        }
        else
        {
            ft.show(mFragment);
        }
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        if(mFragment!=null)
            ft.hide(mFragment);
    }
}
