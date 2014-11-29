package com.agilemessage.amrefreshfragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sbvb on 29/10/14.
 */
public class MyListFragment extends Fragment {

    final String LOG_TAG = "=================" + MyListFragment.class.getSimpleName();

    int listSize = 4;

    Context context;
    ProgressDialog progress;

    /**
     * The {@link android.support.v4.widget.SwipeRefreshLayout} that detects swipe gestures and
     * triggers callbacks in the app.
     */
    private SwipeRefreshLayout mSwipeRefreshLayout;

    /**
     * The {@link android.widget.ListView} that displays the content that should be refreshed.
     */
    ListView mListView;

    /**
     * The {@link android.widget.ListAdapter} used to populate the {@link android.widget.ListView}
     * defined in the previous statement.
     */
//    MyArrayAdapter mListAdapter;
    ArrayAdapter<String> mListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Notify the system to allow an options menu for this fragment.
        setHasOptionsMenu(true);
    }

    /**
     * Respond to the user's selection of the Refresh action item. Start the SwipeRefreshLayout
     * progress bar, then initiate the background task that refreshes the content.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sync:
                Log.i(LOG_TAG, "Refresh menu item selected");


                // We make sure that the SwipeRefreshLayout is displaying it's refreshing indicator
                if (!mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
                // Start our refresh background task
                initiateRefresh();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // END_INCLUDE (setup_refresh_menu_listener)


//        void updateNamesRandomly() {
//            Context context = getActivity().getApplicationContext();
//            names = new String[listSize];
//            for (int i = 0; i<listSize; i++) {
//                names[i] = possibleNames[new Random().nextInt(possibleNames.length)];
//            }
//        }

    // BEGIN_INCLUDE (inflate_view)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onCreateView");
        context = getActivity().getApplicationContext();
        View rootView = inflater.inflate
                (R.layout.fragment_list, container, false);

        // Retrieve the SwipeRefreshLayout and ListView instances
        mSwipeRefreshLayout = (SwipeRefreshLayout)
                rootView.findViewById(R.id.swiperefresh);

        mListView = (ListView) rootView.findViewById(R.id.listView1);

        return rootView;
    }
    // END_INCLUDE (inflate_view)

    // BEGIN_INCLUDE (setup_views)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//            Context context = getActivity().getApplicationContext();
//            updateNamesRandomly();

        /**
         * Create an ArrayAdapter to contain the data for the ListView. Each item in the ListView
         * uses the system-defined simple_list_item_1 layout that contains one TextView.
         */
        mListAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                Cheeses.randomList(listSize));

        // Set the adapter between the ListView and its backing data.
        mListView.setAdapter(mListAdapter);

        // BEGIN_INCLUDE (setup_refreshlistener)
        /**
         * Implement {@link SwipeRefreshLayout.OnRefreshListener}. When users do the "swipe to
         * refresh" gesture, SwipeRefreshLayout invokes
         * {@link SwipeRefreshLayout.OnRefreshListener#onRefresh onRefresh()}. In
         * {@link SwipeRefreshLayout.OnRefreshListener#onRefresh onRefresh()}, call a method that
         * refreshes the content. Call the same method in response to the Refresh action from the
         * action bar.
         */
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initiateRefresh();
            }
        });
        // END_INCLUDE (setup_refreshlistener)
    }

    private void initiateRefresh() {
        Log.i(LOG_TAG, "initiateRefresh");
        new MyBackgroundTask().execute();
    }

    /**
     * When the AsyncTask finishes, it calls onRefreshComplete(), which updates the data in the
     * ListAdapter and turns off the progress bar.
     */
    private void onRefreshComplete(List<String> result) {
        Log.i(LOG_TAG, "onRefreshComplete");

        // Remove all items from the ListAdapter, and then replace them with the new items
        mListAdapter.clear();
        for (String str : result) {
            mListAdapter.add(str);
        }

        // Stop the refreshing indicator
        mSwipeRefreshLayout.setRefreshing(false);
        progress.dismiss();
    }


    private class MyBackgroundTask extends AsyncTask<Void, Void, List<String>> {

        static final int TASK_DURATION = 3 * 1000; // 3 seconds

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(getActivity());
            progress.setMessage("accessing the cloud");
            progress.setTitle("wait");
            // progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setCancelable(true);
            progress.setProgress(0);
            progress.show();

        }

        @Override
        protected List<String> doInBackground(Void... params) {
            Log.i(LOG_TAG, "doInBackground");

            // Sleep for a small amount of time to simulate a background-task
            try {
                Thread.sleep(TASK_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Return a new random list of cheeses
            return Cheeses.randomList(listSize);
        }

        @Override
        protected void onPostExecute(List<String> result) {
            Log.i(LOG_TAG, "onPostExecute");
            super.onPostExecute(result);
            // Tell the Fragment that the refresh has completed
            onRefreshComplete(result);
        }

    }

}


class MyArrayAdapter extends ArrayAdapter<String> {

    public MyArrayAdapter(Context context, int textViewResourceId, String[] objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
//            if (position == 1) {
        ((TextView) v).setTextColor(Color.BLACK);
//            }
        return v;

//            View view = super.getView(position, convertView, parent);
//            TextView tv = (TextView) view.findViewById(android.R.id.text1);
//            // tv.setTextColor(0);
//            return view;
    }
}



