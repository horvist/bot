package bot.common;

import bot.exchange.ExchangeI;
import bot.exchange.ExchangeType;
import bot.exchange.cex.Cex;
import bot.exchange.simulation.ExchangeSimulation;
import bot.margin.MarginTradingBot;

public class BotFactory {
	
	private static final BotFactory INSTANCE = new BotFactory();
	
	private BotFactory() { }

	public static BotFactory getInstance() {
		return INSTANCE;
	}

	public ExchangeI getDefaultExchange() {
		return getExchange(BotProperties.EXCHANGE);
	}
	
	public ExchangeI getExchange(ExchangeType type)  {
		if (type == ExchangeType.SIMULATION) {
			return ExchangeSimulation.getInstance();
		}
		
		if (type == ExchangeType.CEX) {
			return Cex.getInstance();
		}
		
		throw new IllegalStateException("Unknown type: " + type);
	}
	
	public BotI getDefaultBot() {
		return getBot(BotProperties.BOT_TYPE);
	}
	
	public BotI getBot(BotType type) {
		if (type == BotType.MARGIN) {
			return MarginTradingBot.getInstance();
		}
		
		throw new IllegalStateException("Unknown type: " + type);
	}
}
