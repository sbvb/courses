package am.multiactivity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class PickColorActivity extends Activity {

	ArrayAdapter<String> myAdapter;
	ArrayList<String> als;
	Boolean returnByButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		returnByButton = false;
		
		setContentView(R.layout.pick_color_layout);

		Intent data = getIntent();

//		als = data.getExtra().getStringArrayList("myColorList");
		als = data.getBundleExtra("mybundle").getStringArrayList("myColorList");

		// array based on an String array does not accept creation or deletion
		// of items
		myAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, als);

		ListView lv = (ListView) findViewById(R.id.listView1);

		// bind adapter to ListActivity
		lv.setAdapter(myAdapter);

		
		lv.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long arg3) {

				returnByButton = true;
				/*
				 * Toast.makeText(getApplicationContext(), "You clicked on " +
				 * parent.getItemAtPosition(position),
				 * Toast.LENGTH_SHORT).show();
				 */

				Intent answer = new Intent();
//				answer.putExtra("clicked", parent.getItemAtPosition(position)
//						.toString());
				answer.putExtra("clickedInt", position);
				answer.putExtra("returnButton", "1");				
				setResult(Activity.RESULT_OK, answer);
				finish();
			}
		});
		

	}

//	@Override
//    public void onPause() {
//    	super.onPause();
//    	if (!returnByButton) {
//            Toast.makeText(getApplicationContext(), "onPause()", 
//            		Toast.LENGTH_LONG).show();
//    		Intent answer = new Intent();
//    		answer.putExtra("clickedInt", -1);
////    		setResult(Activity.RESULT_OK, answer);
////    		finish();    		
//    	}
//    }


}
