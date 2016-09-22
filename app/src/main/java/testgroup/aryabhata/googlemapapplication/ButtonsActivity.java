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

/**
 * Created by Sainath on 9/16/2016.
 */
public class ButtonsActivity  extends Activity{
    Button button;
    Button directionbutton;
    final String TAG = "ButtonsActivity.java";
    SharedPreferences sharedpreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        //my code

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {

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
