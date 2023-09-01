package com.class10th.mathematics.Ads;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

public class LoadInterstitialAds extends AppCompatActivity {

    @Override
    protected void onChildTitleChanged(Activity childActivity, CharSequence title) {
        super.onChildTitleChanged(childActivity, title);
        InterstitialAds.interAds(LoadInterstitialAds.this);
    }
}
