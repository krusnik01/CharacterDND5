package com.demo.newcharacter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
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
    private TextView textViewFreeValue;
    private TextView textViewStrengthValue;
    private TextView textViewDexterityValue;
    private TextView textViewConstitutionValue;
    private TextView textViewIntelligenceValue;
    private TextView textViewWisdomValue;
    private TextView textViewCharismaValue;

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

    private int idButton;
    private int idImageButton;

    private int strValue;
    private int dexValue;
    private int conValue;
    private int intValue;
    private int wisValue;
    private int charValue;

    private MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_character);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        searchItem();
        setAttributes();

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
                changesButtonPlus();
            } else {
                changesButtonUp();
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

    }

    public void setAttributes() {
        strValue = Integer.parseInt(textViewStrengthValue.getText().toString());
        dexValue = Integer.parseInt(textViewDexterityValue.getText().toString());
       conValue = Integer.parseInt(textViewConstitutionValue.getText().toString());
        intValue = Integer.parseInt(textViewIntelligenceValue.getText().toString());
        wisValue = Integer.parseInt(textViewWisdomValue.getText().toString());
        charValue = Integer.parseInt(textViewCharismaValue.getText().toString());

    }

    View.OnClickListener setAttributesClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           ImageButton imageButtonClick = (ImageButton) view;
            idImageButton = imageButtonClick.getId();
            setValueAttributes(idImageButton);
            setAttributes();
        }
    };

    private void setValueAttributes (int idImageButton){
        switch (idImageButton) {
            case R.id.imageButtonStrUp: strValue += 1; textViewStrengthValue.setText(String.format("%s", strValue)); break;
            case R.id.imageButtonStrDown: strValue -= 1;  textViewStrengthValue.setText(String.format("%s", strValue)); break;

            case R.id.imageButtonDexUp: dexValue += 1; textViewDexterityValue.setText(String.format("%s", dexValue)); break;
            case R.id.imageButtonDexDown: dexValue -= 1; textViewDexterityValue.setText(String.format("%s", dexValue)); break;

            case R.id.imageButtonConUp: conValue += 1; textViewConstitutionValue.setText(String.format("%s", conValue+ 1)); break;
            case R.id.imageButtonConDown:conValue -= 1;  textViewConstitutionValue.setText(String.format("%s", conValue)); break;

            case R.id.imageButtonIntUp:  intValue += 1;textViewIntelligenceValue.setText(String.format("%s", intValue + 1)); break;
            case R.id.imageButtonIntDown:  intValue -= 1;textViewIntelligenceValue.setText(String.format("%s", intValue)); break;

            case R.id.imageButtonWisUp:  wisValue += 1;textViewWisdomValue.setText(String.format("%s", wisValue+ 1)); break;
            case R.id.imageButtonWisDown: wisValue -= 1; textViewWisdomValue.setText(String.format("%s", wisValue)); break;

            case R.id.imageButtonCharUp:  charValue += 1;textViewCharismaValue.setText(String.format("%s", charValue)); break;
            case R.id.imageButtonCharDown:  charValue -= 1;textViewCharismaValue.setText(String.format("%s", charValue)); break;

        }
    }


}