package com.example.ibrahimtahirou.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0= yellow, 1 = red
    int activePlayer =0;

    boolean gameIsActive = true;

    //2 means unplayed
    int[] gameState={2,2,2,2,2,2,2,2,2};

    //winning positions : horizontal, vertical and diagonal
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},
                              {0,3,6},{1,4,7},{2,5,8},
                              {0,4,8},{2,4,6} };




    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter]==2 && gameIsActive){

            gameState[tappedCounter]= activePlayer;

            counter.setTranslationY(-1000f);


            if (activePlayer==0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer=1;
            }
            else{
                counter.setImageResource(R.drawable.red);
                activePlayer=0;
            }


            for (int[] wingPosition : winningPositions){

                if(gameState[wingPosition[0]]==gameState[wingPosition[1]] &&
                        gameState[wingPosition[1]]==gameState[wingPosition[2]] &&
                        gameState[wingPosition[0]]!=2){

                    //someone won
                    gameIsActive=false;

                    String winner  = "Red ";
                    if(gameState[wingPosition[0]]==0){
                        winner ="Yellow ";

                    }

                    TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);

                    winnerMessage.setText( winner + "has won!");

                    //after someone has won the game display the linear loyout wich asks user to play again
                    LinearLayout layout =(LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(view.VISIBLE);

                }
                else{

                    boolean gameIsOver =true;
                    for (int conuterState: gameState){
                        if (conuterState ==2) gameIsOver=false;
                    }

                    if (gameIsOver){
                        TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);

                        winnerMessage.setText(  " the game is draw!");

                        //after someone has won the game display the linear loyout wich asks user to play again
                        LinearLayout layout =(LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(view.VISIBLE);
                    }
                }
            }

            counter.animate().translationYBy(1000f).setDuration(300);

        }

    }



    public void playAgain(View view){

        gameIsActive = true;

        LinearLayout layout =(LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(view.INVISIBLE);


        for (int i=0; i<gameState.length; i++){
            gameState[i]=2;

        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gameBoardLayout);

        for( int i =0; i< gridLayout.getChildCount(); i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
