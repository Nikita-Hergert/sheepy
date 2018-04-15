package org.sheepy.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.sheepy.Game;
import org.sheepy.R;
import org.sheepy.utils.AppHandling;
import org.sheepy.utils.Scope;

/**
 * Created by Vitali Dettling on 30.01.2016.
 */
public class Menu extends Activity {
    private static final int REQUEST_CODE_GAME = 1;
    private static final String ASSETS_FONT_ALGERIAN = "Fonts/ALGER.TTF";

    private Intent intentGameRuns;
    private TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);
        Typeface tf = Typeface.createFromAsset(getAssets(), ASSETS_FONT_ALGERIAN);
        scoreText = (TextView) findViewById(R.id.ScoreText);
        scoreText.setTypeface(tf);
    }

    public void frontImage(View view) {
        intentGameRuns = new Intent(this, Game.class);
        startActivityForResult(intentGameRuns, REQUEST_CODE_GAME);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GAME) {
            if (resultCode == RESULT_OK) {
                scoreText.setText(scoreText.getText() + data.getStringExtra(Scope.KEY_STRING_SCORE));
            }
        }
    }

    /**
     * This method is called two times, once even before the game was instantiate and the second time when the user press the home bottom.
     * This is important because otherwise the game would run in the back of the mobile device without the knowledge of the user.
     * It basically just terminate the program with the code 0;
     */
    protected void onPause() {
        super.onPause();
        //The game has not started yet.
        if (intentGameRuns == null) {
            this.finish();
            AppHandling.destroy();
        }
    }
}
