package me.maanex.schmude.core.customcontent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Recipe;

import me.maanex.schmude.Main;
import me.maanex.schmude.core.customcontent.structs.CustomBlock;
import me.maanex.schmude.core.customcontent.structs.CustomElement;
import me.maanex.schmude.core.customcontent.structs.CustomItem;
import me.maanex.schmude.core.customcontent.structs.CustomTileEntity;

public class CustomContent {

  public final static List<CustomElement> allCustomElements = new ArrayList<>();
  public final static Map<Integer, CustomBlock> customBlocks = new HashMap<>();
  public final static Map<Integer, CustomItem> customItems = new HashMap<>();
  public final static List<CustomItem> customItemsList = new ArrayList<>();
  public final static Map<Integer, CustomTileEntity> customTileEntities = new HashMap<>();
  
  public static void register(CustomElement element) {
    allCustomElements.add(element);

    if (element instanceof CustomBlock) {
      customBlocks.put(element.getId(), (CustomBlock) element);
    } else if (element instanceof CustomItem) {
      customItems.put(element.getId(), (CustomItem) element);
      customItemsList.add((CustomItem) element);
    } else if (element instanceof CustomTileEntity) {
      customTileEntities.put(element.getId(), (CustomTileEntity) element);
    }

    if (element instanceof Listener)
      Main.instance.getServer().getPluginManager().registerEvents((Listener) element, Main.instance);
    
    element.init();
  }

  @SuppressWarnings("unchecked")
  public static <T extends CustomBlock> T getCustomBlockInstance(Class<T> cls) {
    return (T) customBlocks.values().stream()
      .filter(cls::isInstance)
      .findFirst()
      .orElse(null);
  }

  @SuppressWarnings("unchecked")
  public static <T extends CustomItem> T getCustomItemInstance(Class<T> cls) {
    return (T) customItems.values().stream()
      .filter(cls::isInstance)
      .findFirst()
      .orElse(null);
  }

  public static void addRecipes(Recipe... recipes) {
    for (Recipe r : recipes)
      Bukkit.addRecipe(r);
  }

}
