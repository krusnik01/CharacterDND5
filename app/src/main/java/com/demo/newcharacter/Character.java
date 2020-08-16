package com.demo.newcharacter;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "CharacterNew")
//показываем что это будет БД и имя бп
public class Character implements Serializable {                            //наследуемся для того что бы передавать как объект в интент
    @PrimaryKey(autoGenerate = true)
    //создаём первичный ключ и автогенерацию
    private int idCharacter;            //id Для БД
    private String nameCharacter;       //имя персонажа
    private String imgCharacter; // урл картинки
    private String classCharacter;   //класс
    private String raceCharacter;   //класс
    private int lvlCharacter;        //уровень персонажа
    private int minExp;             //текущий опыт
    private int maxExp;             //опыт до след уровня


    public Character(int idCharacter, String nameCharacter, String imgCharacter, String classCharacter, String raceCharacter, int lvlCharacter, int minExp, int maxExp) {
        this.idCharacter = idCharacter;
        this.nameCharacter = nameCharacter;
        this.imgCharacter = imgCharacter;
        this.classCharacter = classCharacter;
        this.raceCharacter = raceCharacter;
        this.lvlCharacter = lvlCharacter;
        this.minExp = minExp;
        this.maxExp = maxExp;
    }

    @Ignore
    public Character(String nameCharacter, String imgCharacter, String classCharacter, String raceCharacter, int lvlCharacter, int minExp, int maxEx) {
        this.nameCharacter = nameCharacter;
        this.imgCharacter = imgCharacter;
        this.classCharacter = classCharacter;
        this.raceCharacter = raceCharacter;
        this.lvlCharacter = lvlCharacter;
        this.minExp = minExp;
        this.maxExp = maxEx;
    }


    public String getNameCharacter() {
        return nameCharacter;
    }

    public String getImgCharacter() {
        return imgCharacter;
    }

    public String getClassCharacter() {
        return classCharacter;
    }

    public int getIdCharacter() {
        return idCharacter;
    }

    public String getRaceCharacter() {
        return raceCharacter;
    }

    public int getLvlCharacter() {
        return lvlCharacter;
    }

    public int getMinExp() {
        return minExp;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public void setIdCharacter(int idCharacter) {
        this.idCharacter = idCharacter;
    }

    public void setNameCharacter(String nameCharacter) {
        this.nameCharacter = nameCharacter;
    }

    public void setImgCharacter(String imgCharacter) {
        this.imgCharacter = imgCharacter;
    }

    public void setClassCharacter(String classCharacter) {
        this.classCharacter = classCharacter;
    }

    public void setRaceCharacter(String raceCharacter) {
        this.raceCharacter = raceCharacter;
    }

    public void setLvlCharacter(int lvlCharacter) {
        this.lvlCharacter = lvlCharacter;
    }

    public void setMinExp(int minExp) {
        this.minExp = minExp;
    }

    public void setMaxExp(int maxExp) {
        this.maxExp = maxExp;
    }
}
