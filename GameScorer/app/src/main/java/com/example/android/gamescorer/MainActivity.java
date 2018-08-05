package com.example.android.gamescorer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.os.SystemClock;

import com.example.android.gamescorer.R;

public class MainActivity extends AppCompatActivity {

    //Chronometer counter;
    int scoreTeamA = 0;
    int scoreTeamB = 0;
    boolean timer_running = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startChronometer(counter);
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }




    public void stop(View view) {
if (timer_running = false) {
        ((Chronometer) findViewById(R.id.timer)).start();
timer_running = true;}


        else {((Chronometer) findViewById(R.id.timer)).stop();
            timer_running = false;}
    }




    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * This method adds 3 points to the total score for team A
     */
    public void add3A(View view) {

        scoreTeamA = scoreTeamA + 3;
        displayForTeamA(scoreTeamA);
    }

    /**
     * This method adds 2 points to the total score for team A
     */
    public void add2A(View view) {

        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
    }

    /**
     * This method adds 1 point to the total score for team A
     */
    public void add1A(View view) {

        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * This method adds 3 points to the total score for team B
     */
    public void add3B(View view) {

        scoreTeamB = scoreTeamB + 3;
        displayForTeamB(scoreTeamB);
    }

    /**
     * This method adds 2 points to the total score for team B
     */
    public void add2B(View view) {

        scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);
    }

    /**
     * This method adds 1 point to the total score for team B
     */
    public void add1B(View view) {

        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
    }
    /**
     * This method resents the scores for both teams
     */
    public void resetGame(View view) {

        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

}
