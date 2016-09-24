package testgroup.aryabhata.googlemapapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailsActivity extends AppCompatActivity {
    Button registerbutton;
    SharedPreferences sharedpreferences;
    EditText edit_field;
    final String TAG = "DetailsActivity.java";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addListenerOnButton();

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

        final Context context = this;

        registerbutton = (Button) findViewById(R.id.Register_id);
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        edit_field = (EditText) findViewById(R.id.name_id);
        String name = edit_field.getText().toString();

        edit_field= (EditText) findViewById(R.id.email_id);
        String email = edit_field.getText().toString();

        edit_field = (EditText) findViewById(R.id.vehicle_id);
        String vehiclenumber = edit_field.getText().toString();

        editor.putString("Name",name);
        editor.putString("EmailID", email);
        editor.putString("VehicleNumber", vehiclenumber);
        editor.commit();
        String l= sharedpreferences.getString("Name","deepa");
        Log.e(TAG,"Name from db is " +l);

        registerbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, MapsActivity.class);
                startActivity(intent);

            }

        });

    }

}
