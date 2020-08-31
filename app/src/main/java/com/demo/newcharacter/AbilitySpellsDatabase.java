package com.demo.newcharacter;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities  = {AbilitySpells.class}, version = 1,exportSchema = false)

public abstract class AbilitySpellsDatabase extends RoomDatabase {
    private static AbilitySpellsDatabase database;
    private static final String DB_NAME = "abilitySpells.db";
    private static final Object LOCK = new Object();                //создаем объёкт замок

    public static AbilitySpellsDatabase getInstance(Context context) {         //если нет базы то создать иначе вернуть базу
        synchronized (LOCK) {                   //синхронизация потоков против создания двух БД
            if (database == null) {
                database = Room.databaseBuilder(context, AbilitySpellsDatabase.class, DB_NAME)
                        .build();
            }
        }
        return database;
    }

    public abstract  AbilitySpellsDao abilitySpellsDao();
}
