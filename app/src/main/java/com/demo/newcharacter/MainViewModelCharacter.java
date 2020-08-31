package com.demo.newcharacter;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModelCharacter extends AndroidViewModel {                //для работы в другом потоке


    private static CharacterDatabase database;

    private LiveData<List<Character>> characters; //храним всех персонажей

    public MainViewModelCharacter(@NonNull Application application) {
        super(application);
        database = CharacterDatabase.getInstance(getApplication());
        characters = database.characterDao().getAllCharacter();
    }

    public LiveData<List<Character>> getCharacters() {return characters;}//метод для всех заметок

    public void insertCharacter(Character character) {new InsertTask().execute(character);    }//вставка персонажа

    public void updateCharacter(Character character) {new UpdateTask().execute(character);    } //вставка персонажа

    public void deleteCharacter(Character character) {new DeleteTask().execute(character);    }//удаление персонажа

    public void deleteAllCharacter() {new DeleteAllTask();    }//удаление все в бд


    private static class InsertTask extends AsyncTask<Character, Void, Void> {
        @Override
        protected Void doInBackground(Character... characters) {
            if (characters != null && characters.length > 0) {
                database.characterDao().insertCharacter(characters[0]);             //вставляем данные в бд
            }
            return null;
        }
    }

    private static class UpdateTask extends AsyncTask<Character, Void, Void> {
        @Override
        protected Void doInBackground(Character... characters) {
            if (characters != null && characters.length > 0) {
                database.characterDao().updateCharacter(characters[0]);             //обновляем  данные в бд
            }
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<Character, Void, Void> {
        @Override
        protected Void doInBackground(Character... characters) {
            if (characters != null && characters.length > 0) {
                database.characterDao().deleteCharacter(characters[0]);             //удаляем  данные в бд
            }
            return null;
        }
    }

    private static class DeleteAllTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... characters) {
            database.characterDao().deleteAllCharacter(); //удаляем  все  в бд

            return null;
        }
    }
}
