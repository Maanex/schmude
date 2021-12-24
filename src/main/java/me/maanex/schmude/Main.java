package me.maanex.schmude;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.maanex.schmude.commands.ItemsCommand;
import me.maanex.schmude.content.blocks.DwayneBlock;
import me.maanex.schmude.content.blocks.FleshBlock;
import me.maanex.schmude.content.common.ScythesLogic;
import me.maanex.schmude.content.items.DonerMeat;
import me.maanex.schmude.content.items.DwayneBlockItem;
import me.maanex.schmude.content.items.FleshBlockItem;
import me.maanex.schmude.core.customcontent.CustomContent;
import me.maanex.schmude.core.gui.GuiHandler;
import me.maanex.schmude.features.customcontent.BlockBreakingCycle;
import me.maanex.schmude.features.customcontent.BlockChangeActions;
import me.maanex.schmude.features.customcontent.PreventBlockDefaults;
import me.maanex.schmude.features.customcontent.PreventItemDefaults;
import me.maanex.schmude.features.qol.PreventTrampling;
import me.maanex.schmude.features.qol.SlowdownAxe;
import me.maanex.schmude.features.snowballs.HitHandler;


public class Main extends JavaPlugin {

    public static Main instance;

    public Main() {
        instance = this;
    }

    //

    @Override
    public void onEnable() {
        System.out.println("poggies");

        registerEventHandlers();
        registerCustomContent();
        registerCommands();
    }

    @Override
    public void onDisable() {
        System.out.println("over and out");
    }

    //

    private void registerEventHandlers() {
        PluginManager m = getServer().getPluginManager();

        /** INTERNAL * Gui Handler */
        m.registerEvents(new GuiHandler(), this);

        /** FEATURE * Snowballs */
        m.registerEvents(new HitHandler(), this);

        /** FEATURE * Quality of Life */
        m.registerEvents(new PreventTrampling(), this);
        m.registerEvents(new SlowdownAxe(), this);

        /** FEATURE * Custom Content */
        m.registerEvents(new BlockChangeActions(), this);
        m.registerEvents(new BlockBreakingCycle(), this);
        m.registerEvents(new PreventBlockDefaults(), this);
        m.registerEvents(new PreventItemDefaults(), this);
    }

    private void registerCustomContent() {
        CustomContent.register(new DwayneBlock());
        CustomContent.register(new DwayneBlockItem());

        CustomContent.register(new FleshBlock());
        CustomContent.register(new FleshBlockItem());
        CustomContent.register(new DonerMeat());

        CustomContent.register(new ScythesLogic());
    }

    private void registerCommands() {
        getCommand("items").setExecutor(new ItemsCommand());
    }

}
