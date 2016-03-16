package com.example.guest.bogglesolitaire;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Random;

public class GameBoardActivity extends AppCompatActivity {
    public static final String TAG = GameBoardActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);

        String[] alphabet = this.getResources().getStringArray(R.array.alphabet);
        String[] vowels = this.getResources().getStringArray(R.array.vowels);
        String[] boggleSequence = new String[8];


        Boolean isTwoVowels = false;
        do {
            boggleSequence = randomSequence(alphabet, 8);
            Log.d(TAG, "new sequence");
            Log.d(TAG, Boolean.toString(validateVowels(boggleSequence, vowels)));
        } while (!validateVowels(boggleSequence, vowels));

//        Toast.makeText(GameBoardActivity.this, alphabet[number], Toast.LENGTH_LONG).show();
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
