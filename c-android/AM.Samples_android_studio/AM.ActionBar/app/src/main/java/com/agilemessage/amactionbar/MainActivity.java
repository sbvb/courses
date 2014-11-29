package com.agilemessage.amactionbar;

/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;

/**
 * This sample shows you how to use ActionBarCompat with a customized theme. It utilizes a split
 * action bar when running on a device with a narrow display, and show three tabs.
 *
 * This Activity extends from {@link ActionBarActivity}, which provides all of the function
 * necessary to display a compatible Action Bar on devices running Android v2.1+.
 *
 * The interesting bits of this sample start in the theme files
 * ('res/values/styles.xml' and 'res/values-v14</styles.xml').
 *
 * Many of the drawables used in this sample were generated with the
 * 'Android Action Bar Style Generator': http://jgilfelt.github.io/android-actionbarstylegenerator
 */
public class MainActivity extends ActionBarActivity
//        implements ActionBar.TabListener
{
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the Action Bar to use tabs for navigation
        ActionBar ab = getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab = ab.newTab()
                .setText("hello tab")
                .setTabListener(new TabListener<HelloFragment>(this, "hello", HelloFragment.class));
        actionBar.addTab(tab);


        // Add three tabs to the Action Bar for display
        ab.addTab(ab.newTab().setText("Tab 1").setTabListener(this));
        ab.addTab(ab.newTab().setText("Tab 2").setTabListener(this));
        ab.addTab(ab.newTab().setText("Tab 3").setTabListener(this));
        ab.addTab(ab.newTab().setText("Tab 4").setTabListener(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu from menu resource (res/menu/main)
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
    * Returns the correct id of the action bar
    * @return
            */
    public static int getCorrectActionBarId () {
        int androidVersion = Build.VERSION.SDK_INT;
        if (androidVersion >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return android.R.id.content;
        } else {
            return R.id.action_bar_activity_content;
        }
    }



    public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
        private Fragment mFragment;
        private final ActionBarActivity mActivity;
        private final String mTag;
        private final Class<T> mClass;

        /**
         * Constructor used each time a new tab is created.
         * @param activity The host Activity, used to instantiate the fragment
         * @param tag The identifier tag for the fragment
         * @param clz The fragment's Class, used to instantiate the fragment
         */
        public TabListener(ActionBarActivity activity, String tag, Class<T> clz) {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            mFragment = mActivity.getSupportFragmentManager().findFragmentByTag(mTag);

            // Check if the fragment is already initialized
            if (mFragment == null) {
                // If not, instantiate and add it to the activity
                mFragment = Fragment.instantiate(mActivity, mClass.getName());
                ft.add(getCorrectActionBarId(), mFragment, mTag);
                Log.i(TAG, "FragID " + mFragment.getId() + ", FragTAG=" + mFragment.getTag() + " ADDED!!!");
            } else {
                // If it exists, simply attach it in order to show it
                ft.attach(mFragment);
                Log.i(TAG, "FragID " + mFragment.getId() + ", FragTAG=" + mFragment.getTag() + " attached.");
            }
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            if (mFragment != null) {
                // Detach the fragment, because another one is being attached
                ft.detach(mFragment);
                Log.i(TAG, "FragID " + mFragment.getId() + ", FragTAG=" + mFragment.getTag() + " detached.");
            }
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            // User selected the already selected tab. Usually do nothing.
        }
    }

//    // Implemented from ActionBar.TabListener
//    @Override
//    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
//
//        int tab_val=-1;
//        if (tab != null) {
//            tab_val = ((Integer) tab.getTag()).intValue();
//        }
//        Toast.makeText(getApplicationContext(),
//                "onTabSelected, tab=" + tab_val,
//                Toast.LENGTH_LONG).show();
//    }
//
//    // Implemented from ActionBar.TabListener
//    @Override
//    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
//        // This is called when a previously selected tab is unselected.
//    }
//
//    // Implemented from ActionBar.TabListener
//    @Override
//    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
//        // This is called when a previously selected tab is selected again.
//    }
}
