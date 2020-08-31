package com.demo.newcharacter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CharacterView extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewActualLvl;
    private TextView textViewClassCharacter;
    private TextView textViewRaceCharacter;
    private TextView textViewExpMin;
    private TextView textViewExpMax;
    private TextView textViewAddExp;
    private EditText editTextExp_Add;
    private FloatingActionButton buttonOnClickAddLvl;
    private FloatingActionButton buttonOnClickAddExp;
    private int lvl;
    private int maxExp;
    private int minExp;
    public Character character;
    private MainViewModelCharacter viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_view);
        textViewName = findViewById(R.id.textViewNameCharacterView);
        textViewActualLvl = findViewById(R.id.textViewActualLvlCharacterView);
        textViewClassCharacter = findViewById(R.id.textViewClassCharacterView);
        textViewRaceCharacter = findViewById(R.id.textViewRaceCharacterView);
        textViewExpMax = findViewById(R.id.textViewExpMax);
        textViewExpMin = findViewById(R.id.textViewExpMin);
        textViewAddExp = findViewById(R.id.textViewAddExp);
        editTextExp_Add = findViewById(R.id.editTextExp_Add);
        buttonOnClickAddLvl = findViewById(R.id.floatingActionButtonLvl_add);
        buttonOnClickAddExp = findViewById(R.id.floatingActionButtonExp_plus);
        viewModel = ViewModelProviders.of(this).get(MainViewModelCharacter.class);
        Intent intent = getIntent();
        character = (Character) intent.getSerializableExtra("character");         //получаем персонажа
        SetCharacter(character);                                                                     //устанавливаем поля
        waitExp();
    }

    public void waitExp() {
        if ((minExp == maxExp) | (minExp > maxExp)) {
            buttonOnClickAddLvl.show();
            buttonOnClickAddExp.hide();
            editTextExp_Add.setVisibility(View.GONE);
            textViewAddExp.setVisibility(View.GONE);
        } else {
            buttonOnClickAddLvl.hide();
            buttonOnClickAddExp.show();
            editTextExp_Add.setVisibility(View.VISIBLE);
            textViewAddExp.setVisibility(View.VISIBLE);
        }
    }

    public void onClickAddLvl(View view) {

    }

    public void onClickAddExp(View view) {
        Integer addExp = Integer.parseInt(String.valueOf(editTextExp_Add.getText()));
        minExp += addExp;
        textViewExpMin.setText(String.format("%s", minExp));
        Toast.makeText(this, "minEXP" +minExp, Toast.LENGTH_SHORT).show();
        waitExp();
    }

    public void SetCharacter(Character character) {
        lvl = character.getLvlCharacter();
        minExp = character.getMinExp();
        maxExp = character.getMaxExp();
        textViewName.setText(character.getNameCharacter());
        textViewActualLvl.setText(String.format("%s", lvl));
        textViewClassCharacter.setText(character.getClassCharacter());
        textViewRaceCharacter.setText(character.getRaceCharacter());
        textViewExpMin.setText(String.format("%s", minExp));
        textViewExpMax.setText(String.format("%s", maxExp));

    }

    public void onClickCloseCharacterView(View view) {
        character.setMinExp(minExp);
        viewModel.updateCharacter(character);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}