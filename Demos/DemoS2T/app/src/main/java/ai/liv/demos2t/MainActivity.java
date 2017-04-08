/*******************************************************
 * Copyright (C) 2015-2016, Liv.AI
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This can not be copied and/or distributed without the
 * permission of Liv Artificial Intelligence Pvt. Ltd.
 *******************************************************/
package ai.liv.demos2t;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import ai.liv.s2tlibrary.Speech2TextIntent;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_lang);
        CharSequence lang = prefs.getString("pref_language", Speech2TextIntent.LANGUAGE_ENGLISH);
        Log.v("Language already set: " , lang.toString());
        item.setTitle(lang);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            Intent intent = new Intent(this, PreferenceActivity.class);
//            startActivity(intent);
            return true;
        } else if (id == R.id.action_lang) {
            CharSequence title = item.getTitle();
            if (title.equals(Speech2TextIntent.LANGUAGE_ENGLISH)) {
                item.setTitle(Speech2TextIntent.LANGUAGE_PUNJABI);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("pref_language", Speech2TextIntent.LANGUAGE_PUNJABI);
                editor.commit();
            }
            else if(title.equals(Speech2TextIntent.LANGUAGE_PUNJABI)){
                item.setTitle(Speech2TextIntent.LANGUAGE_KANNADA);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("pref_language", Speech2TextIntent.LANGUAGE_KANNADA);
                editor.commit();
            }
            else if(title.equals(Speech2TextIntent.LANGUAGE_KANNADA)){
                item.setTitle(Speech2TextIntent.LANGUAGE_BENGALI);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("pref_language", Speech2TextIntent.LANGUAGE_BENGALI);
                editor.commit();
            }
            else if(title.equals(Speech2TextIntent.LANGUAGE_BENGALI)){
                item.setTitle(Speech2TextIntent.LANGUAGE_TELUGU);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("pref_language", Speech2TextIntent.LANGUAGE_TELUGU);
                editor.commit();
            }
            else if(title.equals(Speech2TextIntent.LANGUAGE_TELUGU)){
                item.setTitle(Speech2TextIntent.LANGUAGE_HINDI);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("pref_language", Speech2TextIntent.LANGUAGE_HINDI);
                editor.commit();
            }
            else if(title.equals(Speech2TextIntent.LANGUAGE_HINDI)){
                item.setTitle(Speech2TextIntent.LANGUAGE_MARATHI);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("pref_language", Speech2TextIntent.LANGUAGE_MARATHI);
                editor.commit();
                return true;
            }
            else if (title.equals(Speech2TextIntent.LANGUAGE_MARATHI)) {
                item.setTitle(Speech2TextIntent.LANGUAGE_GUJARATI);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("pref_language", Speech2TextIntent.LANGUAGE_GUJARATI);
                editor.commit();
                return true;
            }
            else if (title.equals(Speech2TextIntent.LANGUAGE_GUJARATI)) {
                item.setTitle(Speech2TextIntent.LANGUAGE_ENGLISH);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("pref_language", Speech2TextIntent.LANGUAGE_ENGLISH);
                editor.commit();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
