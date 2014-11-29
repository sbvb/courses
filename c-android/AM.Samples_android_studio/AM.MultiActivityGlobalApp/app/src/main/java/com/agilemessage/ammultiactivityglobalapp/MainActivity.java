package com.agilemessage.ammultiactivityglobalapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends Activity {

	enum RequestCode {PICK_COLOR, ADD_COLOR };
	
	final int PICK_COLOR_REQUEST_CODE = 10; // any non repetitive number here
	final int ADD_COLOR_REQUEST_CODE = 11; // any non repetitive number here

	int currColor = 0;
	Button b, bp;
	TextView tv;
	EditText et;

    // the application instance
    MyApplication app;


    void updateColor(int newColor) {
		currColor = newColor;
		tv.setBackgroundColor(app.colorList.get(currColor).getColor());
		tv.setText(app.colorList.get(currColor).getColorName());
		et.setBackgroundColor(app.colorList.get(currColor).getColor());
		tv.setTextColor(app.colorList.get(currColor).getColor() ^ 0xFFFFFF);
	}

	void deleteColorTest(String colorName) {
		Iterator<MyColor> it = app.colorList.iterator();
		while (it.hasNext()) {
			MyColor myColor = it.next();
			if (myColor.getColorName().equals(colorName)) {
				it.remove();
			}
		}
	}

	void deleteColorTest(int item) {
        app.colorList.remove(item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        // Get the application instance
        app = (MyApplication)getApplication();

		// initialize colorList with some values
		app.colorList = new ArrayList<MyColor>();
		// colorList.add(new MyColor("#ffcc09" ,"first"));
        app.colorList.add(new MyColor(Color.MAGENTA, "magenta"));
        app.colorList.add(new MyColor(Color.GREEN, "green"));
        app.colorList.add(new MyColor(Color.RED, "red"));
        app.colorList.add(new MyColor(Color.BLUE, "blue"));
        app.colorList.add(new MyColor(Color.YELLOW, "yellow 黄色"));
        app.colorList.add(new MyColor(Color.rgb(22, 33, 44), "azul bombom"));
//		colorList.add(new MyColor(Color.LTGRAY, "hello"));
//		colorList.add(new MyColor(Color.MAGENTA, "violete"));

		deleteColorTest("green");

		// updateColor(1); // initial color

		et = (EditText) findViewById(R.id.color_name);
		tv = (TextView) findViewById(R.id.textView1);
		tv.setBackgroundColor(app.colorList.get(currColor).getColor());
		tv.setTextColor(app.colorList.get(currColor).getColor() ^ 0xFFFFFF);
		tv.setText("the color");
		tv.setGravity(Gravity.CENTER_HORIZONTAL);

		b = (Button) findViewById(R.id.ok_button);
		b.setText("change color");
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				currColor++;
				if (currColor >= app.colorList.size())
					currColor = 0;
				updateColor(currColor);
			}
		});

		b.performClick();

		bp = (Button) findViewById(R.id.Button2);
		bp.setText("pick color\nusing other Activity");
		bp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						PickColorActivity.class);

//				// copy strings of colorList to als
//				final ArrayList<String> als = new ArrayList<String>();
//				for (int i = 0; i < colorList.size(); i++)
//					als.add(colorList.get(i).getColorName());
//
//				Bundle bundle = new Bundle();
//				bundle.putStringArrayList("myColorList", als);
////				intent.putExtras(bundle);
//				intent.putExtra("mybundle",bundle);

				// startActivity(intent);
				startActivityForResult(intent, PICK_COLOR_REQUEST_CODE);
			}
		});

		Button addBtn = (Button) findViewById(R.id.Button3);
		addBtn.setText("add color\nusing other Activity");
		addBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						AddColorActivity.class);
				startActivityForResult(intent, ADD_COLOR_REQUEST_CODE);
			}
		});

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case PICK_COLOR_REQUEST_CODE:
				// String clicked = data.getExtras().getString("clicked");
//				int clickedInt = data.getExtras().getInt("clickedInt");
				updateColor(app.clickedInt);

				// iterate to get what is the newColor associated with "value"
				/*
				 * for (int i=0; i<colorList.size(); i++) if
				 * (colorList.get(i).getColorName().equals(clicked)) {
				 * updateColor(i); break; // color is already updated, no need
				 * to finish loop }
				 */
				break;
				
			case ADD_COLOR_REQUEST_CODE:
//				String colorName = data.getExtras().getString("colorName");
//				String colorValue = data.getExtras().getString("colorValue");

//				 Toast.makeText(getApplicationContext(),
//                         "colorName =" + colorName + ", colorValue =" + colorValue
//                         , Toast.LENGTH_SHORT).show();

				 ////				int cv = Color.parseColor(colorValue);
				
//				if (!data.getExtras().getString("returnButton").equals("1")) {
//					Toast.makeText(getApplicationContext(), "return of system",
//							Toast.LENGTH_SHORT).show();					
//				} else {
					
//					colorList.add(new MyColor(colorValue, colorName));

					// value is added to the end of the list
					updateColor(app.colorList.size() - 1);
//				}
				
				

				break;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
