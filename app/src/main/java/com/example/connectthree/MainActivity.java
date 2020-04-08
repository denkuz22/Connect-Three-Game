package com.example.connectthree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0: yellow, 1: red, 2: empty
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningCombination = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameOn = true;

    public void ImageDropIn (View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gameOn){
            counter.setTranslationY(-1500);
            gameState[tappedCounter] = activePlayer;
            if(activePlayer == 0){
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }else{
                counter.setImageResource(R.drawable.red);
                activePlayer =  0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(350);

            for(int[] winningPos: winningCombination ){

                if(gameState[winningPos[0]]==gameState[winningPos[1]] && gameState[winningPos[1]] == gameState[winningPos[2]] && gameState[winningPos[0]]!=2){
                    gameOn = false;
                    String winner = "";
                    //Somebody won the game.
                    if(activePlayer == 1){
                        winner = "Yellow";

                    }else{
                        winner = "Red";

                    }

                    TextView winningText = (TextView) findViewById(R.id.winnerTextView);
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    winningText.setText(winner + " has won the game!");
                    winningText.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility((View.VISIBLE));
                }
            }
        }


    }

    public void playAgain(View view){
        TextView winningText = (TextView) findViewById(R.id.winnerTextView);
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        winningText.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility((View.INVISIBLE));

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i=0;i<gameState.length;i++){
            gameState[i] = 2;
        }
        activePlayer = 0;

        gameOn = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
