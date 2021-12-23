package me.maanex.schmude.core.customcontent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.event.Listener;

import me.maanex.schmude.Main;
import me.maanex.schmude.core.customcontent.structs.CustomBlock;
import me.maanex.schmude.core.customcontent.structs.CustomElement;
import me.maanex.schmude.core.customcontent.structs.CustomItem;
import me.maanex.schmude.core.customcontent.structs.CustomTileEntity;

public class CustomContent {

  public static final Map<Integer, Instrument> CUSTOMBLOCK_INSTRUMENTS = new HashMap<Integer, Instrument>();
  public static final Set<Material> FORBIDDEN_INSTRUMENT_BLOCKS = new HashSet<Material>();

  static {
    CUSTOMBLOCK_INSTRUMENTS.put(0, Instrument.IRON_XYLOPHONE);
    CUSTOMBLOCK_INSTRUMENTS.put(1, Instrument.DIDGERIDOO);
    CUSTOMBLOCK_INSTRUMENTS.put(2, Instrument.BIT);
    CUSTOMBLOCK_INSTRUMENTS.put(3, Instrument.BANJO);

    FORBIDDEN_INSTRUMENT_BLOCKS.add(Material.IRON_BLOCK);
    FORBIDDEN_INSTRUMENT_BLOCKS.add(Material.PUMPKIN);
    FORBIDDEN_INSTRUMENT_BLOCKS.add(Material.EMERALD_BLOCK);
    FORBIDDEN_INSTRUMENT_BLOCKS.add(Material.HAY_BLOCK);
  }

  //

  public final static List<CustomElement> allCustomElements = new ArrayList<>();
  public final static List<CustomBlock> cusomBlocks = new ArrayList<>();
  public final static List<CustomItem> cusomItems = new ArrayList<>();
  public final static List<CustomTileEntity> customTileEntities = new ArrayList<>();
  
  public static void register(CustomElement element) {
    allCustomElements.add(element);

    if (element instanceof CustomBlock)
      cusomBlocks.add((CustomBlock) element);
    else if (element instanceof CustomItem)
      cusomItems.add((CustomItem) element);
    else if (element instanceof CustomTileEntity)
      customTileEntities.add((CustomTileEntity) element);

    if (element instanceof Listener)
      Main.instance.getServer().getPluginManager().registerEvents((Listener) element, Main.instance);
  }

  @SuppressWarnings("unchecked")
  public static <T extends CustomBlock> T getCustomBlockInstance(Class<T> cls) {
    return (T) cusomBlocks.stream()
      .filter(cls::isInstance)
      .findFirst()
      .orElse(null);
  }

}
