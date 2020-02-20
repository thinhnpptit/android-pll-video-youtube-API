package com.example.playlistyoutubeapidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {


    YouTubePlayerView youTubePlayerView;
    String idVid="";
    int REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        youTubePlayerView = findViewById(R.id.youtubeolayerview);

        Intent intent = getIntent();
        idVid = intent.getStringExtra("idVideo");

        youTubePlayerView.initialize(MainActivity.KEY_API, PlayVideoActivity.this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(idVid);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(PlayVideoActivity.this, REQUEST_CODE);
        }else {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE){
            youTubePlayerView.initialize(MainActivity.KEY_API, PlayVideoActivity.this);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
