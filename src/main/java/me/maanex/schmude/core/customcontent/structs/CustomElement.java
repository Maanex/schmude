package me.maanex.schmude.core.customcontent.structs;

import org.bukkit.inventory.Recipe;

public interface CustomElement {

  public void addRecipes(Recipe... recipes);

  public int getId();

  public default void init() {}

}
