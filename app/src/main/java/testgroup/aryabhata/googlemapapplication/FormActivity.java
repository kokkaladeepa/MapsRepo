package testgroup.aryabhata.googlemapapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {
    Button submitButton;
    SharedPreferences sharedpreferences;
    final String TAG = "FormActivity.java";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
//button action
        addListenerOnButton();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void addListenerOnButton() {
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        final Context context = this;

        submitButton = (Button) findViewById(R.id.submitbutton_id);
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
               EditText location = (EditText) findViewById(R.id.Location_id);
              String location_string = location.getText().toString();
                int location_value = Integer.parseInt( location.getText().toString() );

            //store the data
                SharedPreferences.Editor editor = sharedpreferences.edit();
                Log.e(TAG,"before " + location_value);
                editor.putInt("locationid", location_value);
                editor.commit();

                int a= sharedpreferences.getInt("locationid",1);
                Log.e(TAG,"after: " + a);

                Toast.makeText(FormActivity.this,
                        location_string,
                        Toast.LENGTH_SHORT).show();


            }

        });

        //code for map
       /* submitButton.setOnClickListener(new View.OnClickListener() {
//dest addr lon and ltt shud be fetched from db which has been stored already
//source addr has to be captured
            public void onClick(final View view) {
                final Intent intent = new Intent(Intent.ACTION_VIEW,
                        *//** Using the web based turn by turn directions url. *//*
                        Uri.parse(
                                "http://maps.google.com/maps?" +
                                        "saddr=43.0054446,-87.9678884" +
                                        "&daddr=42.9257104,-88.0508355"));
                startActivity(intent);
            }
        });*/

    }
}
