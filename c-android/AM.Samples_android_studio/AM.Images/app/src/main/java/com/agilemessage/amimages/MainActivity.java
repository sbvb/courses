package com.agilemessage.amimages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	final int PICK_IMAGE_REQUEST_CODE = 10; // any non repetitive number here

	Button btImages;
	Button btImagesMT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btImages = (Button) findViewById(R.id.Button01);
		btImages.setOnClickListener(new OnClickListener() {
			// @Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						ImagesActivity.class);
				startActivity(intent);
//				startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
//				overridePendingTransition(R.anim.fade, R.anim.hold);
//				overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);

			}
		});
		
		btImagesMT = (Button) findViewById(R.id.button_mt);
		btImagesMT.setOnClickListener(new OnClickListener() {
			// @Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						ImagesMTActivity.class);
				startActivity(intent);
			}
		});

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case PICK_IMAGE_REQUEST_CODE:
				// String clicked = data.getExtras().getString("clicked");
				// int clickedInt = data.getExtras().getInt("clickedInt");

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
