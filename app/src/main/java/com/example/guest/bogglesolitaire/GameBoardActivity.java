package com.example.guest.bogglesolitaire;

import android.content.res.Resources;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;


public class GameBoardActivity extends AppCompatActivity {
    public static final String TAG = GameBoardActivity.class.getSimpleName();
    private ArrayList<String> resultList = new ArrayList<String>();
    private ArrayList<String> arrayForAdapter = new ArrayList<String>();
    private String[] boggleSequence = new String[8];
    @Bind(R.id.gridview) GridView gridview;
    @Bind(R.id.wordText) EditText wordText;
    @Bind(R.id.submitButton) Button submitButton;
    @Bind(R.id.resultListView) ListView resultListView;

    ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        ButterKnife.bind(this);

        String[] alphabet = this.getResources().getStringArray(R.array.alphabet);
        String[] vowels = this.getResources().getStringArray(R.array.vowels);



        Boolean isTwoVowels = false;
        do {
            boggleSequence = randomSequence(alphabet, 8);
            Log.d(TAG, "new sequence");
            Log.d(TAG, Boolean.toString(validateVowels(boggleSequence, vowels)));
        } while (!validateVowels(boggleSequence, vowels));

        arrayForAdapter = new ArrayList<String>(Arrays.asList(boggleSequence));

        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayForAdapter);
        gridview.setAdapter(mAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(GameBoardActivity.this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                if (!parent.getItemAtPosition(position).toString().equals("")){
                    wordText.append(parent.getItemAtPosition(position).toString());
                    String letter = parent.getItemAtPosition(position).toString();
                    mAdapter.remove(letter);
                    mAdapter.insert("", position);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
//
        wordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordText.setText("");
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultList.add(wordText.getText().toString());
                ArrayAdapter adapterList = new ArrayAdapter(GameBoardActivity.this, android.R.layout.simple_list_item_1, resultList);
                resultListView.setAdapter(adapterList);
                wordText.setText("");
                mAdapter.clear();
                mAdapter.addAll(boggleSequence);
//                mAdapter = new ArrayAdapter(GameBoardActivity.this, android.R.layout.simple_list_item_1, arrayForAdapter);
//                gridview.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private String[] randomSequence (String[] alphabet, int amount) {
        Random random = new Random();
        String[] sequence = new String[amount];
        for(int i = 0; i < 8; i++) {
            int number = random.nextInt(26);
            sequence[i] = alphabet[number];
            Log.d(TAG, String.valueOf(sequence[i]));
        }
        return sequence;
    }

    private Boolean validateVowels (String[] sequence, String[] vowels) {
        int vowelsAmount = 0;
        for (int i = 0; i < vowels.length; i++) {
            for (int j = 0; j < sequence.length; j++) {
                if (sequence[j].equals(vowels[i])) {
                    vowelsAmount ++;
                }
            }
        }
        if (vowelsAmount >= 2) {
            return true;
        } else {
            return false;
        }
    }
}
