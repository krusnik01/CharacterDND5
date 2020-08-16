package com.demo.newcharacter;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {                //для работы в другом потоке

    private static CharacterDatabase database;

    private LiveData<List<Character>> characters; //храним всех персонажей

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = CharacterDatabase.getInstance(getApplication());
        characters = database.characterDao().getAllCharacter();
    }

    public LiveData<List<Character>> getCharacters() {          //метод для всех заметок
        return characters;
    }

    public void insertCharacter(Character character) {          //вставка персонажа
        new InsertTask().execute(character);
    }

    public void updateCharacter(Character character) {          //вставка персонажа
        new UpdateTask().execute(character);
    }

    public void deleteCharacter(Character character) {          //удаление персонажа
        new DeleteTask().execute(character);
    }

  /*  public void GetCharacter(Character character) {          //открытие персонажа персонажа
        new GetTask().execute(character);
    }*/

    public void deleteAllCharacter() {          //удаление все в бд
        new DeleteAllTask();
    }

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

   /* private static class GetTask extends AsyncTask<Character, Void, Void> {

        @Override
        protected Void doInBackground(Character... characters) {
            database.characterDao().getCharacter(characters[0].getIdCharacter());             //берем одного персонажа  тут неправильная логика, ДУМАЙ ВОВА, ДУМАЙ!
            return null;
        }
    }*/

    private static class DeleteAllTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... characters) {
            database.characterDao().deleteAllCharacter(); //удаляем  все  в бд

            return null;
        }
    }
}
