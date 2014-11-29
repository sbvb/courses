package com.agilemessage.ammultiactivityglobalapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class AddColorActivity extends Activity {

    // the application instance
    MyApplication app;


	EditText colorNameEt;
	EditText colorValueEt;
	SeekBar sb_r, sb_g, sb_b;
	int r, g, b;

	String int2hex(int i) {
		if (i < 16)
			return "0" + Integer.toHexString(i);
		else
			return Integer.toHexString(i);
	}

	void updateColorValue() {
		String strColor = "#" + int2hex(r) + int2hex(g) + int2hex(b);
//		colorNameEt.setText(strColor);
		colorValueEt.setText(strColor);
		colorValueEt.setBackgroundColor(Color.parseColor(strColor));
		colorValueEt.setTextColor(Color.parseColor(strColor) ^ 0xFFFFFF);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_color_layout);

        // Get the application instance
        app = (MyApplication) getApplication();


		colorNameEt = (EditText) findViewById(R.id.color_name);
		// colorNameEt.setText("color name");

		colorValueEt = (EditText) findViewById(R.id.color_value);
		// colorValueEt.setText("#ffcc09");

		sb_r = (SeekBar) findViewById(R.id.seekBar_r);
		sb_g = (SeekBar) findViewById(R.id.seekBar_g);
		sb_b = (SeekBar) findViewById(R.id.seekBar_b);
		
		

		sb_r.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				r = (int) (progress * 255.0 / 100);
				updateColorValue();
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
		        Toast.makeText(getApplicationContext(), "onStartTrackingTouch, r",
                        Toast.LENGTH_SHORT).show();
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
		        Toast.makeText(getApplicationContext(), "onStopTrackingTouch, r",
                        Toast.LENGTH_SHORT).show();
			}
		});

		sb_g.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				g = (int) (progress * 255.0 / 100);
				updateColorValue();
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});

		sb_b.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				b = (int) (progress * 255.0 / 100);
				updateColorValue();
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});

		Button okBtn = (Button) findViewById(R.id.ok_button);
		okBtn.setText("ok");
		okBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent answer = new Intent();
                app.colorList.add(new MyColor(colorValueEt.getText().toString(), colorNameEt.getText().toString()));
//				answer.putExtra("colorName", colorNameEt.getText().toString());
//				answer.putExtra("colorValue", colorValueEt.getText().toString());
				setResult(Activity.RESULT_OK, answer);
				finish();
			}
		});
	}

}
