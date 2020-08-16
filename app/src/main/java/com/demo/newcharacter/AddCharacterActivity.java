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
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddCharacterActivity extends AppCompatActivity {

    private EditText editTextName;                                                                     //объявляем итемы с активности
    private Spinner spinnerRace;
    private Spinner spinnerClass;
    private TextView textViewFreeValueDesc;
    private TextView textViewFreeVlaue;
    private RadioButton radioButtonCustom;
    private RadioButton radioButtonSimple;
    private RadioButton radioButtonPointBuy;
    private RadioButton radioButtonRoll;
    private ImageButton imageButtonStrUp;
    private ImageButton imageButtonStrDown;


    private MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_character);
        editTextName = findViewById(R.id.editTextName);
        spinnerClass = findViewById(R.id.spinnerClass);                                             //находим итемы
        spinnerRace = findViewById(R.id.spinnerRace);
        imageButtonStrUp = findViewById(R.id.imageButtonStrUp);
        imageButtonStrDown = findViewById(R.id.imageButtonStrDown);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        textViewFreeValueDesc = findViewById(R.id.textViewFreeValueDesc);
        textViewFreeVlaue = findViewById(R.id.textViewFreeVlaue);
        radioButtonCustom = findViewById(R.id.radioButtonCustom);
        radioButtonSimple = findViewById(R.id.radioButtonSimple);
        radioButtonPointBuy = findViewById(R.id.radioButtonPointBuy);
        radioButtonRoll = findViewById(R.id.radioButtonRoll);
        radioButtonCustom.setOnClickListener(radioButtonClickListener);
        radioButtonSimple.setOnClickListener(radioButtonClickListener);
        radioButtonPointBuy.setOnClickListener(radioButtonClickListener);
        radioButtonRoll.setOnClickListener(radioButtonClickListener);
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
            Character character = new Character(nameCharacter, imgCharacter, classCharacter, raceCharacter, lvlCharacter, minExp, maxExp);
            viewModel.insertCharacter(character);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isFilled(String name) {                                  //проверяем не пустое ли имя
        return !name.isEmpty();
    }


    public void hideFreeValue() {           //скрываем свободные очки
        textViewFreeValueDesc.setVisibility(View.GONE);
        textViewFreeVlaue.setVisibility(View.GONE);
    }

    public void showFreeValue() {           //показываем совбодные очки
        textViewFreeValueDesc.setVisibility(View.VISIBLE);
        textViewFreeVlaue.setVisibility(View.VISIBLE);
    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {  //слушаем выбор кнопок

        @Override
        public void onClick(View view) {
            RadioButton radioButtonchecked = (RadioButton) view;
            switch (radioButtonchecked.getId()) {
                case R.id.radioButtonCustom:
                    hideFreeValue();
                    break;
                case R.id.radioButtonPointBuy:
                    showFreeValue();
                    break;
                case  R.id.radioButtonSimple:
                    hideFreeValue();
                    break;
                case R.id.radioButtonRoll:
                    hideFreeValue();
                    break;
                default: break;
            }

        }
    };

    public void changesButtonUpDown()  {
       // imageButtonStrUp.setImageDrawable();
    }
}