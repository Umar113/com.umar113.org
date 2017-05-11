package com.example.android.health.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class PediatricianActivity extends AppCompatActivity {
    private ProgressDialog pDialog1;
    private static final String TAG_SUCCESS = "status";
    private static final String TAG_USERS = "peditrician";
    JSONParser jParser = new JSONParser();
    String product_id, product_name, product_image, product_desc, product_actual_price, product_selling_price;
    int status = 0;
    JSONArray categories = null;
    public static final String LOGIN_URL = "http://ssinfotech.org/health/peditrician.php";
    JSONObject json_obj;
    ListView myorderListView;
    ArrayList<MyorderData> myorderArray = new ArrayList<>();
    MyOrderAdapter myorderAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_list);
        myorderListView = (ListView) findViewById(R.id.listview);
        new Category().execute();
    }


    class Category extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog1 = new ProgressDialog(PediatricianActivity.this);
            pDialog1.setMessage("Loading Categories. Please wait...");
            pDialog1.setIndeterminate(false);
            pDialog1.setCancelable(false);
            pDialog1.show();
        }

        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<>();

            JSONObject json = jParser.makeHttpRequest(LOGIN_URL, "GET", params);

            try {
                // Checking for SUCCESS TAG
                status = json.getInt(TAG_SUCCESS);
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
                        //String faddress=c.getString("faddress");
                        myorderArray.add(new MyorderData(name,hospital,address,exp,contact,""));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }


        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog1.dismiss();
            try {
                if (status == 1) {
                    myorderAdapter = new MyOrderAdapter(PediatricianActivity.this, myorderArray);
                    myorderListView.setAdapter(myorderAdapter);
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

}
