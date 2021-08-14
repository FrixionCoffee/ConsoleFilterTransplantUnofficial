package jp.sugarcoffee.consolefiltertransplantunofficial;

import jp.sugarcoffee.consolefiltertransplantunofficial.config.Config;
import jp.sugarcoffee.consolefiltertransplantunofficial.config.ConfigNone;
import jp.sugarcoffee.consolefiltertransplantunofficial.filter.FilterNone;
import jp.sugarcoffee.consolefiltertransplantunofficial.filter.JavaFilter;
import jp.sugarcoffee.consolefiltertransplantunofficial.filter.Log4jFilter;
import jp.sugarcoffee.consolefiltertransplantunofficial.filter.SystemFilter;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("console_filter_transplant_unofficial")
public class ConsoleFilterTransplantUnofficial {

    // Directly reference a log4j logger.
    static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ANNOTATION_VALUE = "console_filter_transplant_unofficial";

    public static Logger getLogger(@SuppressWarnings("unused") ConfigNone ignore) {
        return LOGGER;
    }

    public static Logger getLogger(@SuppressWarnings("unused") FilterNone ignore) {
        return LOGGER;
    }

    public ConsoleFilterTransplantUnofficial() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        FMLJavaModLoadingContext.get()
                .getModEventBus()
                .addListener(this::onModConfigEvent);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private void processIMC(final InterModProcessEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }

    public void onModConfigEvent(final InterModProcessEvent event) {
        Config.initConfig(event);

        JavaFilter.applyFilter();
        Log4jFilter.applyFilter();
        SystemFilter.applyFilter();
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        }
    }
}
