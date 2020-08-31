package com.demo.newcharacter;

public class Special {

    private int strength;
    private int dexterity;
    private int constitution;
    private int intilligence;
    private int wisdom;
    private int charisma;

    public Special(int strength, int dexterity, int constitution, int intilligence, int wisdom, int charisma) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intilligence = intilligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public void setIntilligence(int intilligence) {
        this.intilligence = intilligence;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getIntilligence() {
        return intilligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getCharisma() {
        return charisma;
    }
}
