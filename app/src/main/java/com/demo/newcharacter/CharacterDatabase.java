package com.demo.newcharacter;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities  = {Character.class}, version = 1,exportSchema = false)

public abstract class CharacterDatabase extends RoomDatabase {

    private static CharacterDatabase database;
    private static final String DB_NAME = "characterNew2.db";
    private static final Object LOCK = new Object();                //создаем объёкт замок

    public static CharacterDatabase getInstance(Context context) {         //если нет базы то создать иначе вернуть базу
        synchronized (LOCK) {                   //синхронизация потоков против создания двух БД
            if (database == null) {
                database = Room.databaseBuilder(context, CharacterDatabase.class, DB_NAME)
                        .build();
            }
        }
        return database;
    }

    public abstract  CharacterDao characterDao();
}
