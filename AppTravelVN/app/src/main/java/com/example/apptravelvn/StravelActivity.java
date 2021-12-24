package com.example.apptravelvn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.apptravelvn.databinding.ActivityStravelBinding;

public class StravelActivity extends AppCompatActivity {
    private static int SPLASH_TIMER = 3000;
    ActivityStravelBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityStravelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Animation sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        Animation sideAnimRight = AnimationUtils.loadAnimation(this,R.anim.side_anim_right);
        Animation bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        binding.imgLogo.setAnimation(sideAnim);
        binding.txtdulichvietnam.setAnimation(sideAnimRight);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish(); //destroy activity khi back sẽ ko về splash
            }
        },SPLASH_TIMER);
    }
}