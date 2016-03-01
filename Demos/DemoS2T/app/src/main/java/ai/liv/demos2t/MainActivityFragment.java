/*******************************************************
 * Copyright (C) 2015-2016, Liv.AI
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This can not be copied and/or distributed without the
 * permission of Liv Artificial Intelligence Pvt. Ltd.
 *******************************************************/
package ai.liv.demos2t;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import ai.liv.s2tlibrary.Speech2TextIntent;

/**
 * Created by garima on 01/03/16.
 */
public class MainActivityFragment extends Fragment {
    private static final String TAG = MainActivityFragment.class.getName();

    ImageButton b1;
    TextView contentView1, contentView2, contentView3, contentView4;
    TextView[] textViewList = new TextView[4];
    ProgressBar pb;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,
                container, false);
        contentView1 = (TextView) view.findViewById(R.id.contentTextView1);
        contentView2 = (TextView) view.findViewById(R.id.contentTextView2);
        contentView3 = (TextView) view.findViewById(R.id.contentTextView3);
        contentView4 = (TextView) view.findViewById(R.id.contentTextView4);
        textViewList[0] = contentView1;
        textViewList[1] = contentView2;
        textViewList[2] = contentView3;
        textViewList[3] = contentView4;

        pb = (ProgressBar) view.findViewById(R.id.progress_indicator);

        b1 = (ImageButton) view.findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Speech2TextIntent.class);
                i.putExtra(Speech2TextIntent.LANGUAGE, Speech2TextIntent.LANGUAGE_ENGLISH);
                startActivityForResult(i, 1);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String[] results = data.getStringArrayExtra("resultList");
                for (int i=0; i<results.length; i++) {
                    textViewList[i].setText(results[i]);
                    if (i == 3) {
                        break;
                    }
                }
            }
        }
    }

}
