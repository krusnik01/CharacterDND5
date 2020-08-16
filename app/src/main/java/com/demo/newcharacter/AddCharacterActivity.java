package com.demo.newcharacter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddCharacterActivity extends AppCompatActivity {

    private EditText editTextName;                                                                     //объявляем итемы с активности
    private Spinner spinnerRace;
    private Spinner spinnerClass;


    private MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_character);
        editTextName = findViewById(R.id.editTextName);
        spinnerClass = findViewById(R.id.spinnerClass);                                             //находим итемы
        spinnerRace = findViewById(R.id.spinnerRace);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    public void onClickCreateCharacter(View view) {
        String nameCharacter = editTextName.getText().toString().trim();                                     //получаем значения, trim удаляет лишние пробелы
        String raceCharacter = spinnerRace.getSelectedItem().toString();
        String classCharacter = spinnerClass.getSelectedItem().toString();
        String imgCharacter = "путь до картинки";
        int lvlCharacter = 1;
        int minExp = 0;
        int maxExp = 300;
        if (isFilled(nameCharacter)) {
            Character character = new Character(nameCharacter,imgCharacter,classCharacter,raceCharacter,lvlCharacter,minExp, maxExp);
            viewModel.insertCharacter(character);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isFilled(String name){                                  //проверяем не пустое ли имя
        return !name.isEmpty();
    }
}