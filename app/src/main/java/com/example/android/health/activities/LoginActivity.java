package com.example.android.health.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.health.R;
import com.example.android.health.helpers.RegisterUserClass;
import com.example.android.health.helpers.WebServices;
import com.example.android.health.sharedPreferences.SessionManager;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;


public class LoginActivity  extends AppCompatActivity implements View.OnClickListener {

    private EditText emailEditText, passwordEditText;
    private TextView signUpTextView,forgotPasswordTextView;
    private Button loginButton;
    private SessionManager sessionManager;
    private JSONObject json_obj;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        signUpTextView = (TextView) findViewById(R.id.signUpTextView);
        forgotPasswordTextView = (TextView) findViewById(R.id.forgotPasswordTextView);
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);
        forgotPasswordTextView.setOnClickListener(this);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginButton:
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

                    if (TextUtils.isEmpty(email)) {
                        emailEditText.setError("Email is required");
                    }
                    if (TextUtils.isEmpty(password)) {
                        passwordEditText.setError("Password is required");
                    }
                } else {
                    login(email, password);
                }
                break;
            case R.id.signUpTextView:
                finish();
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.forgotPasswordTextView:
                finish();
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
                break;
        }
    }

    private void login(String email, String password) {
        class LoginUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = new ProgressDialog(LoginActivity.this);
                loading.setMessage("Loading...Please wait!");
                loading.setCancelable(false);
                loading.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                try {
                    json_obj = new JSONObject(s);
                    Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                    if (json_obj.has("status") || json_obj.has("1")) {

//                        JSONArray jsonArray = new JSONArray(`s);
                        JSONObject jsonObject = new JSONObject(s);
//                        JSONObject userJsonObject = jsonObject.getJSONObject("id");
                        int id = jsonObject.getInt("id");
//                        int idInt = Integer.parseInt(id);
//                        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
                        sessionManager.createLoginSession(id);
                        Intent i = new Intent(getApplicationContext(), DoctorListActivity.class);
                        startActivity(i);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("email", params[0]);
                data.put("password", params[1]);
                String result = ruc.sendPostRequest(WebServices.LOGIN_USER_URL, data);
                Log.e(">>>>>>>LOGIN",result);
                return result;
            }
        }
        LoginUser loginUser = new LoginUser();
        loginUser.execute(email, password);
    }

}

