package me.maanex.schmude.core.customcontent.structs;

import org.bukkit.inventory.Recipe;

import me.maanex.schmude.core.customcontent.CustomContent;

public abstract class CustomTileEntity implements CustomElement {

  public int getId() {
    return -1;
  }

  //

  @Override
  public void addRecipes(Recipe... recipes) {
    CustomContent.addRecipes(recipes);
  }
  
}
