/*******************************************************
 * Copyright (C) 2015-2016, Liv.AI
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This can not be copied and/or distributed without the
 * permission of Liv Artificial Intelligence Pvt. Ltd.
 *******************************************************/
package ai.liv.demos2t;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import ai.liv.s2tlibrary.Speech2TextIntent;
import ai.liv.s2tlibrary.model.S2TError;
import ai.liv.s2tlibrary.model.Transcription;

/**
 * Created by garima on 01/03/16.
 */
public class MainActivityFragment extends Fragment {


    private static final String TAG = MainActivityFragment.class.getName();
    ImageButton b1;
    TextView contentView1;
    ProgressBar pb;
    Speech2TextIntent s2TIntent;
    String lang;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,
                container, false);
        contentView1 = (TextView) view.findViewById(R.id.contentTextView1);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        pb = (ProgressBar) view.findViewById(R.id.progress_indicator);

        b1 = (ImageButton) view.findViewById(R.id.button1);

        s2TIntent = new Speech2TextIntent.Speech2TextIntentBuilder(getActivity(), new Speech2TextIntent.Speech2TextIntentCallback() {
            @Override
            public void onTranscriptionReceived(ArrayList<Transcription> transcriptions) {
                if(!isDetached()) {
                    if (transcriptions.size() > 0) {
                        if(getView() != null) {
                            contentView1.setText(transcriptions.get(0).getText());
                        }
                    }
                }
            }

            @Override
            public void onRecordingCancelled() {

            }

            @Override
            public void onError(S2TError error) {
                if(error.errorCode == S2TError.ERROR_NO_USER_ID){
                    Log.d(TAG, "No user id");
                }

            }

        }).setLanguage(lang).setView(Speech2TextIntent.VIEW_KEYBOARD).build();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lang = prefs.getString("pref_language", Speech2TextIntent.LANGUAGE_ENGLISH);
                s2TIntent.setLanguage(lang);
                s2TIntent.startService();
            }
        });
        return view;
    }
}
