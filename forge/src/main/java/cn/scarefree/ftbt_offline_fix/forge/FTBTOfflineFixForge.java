package cn.scarefree.ftbt_offline_fix.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import cn.scarefree.ftbt_offline_fix.FTBTOfflineFix;

@Mod(FTBTOfflineFix.MOD_ID)
public final class FTBTOfflineFixForge {
    public FTBTOfflineFixForge(FMLJavaModLoadingContext context) {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(FTBTOfflineFix.MOD_ID, context.getModEventBus());

        // Run our common setup.
        FTBTOfflineFix.init();
    }
}
