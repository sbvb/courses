package am.buttoncheck;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputType;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button mButton;
    EditText mEditText;
    CheckBox mCheckColor;
    CheckBox mCheckBold;
    SeekBar mSeekBar;
    int mCount = 0;
    float f_progress;
    final float min_size = 18.0f;

    void updateStyle() {

        if (mCheckColor.isChecked())
            mEditText.setTextColor(Color.CYAN);
        else
            mEditText.setTextColor(Color.BLACK);

        if (mCheckBold.isChecked()) {
            // Log.w("MainActivity","=== mCheckBold.isChecked()");
            // mEditText.setTypeface(Typeface.MONOSPACE);
            mEditText.setTypeface(Typeface.DEFAULT_BOLD);
        } else
            // mEditText.setTypeface(Typeface.DEFAULT);
            mEditText.setTypeface(null, Typeface.ITALIC);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // link variables to Activity elements
        mButton = (Button) findViewById(R.id.button1);
        mEditText = (EditText) findViewById(R.id.editText1);
        mCheckColor = (CheckBox) findViewById(R.id.check_color);
        mCheckBold = (CheckBox) findViewById(R.id.check_bold);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar1);

        mEditText.setText(getString(R.string.inic_text));

        mButton.setText(getString(R.string.button));

        mEditText.setTextSize(min_size);

        String colorStr = "#AA0000";
        mEditText.setTextColor(Color.parseColor(colorStr));

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                f_progress = progress / 100.0f * 30.0f + min_size;
                mEditText.setTextSize(f_progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mButton.setOnClickListener(new OnClickListener() {
            // @Override
            public void onClick(View v) {

                updateStyle();

                mEditText.setText(getString(R.string.you_clicked) + " "
                        + ++mCount + " " + getString(R.string.times));
            }
        });

        mCheckBold.setOnClickListener(new OnClickListener() {
            // @Override
            public void onClick(View v) {
                updateStyle();
            }
        });

        mCheckColor.setOnClickListener(new OnClickListener() {
            // @Override
            public void onClick(View v) {
                updateStyle();
            }
        });

    }

    // @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}

//public class MainActivity extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}
