package com.oresomecraft.HorseProtection.DataStorage;

public class CurrentEditMode {
    public String playerName;
    public EditType editType;
    public String additionalInfo;

    public CurrentEditMode(String name, EditType editType, String misc) {
        playerName = name;
        this.editType = editType;
        additionalInfo = misc;
    }
}
