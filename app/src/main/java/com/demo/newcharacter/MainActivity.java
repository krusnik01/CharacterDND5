package com.demo.newcharacter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCharacter;
    private final ArrayList<Character> characters = new ArrayList<>();                            // массив персонажей который попадает в ресайклвью
    public CharacterAdapter characterAdapter;
    private MainViewModelCharacter viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        viewModel = ViewModelProviders.of(this).get(MainViewModelCharacter.class);
        if (actionBar != null) {
            actionBar.hide();
        }
        recyclerViewCharacter = findViewById(R.id.recyclerViewCharacter);
        characterAdapter = new CharacterAdapter(characters);
        recyclerViewCharacter.setLayoutManager(new LinearLayoutManager(this));
        getData();
        recyclerViewCharacter.setAdapter(characterAdapter);
        characterAdapter.setOnNoteClickListener(new CharacterAdapter.OnNoteClickListener() {            //устанавливаем слушатель кликов

            @Override //что делать при коротком  клике
            public void onNoteClick(int position) {
                Character character = characterAdapter.getCharacters().get(position);
                viewCharacter(character);
            }

            @Override
            public void onNoteLongClick(int position) {                                                     //что делать при долгом клике
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {                    //действия при таче
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {              // при свайпе  direction это направление
                removeCharacter(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewCharacter);                                     //применяем к ресайклвью
    }

    private void viewCharacter(Character character) {                                              //открываем персонажа для просмотра
        Intent intent = new Intent(this, CharacterView.class);
        intent.putExtra("character", character);
        startActivity(intent);
    }

    private void removeCharacter(int position) {
        Character character = characterAdapter.getCharacters().get(position);
        viewModel.deleteCharacter(character);

    }

    public void onClickAddCharacter(View view) {                                                    //передаём позицию персонажа в активность
        Intent intent = new Intent(this, AddCharacterActivity.class);
        startActivity(intent);
    }

    private void getData() {
        LiveData <List<Character>> charactersFromDB = viewModel.getCharacters();          //мы получили всех персонажей в просмоторщик событий
        charactersFromDB.observe(this, new Observer<List<Character>>() {
            @Override
            public void onChanged(List<Character> charactersFromLiveData) {
                characterAdapter.setCharacters(charactersFromLiveData);
            }
        });

    }


}