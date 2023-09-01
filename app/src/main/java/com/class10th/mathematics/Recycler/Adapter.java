package com.class10th.mathematics.Recycler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.class10th.mathematics.Ads.InterstitialAds;
import com.class10th.mathematics.DetailsActivity;
import com.class10th.mathematics.R;
import com.class10th.mathematics.SplashActivity;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Model> chapterList;

    public Adapter(Context context, ArrayList<Model> chapterList) {
        this.context = context;
        this.chapterList = chapterList;
    }


    public void setFilteredList(java.util.List<Model> filteredList) {

        this.chapterList = (ArrayList<Model>) filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Model model = chapterList.get(position);
        holder.chapter.setText(model.getChapter());
        holder.itemView.setOnClickListener(v->{

            InterstitialAds.interAds(context);

            if (InterstitialAds.mInterstitialAd != null){
                InterstitialAds.mInterstitialAd.show((Activity) context);

                InterstitialAds.mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                        InterstitialAds.mInterstitialAd = null;
                        InterstitialAds.interAds(context);
                        if(model.getPdfLink()==null){
                            Toast.makeText(context, "Chapter Not Available", Toast.LENGTH_SHORT).show();
                        }else {
                            Intent intent = new Intent(context, DetailsActivity.class);
                            intent.putExtra("img",model.getImg());
                            intent.putExtra("pdfLink",model.getPdfLink());
                            intent.putExtra("videoLink",model.getVideoLink());
                            context.startActivity(intent);
                        }

                    }

                });
            }else {
                if(model.getPdfLink()==null){
                    Toast.makeText(context, "Chapter Not Available", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("img",model.getImg());
                    intent.putExtra("pdfLink",model.getPdfLink());
                    intent.putExtra("videoLink",model.getVideoLink());
                    context.startActivity(intent);
                }
            }



        });


    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView chapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            chapter = itemView.findViewById(R.id.chapter);
        }
    }
}
