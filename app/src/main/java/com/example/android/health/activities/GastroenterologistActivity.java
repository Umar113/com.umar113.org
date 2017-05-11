package com.example.android.health.activities;

import android.app.ProgressDialog;
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

/**
 * Created by Android on 25-01-2017.
 */
public class GastroenterologistActivity extends AppCompatActivity {
    private ProgressDialog pDialog1;
    private static final String TAG_SUCCESS = "status";
    private static final String TAG_USERS = "gastroentrologist";
    JSONParser jParser = new JSONParser();
    String product_id, product_name, product_image, product_desc, product_actual_price, product_selling_price;
    int status = 0;
    JSONArray categories = null;


    public static final String LOGIN_URL = "http://ssinfotech.org/health/gastroentrologist.php";
    JSONObject json_obj;

    ListView myorderListView;
    ArrayList<MyorderData> myorderArray = new ArrayList<MyorderData>();
    MyOrderAdapter myorderAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_list);

        myorderListView = (ListView) findViewById(R.id.listview);

        // myorderTask();
        new Category().execute();


//        myorderArray.add(new MyorderData("", "order", "date", "status", "total"));
//        myorderArray.add(new MyorderData("", "order", "date", "status", "total"));
//        myorderArray.add(new MyorderData("", "order", "date", "status", "total"));
//        myorderArray.add(new MyorderData("", "order", "date", "status", "total"));
//        myorderArray.add(new MyorderData("", "order", "date", "status", "total"));
//        myorderArray.add(new MyorderData("", "order", "date", "status", "total"));
//        myorderArray.add(new MyorderData("", "order", "date", "status", "total"));
//        myorderArray.add(new MyorderData("", "order", "date", "status", "total"));
//        myorderArray.add(new MyorderData("", "order", "date", "status", "total"));
//
//
//        myorderAdapter = new MyOrderAdapter(Swipetodelet.this, myorderArray);
//        myorderListView.setAdapter(myorderAdapter);

    }


    class Category extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog1 = new ProgressDialog(GastroenterologistActivity.this);
            pDialog1.setMessage("Loading Categories. Please wait...");
            pDialog1.setIndeterminate(false);
            pDialog1.setCancelable(false);
            pDialog1.show();
        }

        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();

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
                    myorderAdapter = new MyOrderAdapter(GastroenterologistActivity.this, myorderArray);
                    myorderListView.setAdapter(myorderAdapter);


//                    productsListDb
                } else {
                    //Toast.makeText(getApplicationContext(),"Wrong credentials..", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO: handle exception
                Log.e("DATA", "Error");
            }
        }
    }


}
