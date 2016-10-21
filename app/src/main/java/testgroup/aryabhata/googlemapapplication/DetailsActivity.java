package testgroup.aryabhata.googlemapapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {
    Button registerbutton;
    SharedPreferences sharedpreferences;
    EditText edit_field;
    final String TAG = "DetailsActivity.java";
    //
    Animation animShake;
    private EditText signupInputName,signupInputEmail,signupInputVehicle;
    private TextInputLayout signupInputLayoutName,signupInputLayoutEmail,signupInputLayoutVehicle;
    private Vibrator vib;
     private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        /////new code...
        signupInputLayoutName = (TextInputLayout) findViewById(R.id.signup_input_layout_name);
        signupInputLayoutEmail = (TextInputLayout) findViewById(R.id.signup_input_layout_email);
        signupInputLayoutVehicle = (TextInputLayout) findViewById(R.id.signup_input_layout_vehicle);

        signupInputName = (EditText) findViewById(R.id.signup_input_name);
        signupInputEmail = (EditText) findViewById(R.id.signup_input_email);
        signupInputVehicle = (EditText) findViewById(R.id.signup_input_vehicle);
                btnSignUp = (Button) findViewById(R.id.btn_signup);

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();

            }
        });

/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void submitForm() {

        if (!checkName()) {
            signupInputName.setAnimation(animShake);
            signupInputName.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkEmail()) {
            signupInputEmail.setAnimation(animShake);
            signupInputEmail.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkVehicle()) {
            signupInputVehicle.setAnimation(animShake);
            signupInputVehicle.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        signupInputLayoutName.setErrorEnabled(false);
        signupInputLayoutEmail.setErrorEnabled(false);
        signupInputLayoutVehicle.setErrorEnabled(false);
        final Context context = this;

        Intent intent = new Intent(context, MapsActivity.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "You are successfully Registered !!", Toast.LENGTH_SHORT).show();

    }

    private boolean checkName() {
        if (signupInputName.getText().toString().trim().isEmpty()) {

            signupInputLayoutName.setErrorEnabled(true);
            signupInputLayoutName.setError(getString(R.string.err_msg_name));
            signupInputName.setError(getString(R.string.err_msg_required));
            return false;
        }
        signupInputLayoutName.setErrorEnabled(false);
        return true;
    }

    private boolean checkEmail() {
        String email = signupInputEmail.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {

            signupInputLayoutEmail.setErrorEnabled(true);
            signupInputLayoutEmail.setError(getString(R.string.err_msg_email));
            signupInputEmail.setError(getString(R.string.err_msg_required));
            requestFocus(signupInputEmail);
            return false;
        }
        signupInputLayoutEmail.setErrorEnabled(false);
        return true;
    }
    private boolean checkVehicle() {
        if (signupInputVehicle.getText().toString().trim().isEmpty()) {

            signupInputLayoutVehicle.setErrorEnabled(true);
            signupInputLayoutVehicle.setError(getString(R.string.err_msg_vehicle));
            signupInputLayoutVehicle.setError(getString(R.string.err_msg_required));
            return false;
        }
        signupInputLayoutVehicle.setErrorEnabled(false);
        return true;
    }



    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    public void addListenerOnButton() {

        final Context context = this;

        registerbutton = (Button) findViewById(R.id.btn_signup);
        sharedpreferences = getSharedPreferences("Details_db", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        edit_field = (EditText) findViewById(R.id.signup_input_layout_name);
        String name = edit_field.getText().toString();

        edit_field= (EditText) findViewById(R.id.signup_input_layout_email);
        String email = edit_field.getText().toString();

        edit_field = (EditText) findViewById(R.id.signup_input_vehicle);
        String vehiclenumber = edit_field.getText().toString();
        //validation code


        //validation code ends here

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
