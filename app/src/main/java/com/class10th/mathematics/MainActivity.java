package com.class10th.mathematics;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.class10th.mathematics.Recycler.Adapter;
import com.class10th.mathematics.Recycler.Model;
import com.class10th.mathematics.databinding.ActivityMainBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    FirebaseFirestore firestore;
    ArrayList<Model> chapterList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.menu.setOnClickListener(v -> {
            BottomFragment bottomSheet = new BottomFragment();
            bottomSheet.show(MainActivity.this.getSupportFragmentManager(), bottomSheet.getTag());
        });


        firestore = FirebaseFirestore.getInstance();
        chapterList = new ArrayList<>();
        adapter = new Adapter(this, chapterList);
        binding.rcv.setAdapter(adapter);
        binding.rcv.setLayoutManager(new LinearLayoutManager(this));

        LoadData();

        binding.searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filterList(s.toString());
            }
        });


    }

    private void filterList(String text) {

        java.util.List<Model> filteredList = new ArrayList<>();
        for (Model item : chapterList) {

            if (item.getChapter().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        if (filteredList.isEmpty()) {
            binding.data.setVisibility(View.VISIBLE);
        } else {
            adapter.setFilteredList(filteredList);
            binding.data.setVisibility(View.GONE);
        }
    }


    private void LoadData() {
        chapterList.clear();
        firestore.collection("Chapter").orderBy("id", Query.Direction.ASCENDING)
                .addSnapshotListener((value, error) -> {


                    if (error != null) {
                        Toast.makeText(this, "Chapter Loaded", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    for (DocumentChange dc : Objects.requireNonNull(value).getDocumentChanges()) {
                        if (dc.getType() == DocumentChange.Type.ADDED) {
                            chapterList.add(dc.getDocument().toObject(Model.class));
                        }

                        adapter.notifyDataSetChanged();
                    }


                });


    }


}