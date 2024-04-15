package com.example.wakeupfit.DietAndExercises;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wakeupfit.R;

public class ExercisesWeightLossActivity extends AppCompatActivity {

    private VideoView videoView1, videoView2,videoView3,videoView4,videoView5,videoView6,videoView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exercises_weight_loss);

        videoView1 = findViewById(R.id.videoViewExercise);
        videoView2 = findViewById(R.id.videoViewExercise2);
        videoView3 = findViewById(R.id.videoViewExercise3);
        videoView4 = findViewById(R.id.videoViewExercise4);
        videoView5 = findViewById(R.id.videoViewExercise5);
        videoView6 = findViewById(R.id.videoViewExercise6);
        videoView7 = findViewById(R.id.videoViewExercise7);

        playVideo();

    }
    private void playVideo() {
        // Set the path of the video file
        String videoPath1 = "android.resource://" + getPackageName() + "/" + R.raw.legraise;
        String videoPath2 = "android.resource://" + getPackageName() + "/" + R.raw.plank;
        String videoPath3= "android.resource://" + getPackageName() + "/" + R.raw.pushup;
        String videoPath4 = "android.resource://" + getPackageName() + "/" + R.raw.jumpingrope;
        String videoPath5 = "android.resource://" + getPackageName() + "/" + R.raw.stairraise;
        String videoPath6 = "android.resource://" + getPackageName() + "/" + R.raw.lunges;
        String videoPath7 = "android.resource://" + getPackageName() + "/" + R.raw.burpees;
        Uri videoUri1 = Uri.parse(videoPath1);
        Uri videoUri2 = Uri.parse(videoPath2);
        Uri videoUri3 = Uri.parse(videoPath3);
        Uri videoUri4 = Uri.parse(videoPath4);
        Uri videoUri5 = Uri.parse(videoPath5);
        Uri videoUri6 = Uri.parse(videoPath6);
        Uri videoUri7 = Uri.parse(videoPath7);

        // Set video URI and start playing
        videoView1.setVideoURI(videoUri1);
        videoView1.start();
        videoView1.setOnCompletionListener(mp -> {
            // Restart the video when it completes
            videoView1.start();
        });
        videoView2.setVideoURI(videoUri2);
        videoView2.start();
        videoView2.setOnCompletionListener(mp -> {
            // Restart the video when it completes
            videoView2.start();
        });
        videoView3.setVideoURI(videoUri3);
        videoView3.start();
        videoView3.setOnCompletionListener(mp -> {
            // Restart the video when it completes
            videoView3.start();
        });
        videoView4.setVideoURI(videoUri4);
        videoView4.start();
        videoView4.setOnCompletionListener(mp -> {
            // Restart the video when it completes
            videoView4.start();
        });
        videoView5.setVideoURI(videoUri5);
        videoView5.start();
        videoView5.setOnCompletionListener(mp -> {
            // Restart the video when it completes
            videoView5.start();
        });
        videoView6.setVideoURI(videoUri6);
        videoView6.start();
        videoView6.setOnCompletionListener(mp -> {
            // Restart the video when it completes
            videoView6.start();
        });
        videoView7.setVideoURI(videoUri7);
        videoView7.start();
        videoView7.setOnCompletionListener(mp -> {
            // Restart the video when it completes
            videoView7.start();
        });
    }
}