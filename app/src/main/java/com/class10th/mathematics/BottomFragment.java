package com.class10th.mathematics;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.class10th.mathematics.databinding.FragmentBottomBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomFragment extends BottomSheetDialogFragment {

    FragmentBottomBinding binding;

    public BottomFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBottomBinding.inflate(getLayoutInflater());

        binding.privacy.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://current-english-translation.blogspot.com/p/current-english-translation-privacy.html"));
            startActivity(intent);
        });

        binding.share.setOnClickListener(v->{
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String shareMessage = "\nLet me recommend you this application: ";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.class10th.mathematics";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch (Exception e) {
                //e.toString();
            }
        });


        binding.star.setOnClickListener(v->{
            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + requireContext().getPackageName())));
            } catch (android.content.ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=com.class10th.mathematics")));
            }
        });


        binding.youtube.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.youtube.com/@DearNitishsir"));
            startActivity(intent);
        });


        binding.telegram.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://t.me/dearnitishsirofficial"));
            startActivity(intent);
        });

        binding.moreApps.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=Yadav+Creation"));
            startActivity(intent);
        });

        return binding.getRoot();
    }
}