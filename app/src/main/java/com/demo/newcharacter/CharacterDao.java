package com.demo.newcharacter;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.ArrayList;
import java.util.List;

@Dao
public interface CharacterDao {                             //объект доступа к данным
    @Query("SELECT * FROM CharacterNew ORDER BY lvlCharacter DESC")
    LiveData<List<Character>> getAllCharacter();         //получаем всех персонажей

    @Query("SELECT * FROM CharacterNew WHERE  idCharacter = :idCharacter")
    LiveData<List<Character>> getCharacter(int idCharacter);         //получаем одного

    @Insert
        //вставляем нового (или меняем старого?)
    void insertCharacter(Character character);

    @Update
        //обновляем
    void updateCharacter(Character character);

    @Delete
        //удаляем персонажа
    void deleteCharacter(Character character);

    @Query("DELETE FROM characternew")
        //удаляем
    void deleteAllCharacter();
}
