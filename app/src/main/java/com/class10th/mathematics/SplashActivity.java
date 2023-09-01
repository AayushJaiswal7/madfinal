package com.class10th.mathematics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.class10th.mathematics.Ads.InterstitialAds;
import com.class10th.mathematics.Ads.LoadInterstitialAds;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends LoadInterstitialAds {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#3F51B5"));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this,
                    MainActivity.class);
            startActivity(i);
            finish();

        }, 8000);
    }
}