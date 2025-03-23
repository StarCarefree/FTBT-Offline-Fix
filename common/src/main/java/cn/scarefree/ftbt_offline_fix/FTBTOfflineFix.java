package cn.scarefree.ftbt_offline_fix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FTBTOfflineFix {
    public static final String MOD_ID = "ftbt_offline_fix";
    public static final Logger LOGGER = LogManager.getLogger("FTBT Offline Fix");

    public static void init() {
        // Write common init code here.
        LOGGER.info("FTBT Offline Fix initialized.");
    }
}
