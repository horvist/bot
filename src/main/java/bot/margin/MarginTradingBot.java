package bot.margin;

import bot.common.BotI;

public class MarginTradingBot implements BotI {
	
	private static final MarginTradingBot INSTANCE = new MarginTradingBot();
	
	private MarginTradingBot() { }
	
	public static MarginTradingBot getInstance() {
		return INSTANCE;
	}
}
