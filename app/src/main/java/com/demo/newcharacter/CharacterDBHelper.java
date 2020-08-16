/*package com.demo.newcharacter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CharacterDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "character.db";
    private static final int DB_VERSION = 1;

    public CharacterDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {                   //создать таблицу в бд
   //    db.execSQL(CharacterContract.CharacterEntry.CREATE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      //  db.execSQL(CharacterContract.CharacterEntry.DROP_COMMAND);
     //   onCreate(db);
    }
}
*/