package com.gmail.AndrewJWickens;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.AndrewJWickens.Commands.HorseProtectionCommands;
import com.gmail.AndrewJWickens.DataStorage.CurrentEditMode;
import com.gmail.AndrewJWickens.DataStorage.EditType;
import com.gmail.AndrewJWickens.Listeners.HorseDamageListener;
import com.gmail.AndrewJWickens.Listeners.HorseInteractListener;

public class HorseProtection extends JavaPlugin {

    private final Logger logger = Logger.getLogger("Minecraft");
    private PluginDescriptionFile pdfFile;
    
    private ArrayList<CurrentEditMode> playersEditing;
    
    @Override
    public void onEnable() {
        pdfFile = this.getDescription();
        logger.info(pdfFile.getName() + " [V" + pdfFile.getVersion() + "] is enabling...");
        saveDefaultConfig();
        
        playersEditing = new ArrayList<CurrentEditMode>();
        
        getCommand("horse").setExecutor(new HorseProtectionCommands(this));
        getServer().getPluginManager().registerEvents(new HorseInteractListener(this), this);
        getServer().getPluginManager().registerEvents(new HorseDamageListener(this), this);
    }
    
    @Override
    public void onDisable() {
    	logger.info(pdfFile.getName() + "[V" + pdfFile.getVersion() + "] is disabling...");
    }
    
    public void setEdit(String name, EditType editType, String misc) {
    	CurrentEditMode e = null;
    	for (CurrentEditMode edit : playersEditing) {
    		if (edit.playerName.equalsIgnoreCase(name)) {
    			if (editType == EditType.NONE) {
    				e = edit;
    			} else {
    				edit.editType = editType;
    				edit.additionalInfo = misc;
    				return;
    			}
    		}
    	}
    	if (editType != EditType.NONE) {
    		playersEditing.add(new CurrentEditMode(name, editType, misc));
    	} else if (e != null) {
    		playersEditing.remove(e);
    	}
    }
    
    public EditType containsPlayer(String name) {
    	for (CurrentEditMode edit : playersEditing) {
    		if (edit.playerName.equalsIgnoreCase(name)) {
    			return edit.editType;
    		}
    	}
    	return EditType.NONE;
    }
    
    public String getAdditionalInfo(String name) {
    	for (CurrentEditMode edit : playersEditing) {
    		if (edit.playerName.equalsIgnoreCase(name)) {
    			return edit.additionalInfo;
    		}
    	}
    	return null;
    }
}
