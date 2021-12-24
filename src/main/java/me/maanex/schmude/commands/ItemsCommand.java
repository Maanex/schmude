package me.maanex.schmude.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.maanex.schmude.Main;
import me.maanex.schmude.core.customcontent.CustomContent;
import me.maanex.schmude.core.gui.GuiHandler;
import me.maanex.schmude.core.gui.GuiInstance;
import me.maanex.schmude.core.gui.GuiInteractions;
import me.maanex.schmude.lib.GuiItems;


public class ItemsCommand implements CommandExecutor, GuiInteractions {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) return false;
    Player player = (Player) sender;
    if (!player.isOp()) return false;

    player.openInventory(buildCustomContentInventory(player));

    return false;
  }

  public Inventory buildCustomContentInventory(Player player) {
    Inventory out = Main.instance.getServer().createInventory(player, 54, "§9Custom Content");

    fillInventoryWithCustomContent(out, 0);

    GuiHandler.registerInstance(new GuiInstance(out, player, this));

    return out;
  }

  private void fillInventoryWithCustomContent(Inventory inv, int page) {
    final int USEABLE_SLOTS = 9 * 5;
    final int PAGE_COUNT = CustomContent.customItemsList.size() / USEABLE_SLOTS + 1;
    if (page < 0) page = 0;
    if (page >= PAGE_COUNT) page = PAGE_COUNT - 1;

    for (int i = 0; i < USEABLE_SLOTS; i++) {
      int index = i + USEABLE_SLOTS * page;
      if (index >= CustomContent.customItemsList.size()) {
        inv.setItem(i, null);
      } else {
        ItemStack item = CustomContent.customItemsList.get(index).asItemStack();
        inv.setItem(i, item);
      }
    }

    for (int i = 0; i < 9; i++)
      inv.setItem(USEABLE_SLOTS + i, GuiItems.getBlockingItem());

    inv.setItem(45, GuiItems.getCustomButton(Material.GREEN_STAINED_GLASS_PANE, "§aBack", "page:" + (page - 1)));
    inv.setItem(53, GuiItems.getCustomButton(Material.GREEN_STAINED_GLASS_PANE, "§aNext", "page:" + (page + 1)));
  }

  @Override
  public void onGuiClick(InventoryClickEvent e, GuiInstance instance) {
    if (e.getCurrentItem() == null) return;
    ItemStack item = e.getCurrentItem();

    if (!item.hasItemMeta()) return;
    ItemMeta meta = item.getItemMeta();

    if (!meta.hasLocalizedName()) return;
    String contents = meta.getLocalizedName();

    if (!contents.startsWith("GUI:")) return;
    String action = contents.substring(4);

    e.setCancelled(true);

    if (action.startsWith("page")) {
      int page = Integer.parseInt(action.substring(5));
      fillInventoryWithCustomContent(e.getClickedInventory(), page);
    }
  }
  
}
