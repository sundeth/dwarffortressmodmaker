/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.utils;

/**
 *
 * @author Ander
 */
public class StringLibrary {
    public static final String APP_NAME = "Dwarf Fortress Mod Maker";
    public static final String APP_NAME2 = "D.F.M.M.";
    
    public static final String INIT_INSTALLATION_TITLE = "Select the main path to yor Dwarf Fortress Installation";
    public static final String INIT_ERROR_JSON = "Error reading init.json, check file.";
    public static final String INIT_INVALID_PATH = "Invalid path, select Dwarf Fortress root folder.";
    public static final String INIT_ERROR_SAVE_DATA = "Error on savind data, check if you have permission.";
    public static final String INIT_GAME_PATH = "Invalid path, select Dwarf Fortress root folder.";
    public static final String INIT_PARSE_ERROR = "There was an error parsing #MOD. Its mod will not appear on the list";
    public static final String INIT_OBJECT_PARSE_ERROR = "There was an error parsing #MOD. Make sure the file is formatted correctly";
    
    public static final String GENERAL_ERROR = "Error";
    public static final String GENERAL_CONFIRMATION = "Confirmation";
    public static final String GENERAL_SUCCESS = "Sucess";
    
    public static final String MAIN_CANNOT_REMOVE = "Cannot remove files not created with " + APP_NAME;
    public static final String MAIN_CONFIRM_REMOVE = "Are you sure you want to remove this mod?";
    public static final String MAIN_REMOVE_ERROR = "There was an error while trying to delete the mod files, check if the game isn't running.";
    public static final String MAIN_NEW_MOD_INPUT = "What will be the mod's folder name?";
    public static final String MAIN_ERROR_SAVE_MODINFO = "There was an error while trying to create the mod folder, check if the input name is valid and you have permission to write on the mods folder.";
    public static final String MAIN_SAVE_SUCCESS = "Local mod files updated.";
    public static final String MAIN_ERROR_SAVE_DATA = "There was an error while trying to save the mod information.";
    public static final String MAIN_LOCAL_UPDATE_CONFIRMATION = "This will overrite files on your local data\\installed_mods\\.Do you want to proceed?";
    public static final String MAIN_LOCAL_UPDATE_ERROR = "An error occured while moving files.";
    public static final String MAIN_LOCAL_UPDATE_SUCESS = "Local files have been updated.";
    public static final String MAIN_LOCAL_UPDATE_BAD_MOD = "Only mods created by this tool can be updated and/or published.";
    public static final String MAIN_PUBLISH_CONFIRMATION = "This will copy this mod to the mod_upload folder.\nYou will need to start the game to actually send the files.\nDo you want to proceed?";
    public static final String MAIN_PUBLISH_SUCESS = "Steam folder updated. Run the game to upload files.";
    
    public static final String MAIN_LIBRARY_UPDATE = "WARNING! This operation will replace your local raw structure file.\nMake sure you are connected to the internet.\nDo you want to proceed?";
    public static final String MAIN_LIBRARY_ERROR = "It was not possible to update the library, check your connection and try again.";
}
