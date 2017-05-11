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

public class PhysiotherapistActivity extends AppCompatActivity {
    public ProgressDialog getpDialog1() {
        return pDialog1;
    }

    public void setpDialog1(ProgressDialog pDialog1) {
        this.pDialog1 = pDialog1;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status = 0;

    public void setCategories(JSONArray categories) {
        this.categories = categories;
    }

    public JSONArray getCategories() {
        return categories;
    }

    private ProgressDialog pDialog1;
    private static final String TAG_SUCCESS = "status";
    private static final String TAG_USERS = "physiotherepist";
    JSONParser jParser = new JSONParser();
    String product_id, product_name, product_image, product_desc, product_actual_price, product_selling_price;


    private JSONArray categories = null;
    private static final String LOGIN_URL = "http://ssinfotech.org/health/physiotherepist.php";
    JSONObject json;

    public ListView getMyorderListView() {
        return myorderListView;
    }

    public void setMyorderListView(ListView myorderListView) {
        this.myorderListView = myorderListView;
    }

    private ListView myorderListView;
    ArrayList<MyorderData> myorderArray = new ArrayList<>();
    MyOrderAdapter myorderAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_list);
        setMyorderListView((ListView) findViewById(R.id.listview));
        new Category().execute();
    }


    private class Category extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setpDialog1(new ProgressDialog(PhysiotherapistActivity.this));
            getpDialog1().setMessage("Loading Categories. Please wait...");
            getpDialog1().setIndeterminate(false);
            getpDialog1().setCancelable(false);
            getpDialog1().show();
        }

        protected String doInBackground(String... args) {
            List<NameValuePair> params = new ArrayList<>();
            json = jParser.makeHttpRequest(LOGIN_URL, "GET", params);

            try {
                // Checking for SUCCESS TAG
                setStatus(json.getInt(TAG_SUCCESS));
                if (status == 1) {
                    // products found
                    // Getting Array of Products
                    setCategories(json.getJSONArray(TAG_USERS));

                    // looping through All Products
                    for (int i = 0; i < getCategories().length(); i++) {
                        JSONObject c = getCategories().getJSONObject(i);
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
            getpDialog1().dismiss();
            try {
                if (status == 1) {
                    myorderAdapter = new MyOrderAdapter(PhysiotherapistActivity.this, myorderArray);
                    getMyorderListView().setAdapter(myorderAdapter);
                }
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    }


}
