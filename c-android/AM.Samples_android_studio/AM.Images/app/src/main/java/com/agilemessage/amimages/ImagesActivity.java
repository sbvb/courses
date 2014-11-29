package com.agilemessage.amimages;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.LinkedList;

public class ImagesActivity extends Activity {

	RelativeLayout layoutMain;
	int i = 0;
	LinkedList<Bitmap> imageList;
	ImageView imageView;
	Context context = this;

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

    @Override
    public void onResume() {
        super.onResume();
        createList();
    }

    void createList() {
        imageList = new LinkedList<Bitmap>();
        addBitmapToList(R.drawable.cameron_dias);
        addBitmapToList(R.drawable.christina_aguilera);
        addBitmapToList(R.drawable.paz_vega);
        addBitmapToList(R.drawable.lady_gaga);
        addBitmapToList(R.drawable.carol_nakamura);
        addBitmapToList(R.drawable.camila_pitanga);
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

        createList();

		// imageList.add(BitmapFactory.decodeResource(getResources(),
		// R.drawable.cameron));
		// imageList.add(BitmapFactory.decodeResource(getResources(),
		// R.drawable.christina_aguilera));
		// imageList.add(BitmapFactory.decodeResource(getResources(),
		// R.drawable.paz_vega));

		// remove title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// remove message bar
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);


		layoutMain = new RelativeLayout(context);
		layoutMain.setLayoutParams(new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT/* width */,
				RelativeLayout.LayoutParams.MATCH_PARENT /* height */));

		imageView = new ImageView(context);
		imageView.setImageBitmap(imageList.get(0));
		layoutMain.addView(imageView);
		setContentView(layoutMain);

//		int xpadding = 0;
//		int delta_x = 10;
//		for (int k = 0; k < 5; k++) {
//			xpadding += delta_x;
//	        Log.v("xpadding=" + xpadding,"==== Click Image");            
//			imageView.setPadding(xpadding, 0, 0, 0);
//			imageView.invalidate();
////			setContentView(layoutMain);
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

		
		imageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				i++;
				if (i >= imageList.size())
					i = 0;
				imageView.setImageBitmap(imageList.get(i));

//				int xpadding = 0;
//				int delta_x = 10;
//				for (int k = 0; k < 5; k++) {
//					xpadding += delta_x;
//			        Log.v("xpadding=" + xpadding,"==== Click Image");            
//					imageView.setPadding(xpadding, 0, 0, 0);
//					arg0.invalidate();
////					imageView.invalidate();
////					setContentView(layoutMain);
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
			}

		});

	}

}
