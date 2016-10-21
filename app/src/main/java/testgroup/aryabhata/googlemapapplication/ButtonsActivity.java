package testgroup.aryabhata.googlemapapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.FrameLayout;

/**
 * Created by Sainath on 9/16/2016.
 */
public class ButtonsActivity  extends MenuActivity{
    Button capturebutton;
    Button directionbutton;
    final String TAG = "ButtonsActivity.java";
    SharedPreferences sharedpreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        //my code

        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frames);
        getLayoutInflater().inflate(R.layout.activity_buttons, contentFrameLayout);


       // setContentView(R.layout.activity_buttons);
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        final Context context = this;

        capturebutton = (Button) findViewById(R.id.capture_id);

        capturebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, MapsActivity.class);
                startActivity(intent);

            }

        });
        directionbutton=(Button) findViewById(R.id.directions_id);
        directionbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
               //code for default text setting


                Intent intent = new Intent(context, FormActivity.class);
                startActivity(intent);

            }

        });
    }

}
