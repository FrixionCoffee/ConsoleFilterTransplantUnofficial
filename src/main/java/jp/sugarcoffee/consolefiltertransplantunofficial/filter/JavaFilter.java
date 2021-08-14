package jp.sugarcoffee.consolefiltertransplantunofficial.filter;

import jp.sugarcoffee.consolefiltertransplantunofficial.config.Config;

import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class JavaFilter implements Filter {

    @Override
    public boolean isLoggable(LogRecord record) {
        for (String s : Config.INSTANCE.getMessagesToFilter()) {
            if (record.getMessage().contains(s)) {
                return false;
            }
        }
        return true;
    }

    public static void applyFilter() {
        Logger.getLogger("").setFilter(new JavaFilter());
    }

}