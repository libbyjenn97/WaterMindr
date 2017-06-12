package com.example.libbyalicia.watermindr;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

//Class for playing a youtube playlist in a youtube player view
public class GardeningVideosActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String API_KEY = "AIzaSyCrssPDFpJRI5EP-Q6_qB1q-yY7yh0nXjg"; //set your own api key
    public static final String PLAYLIST_ID = "PLF36F2ABD7AD79803"; //set playlist ID of youtube video

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gardening_videos);

        //set youtube player view
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this); //initialise youtube player view
    }

    //If youtube player can't load video, show error message
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result){

        Toast.makeText(this, "Failed to initialise!", Toast.LENGTH_LONG).show();
    }

    //Play video
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored){

        if(!wasRestored) {

            player.cuePlaylist(PLAYLIST_ID);


        }
    }
}
/*
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GardeningVideosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gardening_videos);
    }
}
*/
