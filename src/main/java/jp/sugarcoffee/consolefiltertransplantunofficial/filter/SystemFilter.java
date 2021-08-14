package jp.sugarcoffee.consolefiltertransplantunofficial.filter;

import jp.sugarcoffee.consolefiltertransplantunofficial.config.Config;

import java.io.OutputStream;
import java.io.PrintStream;


public class SystemFilter extends PrintStream {

	public SystemFilter(OutputStream out) {
		super(out, true);
	}

	@Override
	public void println(String s) {
		if (!shouldFilter(s)) {
			super.println(s);
		}
	}

	private boolean shouldFilter(String s) {
		for (String filter : Config.INSTANCE.getMessagesToFilter()) {
			if (s.contains(filter)) {
				return true;
			}
		}
		return false;
	}

	public static void applyFilter() {
		System.setOut(new SystemFilter(System.out));
	}

}
