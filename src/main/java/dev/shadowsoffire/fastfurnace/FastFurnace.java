package dev.shadowsoffire.fastfurnace;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TagsUpdatedEvent;

@Mod(FastFurnace.MODID)
public class FastFurnace {

    public static final String MODID = "fastfurnace";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    /**
     * Fleet fix: bumped whenever the server (re)loads its data (startup, /reload, datapack changes). Furnaces compare it
     * with the generation of their cached recipe and drop the cache when it is stale, so they pick up changed or newly
     * added recipes instead of keeping the RecipeHolder of the previous RecipeManager.
     */
    public static volatile int recipeGeneration;

    public FastFurnace() {
        NeoForge.EVENT_BUS.addListener(TagsUpdatedEvent.class, e -> {
            if (e.getUpdateCause() == TagsUpdatedEvent.UpdateCause.SERVER_DATA_LOAD) {
                recipeGeneration++;
            }
        });
    }

}
