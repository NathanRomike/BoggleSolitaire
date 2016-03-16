package com.example.guest.bogglesolitaire;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameBoardActivity extends AppCompatActivity {
    public static final String TAG = GameBoardActivity.class.getSimpleName();
    @Bind(R.id.Cell0) TextView cell0;
    @Bind(R.id.Cell1) TextView cell1;
    @Bind(R.id.Cell2) TextView cell2;
    @Bind(R.id.Cell3) TextView cell3;
    @Bind(R.id.Cell4) TextView cell4;
    @Bind(R.id.Cell5) TextView cell5;
    @Bind(R.id.Cell6) TextView cell6;
    @Bind(R.id.Cell7) TextView cell7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        ButterKnife.bind(this);

        String[] alphabet = this.getResources().getStringArray(R.array.alphabet);
        String[] vowels = this.getResources().getStringArray(R.array.vowels);
        String[] boggleSequence = new String[8];


        Boolean isTwoVowels = false;
        do {
            boggleSequence = randomSequence(alphabet, 8);
            Log.d(TAG, "new sequence");
            Log.d(TAG, Boolean.toString(validateVowels(boggleSequence, vowels)));
        } while (!validateVowels(boggleSequence, vowels));

        cell0.setText(boggleSequence[0]);
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
