package me.maanex.schmude.core.customcontent.structs;

import org.bukkit.Location;
import org.bukkit.inventory.Recipe;

import me.maanex.schmude.core.customcontent.CustomContent;

public abstract class CustomStructEntity implements CustomElement {

  //

  private int id;

  public CustomStructEntity(int id) {
    this.id = id;
  }

  //

  public int getId() {
    return id;
  }

  //

  public abstract void spawnAt(Location loc);

  //


  @Override
  public void addRecipes(Recipe... recipes) {
    CustomContent.addRecipes(recipes);
  }
  
}
