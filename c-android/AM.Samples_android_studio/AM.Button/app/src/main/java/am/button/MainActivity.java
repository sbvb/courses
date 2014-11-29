package am.button;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button mButton;
    EditText mEditText;
    int mCount = 0;

    void updateEditText() {
        mEditText.setText("You clicked " + mCount + " times");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // link variables to Activity elements
        mButton = (Button) findViewById(R.id.button1);
        mEditText = (EditText) findViewById(R.id.editText1);

        // just checking. The code below is not necessary
        if (mButton == null) {
            Log.v("ButtonActivity", "==== myButton == null");
            Toast.makeText(getApplicationContext(), "myButton == null",
                    Toast.LENGTH_LONG).show();
        }

//		mEditText.setText("我爱你");　// 日本語　　我爱你
//        mEditText.setText("something to initialize");
        String initString = getResources().getString(R.string.init_string);
        String MyTranslatedString = getResources().getString(R.string.my_translated_string);

        mEditText.setText(MyTranslatedString);
//        mEditText.setText(initString);




        mButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mCount++;
                updateEditText();
//				mEditText.setText("You clicked " + ++mCount + " times");
            }

        });

        // mButton.setOnClickListener(new OnClickListener() {
        // // @Override
        // public void onClick(View v) {
        // mEditText.setText("You clicked " + ++mCount + " times");
        // }
        // });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

}


//public class MainActivity extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my);
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.my, menu);
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
