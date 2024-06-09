package net.smileycorp.witherproof;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Constants.MODID, name = Constants.NAME, version = Constants.VERSION, acceptableRemoteVersions = "*")
public class Witherproof {
    
    private static Logger logger = LogManager.getLogger(Constants.NAME);
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.init(new Configuration(event.getSuggestedConfigurationFile()));
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Config.load();
    }
    
    public static void logInfo(Object message) {
        logger.info(message);
    }
    
    public static void logError(Object message, Throwable exception) {
        logger.error(message, exception);
        exception.printStackTrace();
    }
    
}
