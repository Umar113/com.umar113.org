package com.example.android.health.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.health.R;

/**
 * Created by Android on 20-02-2017.
 */
public class SplashActivity extends Activity {

    ImageView logoImageview;
    TextView headTextView, colgnameTextiew;

    Animation anim_alpha, anim_up, anim_down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logoImageview = (ImageView) findViewById(R.id.logoImageview);
        headTextView = (TextView) findViewById(R.id.headTextView);
        colgnameTextiew = (TextView) findViewById(R.id.colgnameTextiew);

        anim_alpha = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.alpha_anim);
        anim_down = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.down_anim);
        anim_up = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.up_anim);

        headTextView.setVisibility(View.VISIBLE);
        headTextView.startAnimation(anim_down);
        anim_down.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                colgnameTextiew.setVisibility(View.VISIBLE);
                colgnameTextiew.startAnimation(anim_up);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



        anim_up.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logoImageview.setVisibility(View.VISIBLE);
                logoImageview.startAnimation(anim_alpha);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        Thread timer=new Thread(){

            public void run(){
                try{

                    sleep(3500);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    Intent i=new Intent(SplashActivity.this,DoctorListActivity.class);
                    startActivity(i);
                }
            }

        };
        timer.start();

    }
}