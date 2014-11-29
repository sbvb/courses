package com.agilemessage.amrefreshfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by sbvb on 29/10/14.
 */
public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ImageView iv = (ImageView) rootView.findViewById(R.id.imageView1);
        iv.setBackgroundResource(R.drawable.tabs_mockup);
//            rootView.addView(imageView);
        return rootView;
    }
}
