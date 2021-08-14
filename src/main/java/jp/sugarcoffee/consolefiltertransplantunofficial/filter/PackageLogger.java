package jp.sugarcoffee.consolefiltertransplantunofficial.filter;

import jp.sugarcoffee.consolefiltertransplantunofficial.ConsoleFilterTransplantUnofficial;
import org.apache.logging.log4j.Logger;

public class PackageLogger {
    static final Logger LOGGER = ConsoleFilterTransplantUnofficial.getLogger(new FilterNone());
}
