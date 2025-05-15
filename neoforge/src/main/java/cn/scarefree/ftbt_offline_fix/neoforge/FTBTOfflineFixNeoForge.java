package cn.scarefree.ftbt_offline_fix.neoforge;

import cn.scarefree.ftbt_offline_fix.FTBTOfflineFix;
import net.neoforged.fml.common.Mod;

@Mod(FTBTOfflineFix.MOD_ID)
public final class FTBTOfflineFixNeoForge {
    public FTBTOfflineFixNeoForge() {
        FTBTOfflineFix.init();
    }
}
