package com.example.a456;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.a456.databinding.ActivityDiaryBinding;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class DiaryActivity extends AppCompatActivity {
    private ActivityDiaryBinding binding;
    public  String saveFileName = null;
    public String contents = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDiaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.calendarView.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                binding.textDiary.setVisibility(View.VISIBLE);
                setting2();
                binding.textDiary.setText(String.format("%d / %d / %d", year, month+1, dayOfMonth));
                binding.editContext.setText("");
                load(year, month, dayOfMonth);
            }
        });

        binding.buttonSave.setOnClickListener(v -> {
            contents = binding.editContext.getText().toString();
            writeFile(saveFileName, contents);
            binding.textContext.setText(contents);
            setting1();
        });

    }

    private void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        binding.textDiary.setVisibility(View.VISIBLE);
        setting2();
        binding.textDiary.setText(String.format("%d / %d / %d", year, month+1, dayOfMonth));
        binding.editContext.setText("");
        load(year, month, dayOfMonth);
    }

    private void writeFile(String filename, String data) {
        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(data.getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load(int cYear, int cMonth, int cDay) {
        saveFileName = ""+cYear+"-"+(cMonth+1)+""+"-"+cDay+".txt";
        try {
            String loadedContents = readFile(saveFileName);
            binding.textContext.setText(loadedContents);
            setting1();

            binding.buttonChange.setOnClickListener(v -> {
                setting2();
                binding.editContext.setText(loadedContents);
                binding.textContext.setText(binding.editContext.getText());
            });

            binding.buttonDelete.setOnClickListener(v -> {
                setting2();
                binding.editContext.setText("");
                removeDiary(saveFileName);
            });

            if(binding.textContext.getText()==null) {
                binding.textDiary.setVisibility(View.VISIBLE);
                setting2();
            }

        } catch (FileNotFoundException e) {

        }
    }

    private String readFile(String filename) throws FileNotFoundException {
        FileInputStream fis =  openFileInput(filename);

        InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {

        }
        return stringBuilder.toString().trim();
    }

    private void removeDiary(String readDay) {
        FileOutputStream fos = null;
        try {
            fos=openFileOutput(readDay, MODE_PRIVATE);
            String content = "";
            fos.write((content).getBytes());
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setting1() {
        binding.buttonSave.setVisibility(View.INVISIBLE);
        binding.buttonChange.setVisibility(View.VISIBLE);
        binding.buttonDelete.setVisibility(View.VISIBLE);
        binding.editContext.setVisibility(View.INVISIBLE);
        binding.textContext.setVisibility(View.VISIBLE);
    }

    private void setting2() {
        binding.buttonSave.setVisibility(View.VISIBLE);
        binding.buttonChange.setVisibility(View.INVISIBLE);
        binding.buttonDelete.setVisibility(View.INVISIBLE);
        binding.editContext.setVisibility(View.VISIBLE);
        binding.textContext.setVisibility(View.INVISIBLE);

    }
}