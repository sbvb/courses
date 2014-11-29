package com.agilemessage.amtemplateactionbar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set the Action Bar to use tabs for navigation

        // remove message bar
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        ActionBar ab = getSupportActionBar(); // requires ActionBarActivity

        // Add three tabs to the Action Bar for display
        ab.addTab(ab.newTab().setText("About").setTabListener(this));
        ab.addTab(ab.newTab().setText("Share").setTabListener(this));
        ab.addTab(ab.newTab().setText("Execute").setTabListener(this));



        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle("Application Title");
//        ab.setIcon(R.drawable.ic_action_settings);
        ab.setIcon(0); // to hide the icon
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ab.setHomeButtonEnabled(false);
        ab.setDisplayUseLogoEnabled(false);
        ab.setDisplayHomeAsUpEnabled(false);

        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.background_portrait);
        iv.setImageBitmap(bm);


//        ab.setLogo(R.drawable.ic_action_settings);
        //ab.hide();

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        Toast.makeText(getApplicationContext(), "id="+id,
//                Toast.LENGTH_LONG)
//                .show();
//
////        if (id == R.id.action_settings) {
////            return true;
////        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }
}
