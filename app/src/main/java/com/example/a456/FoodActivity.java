package com.example.a456;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a456.databinding.ActivityFood1Binding;


public class FoodActivity extends AppCompatActivity {
    private ActivityFood1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFood1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonBack.setOnClickListener(v -> finish());
        binding.button1.setOnClickListener(v-> startWeb1());
        binding.button2.setOnClickListener(v-> startWeb2());
        binding.button3.setOnClickListener(v-> startWeb3());
        binding.button4.setOnClickListener(v-> startWeb4());
        binding.button5.setOnClickListener(v-> startWeb5());
    }

    public void startWeb1() {
        String url = "https://in.naver.com/sy0329/contents/321456850639264?query=%EC%A0%80%ED%83%84%EA%B3%A0%EC%A7%80+%EC%8B%9D%EB%8B%A8%ED%91%9C";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void startWeb2() {
        String url = "https://blog.naver.com/by_analog/222260057990";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void startWeb3() {
        String url = "https://terms.naver.com/entry.naver?docId=927807&cid=51007&categoryId=51007";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void startWeb4() {
        String url = "https://terms.naver.com/entry.naver?docId=74335&cid=43667&categoryId=43667";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void startWeb5() {
        String url = "https://terms.naver.com/entry.naver?docId=2843277&cid=63166&categoryId=55605#TABLE_OF_CONTENT4";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}