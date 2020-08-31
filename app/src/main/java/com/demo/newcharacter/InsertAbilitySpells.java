package com.demo.newcharacter;

public class InsertAbilitySpells {
    private AbilitySpells abilitySpells;
    private String name;
    private String description;


    public void insertNewAbility(){
        name = "Тёмное зрение";
        description = "Привыкнув к жизни под землёй, вы обладаете превосходным зрением в темноте и при тусклом освещении. На расстоянии в 60 фт. вы при тусклом освещении можете видеть так, как будто это яркое освещение, и в темноте так, как будто это тусклое освещение. В темноте вы не можете различать цвета, только оттенки серого.";
                abilitySpells = new AbilitySpells(name,description);
    }

}
