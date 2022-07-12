package me.maanex.schmude;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.maanex.schmude.commands.ItemsCommand;
import me.maanex.schmude.content.blocks.DwayneBlock;
import me.maanex.schmude.content.blocks.FleshBlock;
import me.maanex.schmude.content.common.HammersLogic;
import me.maanex.schmude.content.common.ScythesLogic;
import me.maanex.schmude.content.entities.FairyStructEntity;
import me.maanex.schmude.content.items.blocks.DwayneBlockItem;
import me.maanex.schmude.content.items.blocks.FleshBlockItem;
import me.maanex.schmude.content.items.food.DonerMeat;
import me.maanex.schmude.content.items.food.Onion;
import me.maanex.schmude.content.items.hammers.DiamondHammer;
import me.maanex.schmude.content.items.hammers.GoldenHammer;
import me.maanex.schmude.content.items.hammers.IronHammer;
import me.maanex.schmude.content.items.hammers.NetheriteHammer;
import me.maanex.schmude.content.items.scythes.DiamondScythe;
import me.maanex.schmude.content.items.scythes.GoldenScythe;
import me.maanex.schmude.content.items.scythes.IronScythe;
import me.maanex.schmude.content.items.scythes.NetheriteScythe;
import me.maanex.schmude.content.tickhooks.FairySpawner;
import me.maanex.schmude.core.customcontent.CustomContent;
import me.maanex.schmude.core.customcontent.world.WorldEntityManager;
import me.maanex.schmude.core.gui.GuiHandler;
import me.maanex.schmude.features.customcontent.BlockBreakingCycle;
import me.maanex.schmude.features.customcontent.BlockChangeActions;
import me.maanex.schmude.features.mobgriefing.CreeperExplosions;
import me.maanex.schmude.features.nature.DropOnions;
import me.maanex.schmude.features.qol.PreventTrampling;
import me.maanex.schmude.features.qol.SlowdownAxe;
import me.maanex.schmude.features.snowballs.HitHandler;
import me.maanex.schmude.features.technical.ForceResoucrepack;
import me.maanex.schmude.features.technical.Serverlist;


public class Main extends JavaPlugin {

    public static final String RESOURCEPACK_URL = "https://cdn.discordapp.com/attachments/863176882975146034/926978152352337940/schmude-314121241.zip";

    //

    public static Main instance;

    public Main() {
        instance = this;
    }

    //

    @Override
    public void onEnable() {
        System.out.println("poggies");

        WorldEntityManager.init();

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

        /** INTERNAL * Technical */
        m.registerEvents(new Serverlist(), this);
        m.registerEvents(new ForceResoucrepack(), this);

        /** INTERNAL * Gui Handler */
        m.registerEvents(new GuiHandler(), this);

        /** FEATURE * Snowballs */
        m.registerEvents(new HitHandler(), this);

        /** FEATURE * Mobgriefing */
        m.registerEvents(new CreeperExplosions(), this);

        /** FEATURE * Quality of Life */
        m.registerEvents(new PreventTrampling(), this);
        m.registerEvents(new SlowdownAxe(), this);

        /** FEATURE * Custom Content */
        m.registerEvents(new BlockChangeActions(), this);
        m.registerEvents(new BlockBreakingCycle(), this);

        /** FEATURE * Nature */
        m.registerEvents(new DropOnions(), this);
    }

    private void registerCustomContent() {
        CustomContent.register(new DwayneBlock());
        CustomContent.register(new DwayneBlockItem());

        CustomContent.register(new FleshBlock());
        CustomContent.register(new FleshBlockItem());
        CustomContent.register(new DonerMeat());
        CustomContent.register(new Onion());

        CustomContent.register(new ScythesLogic());
        CustomContent.register(new GoldenScythe());
        CustomContent.register(new IronScythe());
        CustomContent.register(new DiamondScythe());
        CustomContent.register(new NetheriteScythe());

        CustomContent.register(new HammersLogic());
        CustomContent.register(new GoldenHammer());
        CustomContent.register(new IronHammer());
        CustomContent.register(new DiamondHammer());
        CustomContent.register(new NetheriteHammer());

        // CustomContent.register(new FairyStructEntity());

        //

        // new FairySpawner().schedule();
    }

    private void registerCommands() {
        getCommand("items").setExecutor(new ItemsCommand());
    }

}
