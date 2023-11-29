package me.maanex.schmude.content.items.misc;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.maanex.schmude.core.customcontent.structs.CustomItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class Test extends CustomItem {
  
  public Test() {
    super(105, "test", Material.SUGAR);


  }
  
  @Override
  public ItemStack asItemStack() {
    ItemStack tester = new ItemStack(Material.STONECUTTER);
    ItemMeta meter = tester.getItemMeta();
    meter.setCustomModelData(1);
    meter.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("ae", 2, Operation.MULTIPLY_SCALAR_1));
    meter.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier("ae2", 4, Operation.ADD_NUMBER));
    meter.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
    meter.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    meter.displayName(Component.text("Ayooe ").append(Component.translatable("schmude.item.fleshblock.name").color(NamedTextColor.AQUA)));
    tester.setItemMeta(meter);
    return tester;
  }

}