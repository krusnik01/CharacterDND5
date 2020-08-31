package com.demo.newcharacter;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "AbilitySpells")
public class AbilitySpells {
    @PrimaryKey(autoGenerate = true)

    private int idAbilitySpells;
    private String name;
    private String description;

    public AbilitySpells(int idAbilitySpells, String name, String description) {
        this.idAbilitySpells = idAbilitySpells;
        this.name = name;
        this.description = description;
    }
    @Ignore
    public AbilitySpells( String name, String description) {
        this.idAbilitySpells = idAbilitySpells;
        this.name = name;
        this.description = description;
    }

    public void setIdAbilitySpells(int idAbilitySpells) {
        this.idAbilitySpells = idAbilitySpells;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdAbilitySpells() {
        return idAbilitySpells;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
