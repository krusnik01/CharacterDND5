package com.demo.newcharacter;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModelAbilitySpells extends AndroidViewModel {
    private static AbilitySpellsDatabase database;

    private LiveData<List<AbilitySpells>> abilitySpells;
    public MainViewModelAbilitySpells(@NonNull Application application) {
        super(application);
        database =AbilitySpellsDatabase.getInstance(getApplication());
        abilitySpells = database.abilitySpellsDao().getAllAbilitySpells();
    }

    public  LiveData<List<AbilitySpells>> getAllAbilitySpells() {return abilitySpells;} //возвращает все спелы

   // public void getAbilitySpells() {return null;}

    public  void insertAbilitySpells (AbilitySpells abilitySpells) {new InsertTask().execute(abilitySpells);} //по идее не нужно потом

 /*   public  void updateAbilitySpells (AbilitySpells abilitySpells) {new UpdateTask().execute(abilitySpells);}  //по идее не нужно

    public  void deleteAbilitySpells (AbilitySpells abilitySpells) {new DeleteTask().execute(abilitySpells);}//по идее не нужно

    public void deleteAllAbilitySpells() { new DeleteAllTask();} //по идее не нужно

    */

    private static class InsertTask extends AsyncTask<AbilitySpells, Void, Void> {

        @Override
        protected Void doInBackground(AbilitySpells... abilitySpells) {
            if (abilitySpells != null && abilitySpells.length >0) {
                database.abilitySpellsDao().insertAbilitySpells(abilitySpells[0]);
            }
            return null;
        }
    }



 /*   private static class UpdateTask extends AsyncTask<AbilitySpells, Void, Void> {

        @Override
        protected Void doInBackground(AbilitySpells... abilitySpells) {
            if (abilitySpells != null && abilitySpells.length >0) {
                database.abilitySpellsDao().updateAbilitySpells(abilitySpells[0]);
            }
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<AbilitySpells, Void, Void> {

        @Override
        protected Void doInBackground(AbilitySpells... abilitySpells) {
            if (abilitySpells != null && abilitySpells.length >0) {
                database.abilitySpellsDao().deleteAbilitySpells(abilitySpells[0]);
            }
            return null;
        }
    }


    private static class DeleteAllTask extends AsyncTask<Void, Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            database.abilitySpellsDao().deleteAllAbilitySpells();
            return null;
        }
    }
    */












}
