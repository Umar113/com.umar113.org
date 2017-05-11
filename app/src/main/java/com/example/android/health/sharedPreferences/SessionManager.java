package com.example.android.health.sharedPreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.android.health.activities.DoctorListActivity;
import com.example.android.health.activities.LoginActivity;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences loginSharedPreferences;
    SharedPreferences.Editor loginEditor;
    Context context;
    int PRIVATE_MODE = 0;
    public static final String PREF_NAME = "CanteenLoginPref";
    public static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_EMAIL = "email";

    public static final String KEY_ID = "id";

    public SessionManager(Context context) {
        this.context = context;
        loginSharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        loginEditor = loginSharedPreferences.edit();
    }

    public void createLoginSession(int id) {
        loginEditor.putBoolean(IS_LOGIN, true);
        loginEditor.putInt(KEY_ID, id);
        loginEditor.commit();
        Toast.makeText(context, "successful login", Toast.LENGTH_SHORT).show();
    }

    public void checkLogin() {
        if (this.isLoggedIn()) {
            Intent intent = new Intent(context, DoctorListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);
        }
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_ID, String.valueOf(Integer.parseInt(String.valueOf(loginSharedPreferences.getInt(KEY_ID, 0)))));
        user.put(KEY_EMAIL, loginSharedPreferences.getString(KEY_EMAIL, null));
        return user;
    }

    public void logoutUser() {

        loginEditor.clear();
        loginEditor.commit();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public boolean isLoggedIn() {
        return loginSharedPreferences.getBoolean(IS_LOGIN, false);
    }
}
