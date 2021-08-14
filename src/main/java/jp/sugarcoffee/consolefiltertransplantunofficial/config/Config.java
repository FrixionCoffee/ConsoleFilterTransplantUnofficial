package jp.sugarcoffee.consolefiltertransplantunofficial.config;

import jp.sugarcoffee.consolefiltertransplantunofficial.ConsoleFilterTransplantUnofficial;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Filter;

import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber
public class Config implements ConfigHandler {

    static final Logger LOGGER;
    public static final Config INSTANCE = new Config();

    public static List<String> filterTextList = null;

    static {
        LOGGER = ConsoleFilterTransplantUnofficial.
                getLogger(new ConfigNone());
    }


    public static void initConfig(@SuppressWarnings("unused") final InterModProcessEvent event) {
        LOGGER.debug("initConfig run.");
        final ConfigUtil CONFIG_UTIL = new ConfigUtil();

        filterTextList = CONFIG_UTIL
                .createOrIgnoreConfigFile()
                .readConfigFile();

        filterTextList.forEach(s -> LOGGER.debug("filter rule[" + s + "] loaded "));
        LOGGER.info(ConsoleFilterTransplantUnofficial.MOD_ANNOTATION_VALUE + " filter rule loaded. rule of number " + filterTextList.size());
    }

    public List<String> getMessagesToFilter() {
        Objects.requireNonNull(filterTextList);
        return filterTextList;
    }

}