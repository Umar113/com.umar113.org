package com.example.android.health.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.health.R;
import com.example.android.health.helpers.RegisterUserClass;
import com.example.android.health.helpers.WebServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Android on 20-02-2017.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText emailForOtpEditText, nameEditText, phoneEditText,passwordSignUpEditText, checkOtpEditText;
    private Button confirmEmailButton, signUpButton, checkOtpButton;
    private TextView loginTextView;
    private String departmentSelected, emailStringForOTP;
    private TextView emailSignUpTextView, otpTextView;
    private RelativeLayout otpRelativeLayout, emailRelativeLayout, mainRegisterRelativeLayout;
    private JSONObject jsonObject;
    private int otp;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        otpRelativeLayout = (RelativeLayout) findViewById(R.id.otpRelativeLayout);
        emailRelativeLayout = (RelativeLayout) findViewById(R.id.emailRelativeLayout);

        mainRegisterRelativeLayout = (RelativeLayout) findViewById(R.id.mainRegisterRelativeLayout);

        otpTextView = (TextView) findViewById(R.id.otpTextView);
        emailForOtpEditText = (EditText) findViewById(R.id.emailForOtpEditText);
        confirmEmailButton = (Button) findViewById(R.id.confirmEmailButton);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailSignUpTextView = (TextView) findViewById(R.id.emailSignUpTextView);
        passwordSignUpEditText = (EditText) findViewById(R.id.passwordSignUpEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);

        checkOtpEditText = (EditText) findViewById(R.id.checkOtpEditText);
        checkOtpButton = (Button) findViewById(R.id.checkOtpButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        loginTextView = (TextView) findViewById(R.id.loginTextView);
        signUpButton.setOnClickListener(this);
        loginTextView.setOnClickListener(this);
        confirmEmailButton.setOnClickListener(this);
        checkOtpButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.confirmEmailButton:
                emailStringForOTP = emailForOtpEditText.getText().toString().trim();
                getOTP(emailStringForOTP);
                break;

            case R.id.signUpButton:
                String name = nameEditText.getText().toString().trim();
                String email = emailSignUpTextView.getText().toString().trim();
                String password = passwordSignUpEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();

                Toast.makeText(getApplicationContext(), departmentSelected, Toast.LENGTH_SHORT).show();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(phone)) {

                    if (TextUtils.isEmpty(name)) {
                        nameEditText.setError("Name is Required");
                    }
                    if (TextUtils.isEmpty(password)) {
                        passwordSignUpEditText.setError("Password is Required");
                    }
                    if (TextUtils.isEmpty(phone)) {
                        phoneEditText.setError("Phone is Required");
                    }

                } else {
                    register(name, email, password, phone);
                }
//                finish();
//                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                break;
            case R.id.loginTextView:
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.checkOtpButton:
                if (checkOtpEditText.getText().toString().equals(Integer.toString(otp))) {
                    otpRelativeLayout.setVisibility(View.GONE);
                    otpTextView.setText("SignUp");
                    mainRegisterRelativeLayout.setVisibility(View.VISIBLE);
                    emailSignUpTextView.setText(emailStringForOTP);
                } else {
                    Toast.makeText(RegisterActivity.this, "OTP is incorrect", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void getOTP(String emailString) {
        //    AsyncTask for Password confirmation
        class GetOTP extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = new ProgressDialog(RegisterActivity.this);
                loading.setMessage("Loading... Please wait!");
                loading.setCancelable(false);
                loading.show();
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("email", params[0]);
                String result = ruc.sendPostRequest(WebServices.GET_OTP_URL, data);
                Log.e("RESULT?????",result);
                return result;
            }

            @Override
            protected void onPostExecute(String response) {
                super.onPostExecute(response);
//                Log.d(">>>>response", response);
                Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                loading.dismiss();
                try {
                    jsonObject = new JSONObject(response);
                    if (jsonObject.has("status") || jsonObject.has("1")) {
                        otp = jsonObject.getInt("otp");
                        Log.d(">>>>otp", Integer.toString(otp));
                        emailRelativeLayout.setVisibility(View.GONE);
                        otpRelativeLayout.setVisibility(View.VISIBLE);
                        otpTextView.setText("Confirm OTP");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (response.equals("please POST all values")) {
                }
            }
        }
        GetOTP confirmPassword = new GetOTP();
        confirmPassword.execute(emailString);
    }

    private void register(String username, String email, String password, String phone) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass registerUserClass = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = new ProgressDialog(RegisterActivity.this);
                loading.setMessage("Loading...Please wait!");
                loading.show();
                loading.setCancelable(false);
            }


            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String, String>();
                data.put("name", params[0]);
                data.put("email", params[1]);
                data.put("password", params[2]);
                data.put("contact_no", params[3]);
                String result = registerUserClass.sendPostRequest(WebServices.REGISTER_USER_URL, data);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(username, email, password, phone);

    }
}

