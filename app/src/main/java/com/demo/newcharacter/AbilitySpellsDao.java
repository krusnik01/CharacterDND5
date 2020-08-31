package com.demo.newcharacter;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface AbilitySpellsDao {
    @Query("SELECT * FROM abilitySpells ORDER BY name DESC")
    LiveData<List<AbilitySpells>> getAllAbilitySpells();         //получаем всех персонажей

    @Query("SELECT * FROM abilitySpells WHERE  name = :nameAbilitySpells")
    AbilitySpells getAbilitySpells(String nameAbilitySpells);         //получаем одного по id

    @Insert
        //вставляем нового (или меняем старого?)
    void insertAbilitySpells(AbilitySpells abilitySpells);

  /*  @Update
        //обновляем
    void updateAbilitySpells(AbilitySpells abilitySpells);

    @Delete
        //удаляем персонажа
    void deleteAbilitySpells(AbilitySpells abilitySpells);

    @Query("DELETE FROM abilitySpells")
        //удаляем
    void deleteAllAbilitySpells();


   */
}
