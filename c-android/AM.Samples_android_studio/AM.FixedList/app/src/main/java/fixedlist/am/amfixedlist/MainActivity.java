package fixedlist.am.amfixedlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

    String[] names = { "Sergio", "Marcia", "Camila", "Mateus", "Heraldo",
            "Adilson", "Nelson", "Jorge", "Felipe", "Solange", "Juliana",
            "Mariana", "日本語" };

    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // array based on an String array does not accept creation or deletion
        // of items
        ArrayAdapter<String> fixedAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);


        lv = (ListView) findViewById(R .id.listView);

        String rclass = R.class.getName();
        Toast.makeText(getApplicationContext(), "rclass =" + rclass,
                Toast.LENGTH_LONG).show();

        // bind adapter to ListActivity
        lv.setAdapter(fixedAdapter);
//		setListAdapter(fixedAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
