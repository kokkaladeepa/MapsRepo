package testgroup.aryabhata.googlemapapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VehicleDetails extends AppCompatActivity {
//    SharedPreferences sharedpreferences;
    Button share_button;
    EditText data;
    String data_info;
    SharedPreferences sharedpreferences;
    String s;
    String[] key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);

        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        s= sharedpreferences.getString("shorturl","defaultshorturl");
        EditText data_field = (EditText) findViewById(R.id.data_id);
        //
        // String s=var.getInString();
        System.out.println("ID in form is :" + s);

        key =s.split("gl/");
        System.out.println("code is:" +  key[1]);
        // location.setText(sharedpreferences.getString("locationkey", "deepa"));
        //new
        data_field.setText(key[1]);
        addListenerOnButton();

    }
    public void addListenerOnButton() {

        final Context context = this;

        share_button = (Button) findViewById(R.id.sharebutton);
        data = (EditText) findViewById(R.id.data_id);
        data_info = data.getText().toString();

        share_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                shareIt();

            }

        });
    }
       private void shareIt()  {
               Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
           data = (EditText) findViewById(R.id.data_id);
           data_info = data.getText().toString();


        System.out.println("my data is" +  data_info);
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AndroidSolved");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,data_info);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}
