package com.example.android.health.activities;

import android.app.ProgressDialog;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.android.health.JSONParser;
import com.example.android.health.R;
import com.example.android.health.adapter.MyOrderAdapter;
import com.example.android.health.adapter.MyorderData;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class DietitionActivity extends AppCompatActivity {

    private ProgressDialog pDialog1;
    private static final String TAG_SUCCESS = "status";
    private static final String TAG_USERS = "dietitian";
    JSONParser jParser = new JSONParser();
    int status = 0;
    JSONArray categories = null;


    public static final String LOGIN_URL = "http://ssinfotech.org/health/dietitian.php";

    Double fromlat, fromlong;


    ListView myorderListView;
    ArrayList<MyorderData> myorderArray = new ArrayList<MyorderData>();
    MyOrderAdapter myorderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_list);
        //Initialization of ListView
        myorderListView = (ListView) findViewById(R.id.listview);
        new Category().execute();


    }


    private class Category extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog1 = new ProgressDialog(DietitionActivity.this);
            pDialog1.setMessage("Loading Data. Please wait...");
            pDialog1.setIndeterminate(false);
            pDialog1.setCancelable(false);
            pDialog1.show();
        }

        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            Geocoder coder = new Geocoder(DietitionActivity.this, Locale.ENGLISH);

            JSONObject json = jParser.makeHttpRequest(LOGIN_URL, "GET", params);
            Log.d("All Products:>>>>>>>>> ", json.toString());

            try {
                // Checking for SUCCESS TAG
                status = json.getInt(TAG_SUCCESS);

                Log.d("Response:>>>>>>>>> ", String.valueOf(status));

                if (status == 1) {
                    // products found
                    // Getting Array of Products
                    categories = json.getJSONArray(TAG_USERS);

                    // looping through All Products
                    for (int i = 0; i < categories.length(); i++) {
                        JSONObject c = categories.getJSONObject(i);
                        String name = c.getString("name");
                        String hospital = c.getString("hospital");
                        String address = c.getString("address");
                        String exp = c.getString("exp");
                        String contact=c.getString("contact");
                       // String faddress=c.getString("faddress");
                        myorderArray.add(new MyorderData(name,hospital,address,exp,contact,""));


                    }
                } else {
                    Log.e("ServiceHandler", "Couldn't get any data from the url");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog1.dismiss();
            try {
                if (status == 1) {
//                    Toast.makeText(getApplicationContext(), "Successfully Taken", Toast.LENGTH_SHORT).show();
                    myorderAdapter = new MyOrderAdapter(DietitionActivity.this, myorderArray);
                    myorderListView.setAdapter(myorderAdapter);


//                    productsListDb
                }
            } catch (Exception e) {
                Log.e("DATA", "Error");
            }
        }
    }

}
