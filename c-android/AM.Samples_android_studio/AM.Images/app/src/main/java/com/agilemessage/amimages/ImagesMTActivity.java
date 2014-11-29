package com.agilemessage.amimages;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import java.util.LinkedList;

public class ImagesMTActivity extends Activity implements OnTouchListener {
	
	FrameLayout layoutMain;
	int i = 0;
	LinkedList<Bitmap> imageList;
	ImageView imageView;
	Context context = this;
	
	MyMultiTouchOnTouchListener mtListener =
            new MyMultiTouchOnTouchListener(this);

	public static Bitmap ScaleBitmap(Bitmap bm, float scalingFactor) {
		int dstHeight = (int) (bm.getHeight() * scalingFactor);
		int dstWidth = (int) (bm.getWidth() * scalingFactor);
		return Bitmap.createScaledBitmap(bm, dstWidth, dstHeight, true);
	}

	private float getBitmapScalingFactor(Bitmap bm) {
		// Get display width from device
		// int displayWidth = getWindowManager().getDefaultDisplay().getWidth();
		int displayWidth;
		int displayHeight;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// require api level >= 13
			Point point = new Point();
			getWindowManager().getDefaultDisplay().getSize(point);
			displayWidth = point.x;
			displayHeight = point.y;

		} else {
			// works for api level < 13
			displayWidth = getWindowManager().getDefaultDisplay().getWidth();
			displayHeight = getWindowManager().getDefaultDisplay().getHeight();
		}

		if (displayWidth > displayHeight)
			return ((float) displayHeight / (float) bm.getHeight());
		else
			return ((float) displayWidth / (float) bm.getWidth());
	}

	@Override
	public void onPause() {
		super.onPause();
		imageList = null;
	}

	void addBitmapToList(int id) {
		// Bitmap bm=
		// Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(),id));
		Bitmap bm = BitmapFactory.decodeResource(getResources(), id);
		float scale = getBitmapScalingFactor(bm);
		imageList.add(ScaleBitmap(bm, scale));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_images);

//		Toast.makeText(getApplicationContext(), "onCreate()", Toast.LENGTH_LONG)
//				.show();

		imageList = new LinkedList<Bitmap>();
//		addBitmapToList(R.drawable.cameron_dias);
		addBitmapToList(R.drawable.christina_aguilera);
		addBitmapToList(R.drawable.paz_vega);
		addBitmapToList(R.drawable.lady_gaga);
		addBitmapToList(R.drawable.carol_nakamura);
		addBitmapToList(R.drawable.camila_pitanga);

		// remove title
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// remove message bar
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		layoutMain = new FrameLayout(context);
		layoutMain.setLayoutParams(new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT/* width */,
				FrameLayout.LayoutParams.MATCH_PARENT /* height */));

		imageView = new ImageView(context);
		imageView.setImageBitmap(imageList.get(0));
		layoutMain.addView(imageView);
		setContentView(layoutMain);
		
		imageView.setScaleType(ScaleType.MATRIX);
		imageView.setOnTouchListener(this);
		
		mtListener.setZoomLimit(100f, 0.1f);

		//		imageView.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				i++;
//				if (i >= imageList.size())
//					i = 0;
//				imageView.setImageBitmap(imageList.get(i));
//			}
//
//		});

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return mtListener.onTouch(v,event);
	}


}
