package com.class10th.mathematics;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.class10th.mathematics.Recycler.Model;
import com.class10th.mathematics.databinding.ActivityDetailsBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String thambnail = getIntent().getStringExtra("img");
        String link = getIntent().getStringExtra("pdfLink");
        String videoLink = getIntent().getStringExtra("videoLink");

        binding.play.setOnClickListener(v -> {
            if (videoLink != null) {
                Uri uri = Uri.parse(videoLink);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Video not found", Toast.LENGTH_SHORT).show();
            }
        });

        Glide.with(this).load(thambnail).into(binding.thambnail);

        ShowPdf(link);
        FirebaseDatabase.getInstance().getReference().child("AdmobAds").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Model model = snapshot.getValue(Model.class);

                if (model.isBanner()) {
                    showBannerAds();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void showBannerAds() {
        MobileAds.initialize(this, initializationStatus -> {
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);


    }

    private void ShowPdf(String link) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(link).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                binding.progress.setVisibility(View.GONE);
                Toast.makeText(DetailsActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.pdfView.fromStream(inputStream)
                                .onLoad(nbPages -> {
                                    // Hide the progress bar when the PDF file is loaded
                                    binding.progress.setVisibility(View.GONE);
                                }).load();
                    }
                });
            }
        });

    }
}