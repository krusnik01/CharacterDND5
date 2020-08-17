package com.demo.newcharacter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
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
    private Spinner spinnerIdeology;
    private TextView textViewFreeValueDesc;
    private TextView textViewFreeValue;
    private TextView textViewStrengthValue;
    private TextView textViewDexterityValue;
    private TextView textViewConstitutionValue;
    private TextView textViewIntelligenceValue;
    private TextView textViewWisdomValue;
    private TextView textViewCharismaValue;
    private TextView textViewRacieBonus;

    private RadioButton radioButtonCustom;
    private RadioButton radioButtonSimple;
    private RadioButton radioButtonPointBuy;
    private RadioButton radioButtonRoll;

    private ImageButton imageButtonStrUp;
    private ImageButton imageButtonStrDown;
    private ImageButton imageButtonDexUp;
    private ImageButton imageButtonDexDown;
    private ImageButton imageButtonConUp;
    private ImageButton imageButtonConDown;
    private ImageButton imageButtonIntUp;
    private ImageButton imageButtonIntDown;
    private ImageButton imageButtonWisUp;
    private ImageButton imageButtonWisDown;
    private ImageButton imageButtonCharUp;
    private ImageButton imageButtonCharDown;

    private Button buttonRoll;

    private int idButton;
    private int idImageButton;
    private int rollSize = 4;
    private int minRandom =1;
    private int maxRandom =6;

    private int strValue;
    private int dexValue;
    private int conValue;
    private int intValue;
    private int wisValue;
    private int charValue;

    private int freeValue;
    private int minValue = 5;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_character);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        searchItem();
        getAttributes();
        changesButtonPlus();
        setDefaultPointBuy();
        idButton =R.id.radioButtonCustom;
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

    OnItemSelectedListener raceCharacterSelected = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            String item = (String)parent.getItemAtPosition(position);
            textViewRacieBonus.setText(item);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {        }
    };

    private boolean isFilled(String name) {                                  //проверяем не пустое ли имя
        return !name.isEmpty();
    }

    public void hideFreeValue() {           //скрываем свободные очки
        textViewFreeValueDesc.setVisibility(View.GONE);
        textViewFreeValue.setVisibility(View.GONE);
    }

    public void showFreeValue() {           //показываем совбодные очки
        textViewFreeValueDesc.setVisibility(View.VISIBLE);
        textViewFreeValue.setVisibility(View.VISIBLE);
    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {  //слушаем выбор кнопок
        @Override
        public void onClick(View view) {
            RadioButton radioButtonchecked = (RadioButton) view;
            idButton = radioButtonchecked.getId();
            if (idButton == R.id.radioButtonPointBuy) {
                showFreeValue();
            } else {
                hideFreeValue();
            }
            if ((idButton == R.id.radioButtonCustom) || (idButton == R.id.radioButtonPointBuy)) {
                setDefaultPointBuy();
                changesButtonPlus();
            } else {
                changesButtonUp();
            }
            if (idButton == R.id.radioButtonSimple) {
                setDefaultSimple();
            }
            if (idButton == R.id.radioButtonRoll) {
                setDefaultRandom();
                buttonRoll.setVisibility(View.VISIBLE);
            } else {
                buttonRoll.setVisibility(View.GONE);
            }
        }
    };

    public void changesButtonPlus() {
        imageButtonStrUp.setImageResource(R.drawable.icon_plus);
        imageButtonStrDown.setImageResource(R.drawable.icon_minus);
        imageButtonDexUp.setImageResource(R.drawable.icon_plus);
        imageButtonDexDown.setImageResource(R.drawable.icon_minus);
        imageButtonConUp.setImageResource(R.drawable.icon_plus);
        imageButtonConDown.setImageResource(R.drawable.icon_minus);
        imageButtonIntUp.setImageResource(R.drawable.icon_plus);
        imageButtonIntDown.setImageResource(R.drawable.icon_minus);
        imageButtonWisUp.setImageResource(R.drawable.icon_plus);
        imageButtonWisDown.setImageResource(R.drawable.icon_minus);
        imageButtonCharUp.setImageResource(R.drawable.icon_plus);
        imageButtonCharDown.setImageResource(R.drawable.icon_minus);
    }

    public void changesButtonUp() {
        imageButtonStrUp.setImageResource(R.drawable.icon_up);
        imageButtonStrDown.setImageResource(R.drawable.icon_down);
        imageButtonDexUp.setImageResource(R.drawable.icon_up);
        imageButtonDexDown.setImageResource(R.drawable.icon_down);
        imageButtonConUp.setImageResource(R.drawable.icon_up);
        imageButtonConDown.setImageResource(R.drawable.icon_down);
        imageButtonIntUp.setImageResource(R.drawable.icon_up);
        imageButtonIntDown.setImageResource(R.drawable.icon_down);
        imageButtonWisUp.setImageResource(R.drawable.icon_up);
        imageButtonWisDown.setImageResource(R.drawable.icon_down);
        imageButtonCharUp.setImageResource(R.drawable.icon_up);
        imageButtonCharDown.setImageResource(R.drawable.icon_down);
    }

    public void searchItem() {
        editTextName = findViewById(R.id.editTextName);
        spinnerClass = findViewById(R.id.spinnerClass);                                             //находим итемы
        spinnerRace = findViewById(R.id.spinnerRace);
        spinnerIdeology = findViewById(R.id.spinnerIdeology);

        imageButtonStrUp = findViewById(R.id.imageButtonStrUp);
        imageButtonStrDown = findViewById(R.id.imageButtonStrDown);
        imageButtonDexUp = findViewById(R.id.imageButtonDexUp);
        imageButtonDexDown = findViewById(R.id.imageButtonDexDown);
        imageButtonConUp = findViewById(R.id.imageButtonConUp);
        imageButtonConDown = findViewById(R.id.imageButtonConDown);
        imageButtonIntUp = findViewById(R.id.imageButtonIntUp);
        imageButtonIntDown = findViewById(R.id.imageButtonIntDown);
        imageButtonWisUp = findViewById(R.id.imageButtonWisUp);
        imageButtonWisDown = findViewById(R.id.imageButtonWisDown);
        imageButtonCharUp = findViewById(R.id.imageButtonCharUp);
        imageButtonCharDown = findViewById(R.id.imageButtonCharDown);

        textViewFreeValueDesc = findViewById(R.id.textViewFreeValueDesc);
        textViewFreeValue = findViewById(R.id.textViewFreeVlaue);
        radioButtonCustom = findViewById(R.id.radioButtonCustom);
        radioButtonSimple = findViewById(R.id.radioButtonSimple);
        radioButtonPointBuy = findViewById(R.id.radioButtonPointBuy);
        radioButtonRoll = findViewById(R.id.radioButtonRoll);

        textViewStrengthValue = findViewById(R.id.textViewStrengthValue);
        textViewDexterityValue = findViewById(R.id.textViewDexterityValue);
        textViewConstitutionValue = findViewById(R.id.textViewConstitutionValue);
        textViewIntelligenceValue = findViewById(R.id.textViewIntelligenceValue);
        textViewWisdomValue = findViewById(R.id.textViewWisdomValue);
        textViewCharismaValue = findViewById(R.id.textViewCharismaValue);
        textViewRacieBonus = findViewById(R.id.textViewRacieBonus);
        spinnerRace.setOnItemSelectedListener(raceCharacterSelected);

        radioButtonCustom.setOnClickListener(radioButtonClickListener);
        radioButtonSimple.setOnClickListener(radioButtonClickListener);
        radioButtonPointBuy.setOnClickListener(radioButtonClickListener);
        radioButtonRoll.setOnClickListener(radioButtonClickListener);

        imageButtonStrUp.setOnClickListener(setAttributesClickListner);
        imageButtonStrDown.setOnClickListener(setAttributesClickListner);
        imageButtonDexUp.setOnClickListener(setAttributesClickListner);
        imageButtonDexDown.setOnClickListener(setAttributesClickListner);
        imageButtonConUp.setOnClickListener(setAttributesClickListner);
        imageButtonConDown.setOnClickListener(setAttributesClickListner);
        imageButtonIntUp.setOnClickListener(setAttributesClickListner);
        imageButtonIntDown.setOnClickListener(setAttributesClickListner);
        imageButtonWisUp.setOnClickListener(setAttributesClickListner);
        imageButtonWisDown.setOnClickListener(setAttributesClickListner);
        imageButtonCharUp.setOnClickListener(setAttributesClickListner);
        imageButtonCharDown.setOnClickListener(setAttributesClickListner);

        buttonRoll = findViewById(R.id.buttonRoll);
    }

    public void getAttributes() {
        strValue = Integer.parseInt(textViewStrengthValue.getText().toString());
        dexValue = Integer.parseInt(textViewDexterityValue.getText().toString());
        conValue = Integer.parseInt(textViewConstitutionValue.getText().toString());
        intValue = Integer.parseInt(textViewIntelligenceValue.getText().toString());
        wisValue = Integer.parseInt(textViewWisdomValue.getText().toString());
        charValue = Integer.parseInt(textViewCharismaValue.getText().toString());
        freeValue = Integer.parseInt(textViewFreeValue.getText().toString());
    }

    public void setAttributes() {
        textViewFreeValue.setText(String.format("%s", freeValue));
        textViewStrengthValue.setText(String.format("%s", strValue));
        textViewDexterityValue.setText(String.format("%s", dexValue));
        textViewConstitutionValue.setText(String.format("%s", conValue));
        textViewIntelligenceValue.setText(String.format("%s", intValue));
        textViewWisdomValue.setText(String.format("%s", wisValue));
        textViewCharismaValue.setText(String.format("%s", charValue));
    }

    View.OnClickListener setAttributesClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ImageButton imageButtonClick = (ImageButton) view;
            idImageButton = imageButtonClick.getId();
            if ((idButton == R.id.radioButtonPointBuy) && (freeValue > 0)) {
                setValueAttributes(idImageButton);
            }
            if (idButton == R.id.radioButtonCustom) {
                setValueAttributes(idImageButton);
            }
            if (idButton == R.id.radioButtonSimple) {
                changeAttributes(imageButtonClick,idImageButton);
            }
            if (idButton == R.id.radioButtonRoll) {
                changeAttributes(imageButtonClick,idImageButton);
            }

        }
    };

    private void setValueAttributes(int idImageButton) {
        switch (idImageButton) {
            case R.id.imageButtonStrUp:
                strValue = addAttributes(strValue);
                break;
            case R.id.imageButtonStrDown:
                strValue = removeAttributes(strValue);
                break;

            case R.id.imageButtonDexUp:
                dexValue = addAttributes(dexValue);
                break;
            case R.id.imageButtonDexDown:
                dexValue = removeAttributes(dexValue);
                break;

            case R.id.imageButtonConUp:
                conValue = addAttributes(conValue);
                break;
            case R.id.imageButtonConDown:
                conValue = removeAttributes(conValue);
                break;

            case R.id.imageButtonIntUp:
                intValue = addAttributes(intValue);
                break;
            case R.id.imageButtonIntDown:
                intValue = removeAttributes(intValue);
                break;

            case R.id.imageButtonWisUp:
                wisValue = addAttributes(wisValue);
                break;
            case R.id.imageButtonWisDown:
                wisValue = removeAttributes(wisValue);
                break;

            case R.id.imageButtonCharUp:
                charValue = addAttributes(charValue);
                break;
            case R.id.imageButtonCharDown:
                charValue = removeAttributes(charValue);
                break;
        }

        setAttributes();
    }

    public int addAttributes(int valueAttributes) {
        if (freeValue > 0) {
            valueAttributes += 1;
            freeValue -= 1;
        }
        return valueAttributes;
    }

    public int removeAttributes(int valueAttributes) {
        if (valueAttributes > minValue) {
            valueAttributes -= 1;
            freeValue += 1;
        }
        return valueAttributes;
    }

    public void setDefaultPointBuy() {
        strValue = 8;
        dexValue = 8;
        conValue = 8;
        intValue = 8;
        wisValue = 8;
        charValue = 8;
        freeValue = 28;
        setAttributes();
    }

    public void setDefaultRandom() {                                        //генерация ролл 4d6
        strValue = setGenerateRandom();
        dexValue = setGenerateRandom();
        conValue = setGenerateRandom();
        intValue = setGenerateRandom();
        wisValue = setGenerateRandom();
        charValue = setGenerateRandom();
        setAttributes();
    }
    public int setGenerateRandom(){
        int[] roll = new int[rollSize];
        int minRoll = 10;
        int minRollIndex = 0;
        int summRoll = 0;
        for (int i = 0; i < roll.length; i++) {
            roll[i] = generateRandom();
            if (minRoll > roll[i]) {
                minRoll = roll[i];
                minRollIndex = i;
            }
        }
        roll[minRollIndex] = 0;
        for (int i = 0; i < roll.length; i++) {
            summRoll = summRoll + roll[i];
        }
        return summRoll;
            }

    public int generateRandom() {
        int getRandom;
        getRandom= minRandom+(int)(Math.random()*maxRandom);
        return getRandom;
    }

    public void changeAttributes(ImageButton imageButtonClick, int idImageButton) {                // логика сортировки
                switch (idImageButton) {
            case R.id.imageButtonStrUp:break;

            case R.id.imageButtonStrDown:
                moveValueDown(textViewStrengthValue, textViewDexterityValue);
                break;

            case R.id.imageButtonDexUp:
                moveValueDown(textViewDexterityValue,textViewStrengthValue);
                break;

            case R.id.imageButtonDexDown:
                moveValueDown(textViewDexterityValue,textViewConstitutionValue);
                break;

            case R.id.imageButtonConUp:
                moveValueDown(textViewConstitutionValue,textViewDexterityValue);
                break;
            case R.id.imageButtonConDown:
                moveValueDown(textViewConstitutionValue,textViewIntelligenceValue);
                break;

            case R.id.imageButtonIntUp:
                moveValueDown(textViewIntelligenceValue,textViewConstitutionValue);
                break;
            case R.id.imageButtonIntDown:
                moveValueDown(textViewIntelligenceValue, textViewWisdomValue);
                break;

            case R.id.imageButtonWisUp:
                moveValueDown(textViewWisdomValue,textViewIntelligenceValue );
                break;
            case R.id.imageButtonWisDown:
                moveValueDown(textViewWisdomValue,textViewCharismaValue );
                break;

            case R.id.imageButtonCharUp:
                moveValueDown(textViewCharismaValue,textViewWisdomValue );
                break;
            case R.id.imageButtonCharDown: break;
        }

        }
    public void moveValueDown(TextView movedValueClick, TextView movedValue){
        String newValue;
        newValue = movedValue.getText().toString();
        movedValue.setText(movedValueClick.getText().toString());
        movedValueClick.setText(newValue);
    }

    public void setDefaultSimple() {
        strValue = 15;
        dexValue = 14;
        conValue = 13;
        intValue = 12;
        wisValue = 10;
        charValue = 8;
        setAttributes();
    }

    public void onClickGenerateRoll(View view) {
        setDefaultRandom();
    }


    public void setRaceBonus() {

    }
}