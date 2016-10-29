package testgroup.aryabhata.googlemapapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button Submit;
    EditText mInputUrl;
    TextView mOutput;
    final String TAG = "MainActivity.java";
    private static Context mContext = null;
    public String db_loc;
    static String address = "https://www.googleapis.com/urlshortener/v1/url?key=AIzaSyCcqwt_B9IyhVaqCOtzCNAhwT3Mo4o9NwE";
    SharedPreferences sharedpreferences;
    String id=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        db_loc = sharedpreferences.getString("locationkey", "novalue");
        Submit = (Button) findViewById(R.id.submit);
        mInputUrl = (EditText) findViewById(R.id.LongUrl);
        mOutput = (TextView) findViewById(R.id.ShortUrl);
        Submit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                //new URLShort().execute();
                mOutput.setText("");
                newShortAsync task = new newShortAsync();
                task.execute();
            }
        });
    }
    public class newShortAsync extends AsyncTask<Void, Void, String> {

        String longUrl = "http://maps.google.com/maps?saddr=My+Location&daddr="+db_loc;
        //String longUrl = mInputUrl.getText().toString();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //progressBar.setVisibility(View.GONE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            System.out.println("JSON RESP:" + s);
            String response = s;
            try {
                JSONObject jsonObject = new JSONObject(response);
                id = jsonObject.getString("id");
                mOutput.setText(id);
                System.out.println("JSON ID is :" + id);
                editor.putString("idurl",id);
                String generatedkey[]=id.split("gl/");
                System.out.println("generated location key is :" + generatedkey[0]);
                editor.putString("generatedkey",id);

                editor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(Void... params) {
            BufferedReader reader;
            StringBuffer buffer;
            String res = null;
            String json = "{\"longUrl\": \"" + longUrl + "\"}";
            try {
                URL url = new URL("https://www.googleapis.com/urlshortener/v1/url?key=AIzaSyCcqwt_B9IyhVaqCOtzCNAhwT3Mo4o9NwE");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setReadTimeout(40000);
                con.setConnectTimeout(40000);
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                OutputStream os = con.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));

                writer.write(json);
                writer.flush();
                writer.close();
                os.close();

                int status = con.getResponseCode();
                InputStream inputStream;
                if (status == HttpURLConnection.HTTP_OK)
                    inputStream = con.getInputStream();
                else
                    inputStream = con.getErrorStream();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                res = buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return res;


        }
    }
}
