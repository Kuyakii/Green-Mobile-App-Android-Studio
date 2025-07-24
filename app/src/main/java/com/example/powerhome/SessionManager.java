package com.example.powerhome;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    public static final String HOST = "http://192.168.1.134"; // A changer
    private static final String PREF_NAME = "powerhome_session";
    private static final String KEY_ID = "id_resident";
    private static final String KEY_NOM = "nom";
    private static final String KEY_PRENOM = "prenom";
    private static final String KEY_EMAIL = "email";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void createLoginSession(int id, String nom, String prenom, String email) {
        editor.putInt(KEY_ID, id);
        editor.putString(KEY_NOM, nom);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PRENOM, prenom);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return prefs.contains(KEY_ID);
    }

    public int getId() {
        return prefs.getInt(KEY_ID, -1);
    }

    public String getNom() {
        return prefs.getString(KEY_NOM, null);
    }
    public String getPrenom() {
        return prefs.getString(KEY_PRENOM, null);
    }

    public String getEmail() {
        return prefs.getString(KEY_EMAIL, null);
    }
    public void setEcoCoins(int coins) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("eco_coin", coins);
        editor.apply();
    }

    public int getEcoCoins() {
        return prefs.getInt("eco_coin", 0);
    }

    public void logout() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }
}
