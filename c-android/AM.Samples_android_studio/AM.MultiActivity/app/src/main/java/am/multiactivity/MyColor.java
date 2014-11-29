package am.multiactivity;

import android.graphics.Color;

// POJO = Pure Object Java Object
public class MyColor {
	int color; // RGB of color
	String colorName;
	
	MyColor(String s, String name) {
		color = Color.parseColor(s);
		colorName = name;
	}
	
	MyColor(int cin, String name) {
		color = cin;
		colorName = name;
	}

	int getColor() {
		return color;
	}
	
	String getColorName() {
		return colorName;
	}
}

