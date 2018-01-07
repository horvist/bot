package bot.main;

import java.text.ParseException;
import java.util.Date;

import bot.common.BotProperties;
import bot.exchange.cex.Cex;

public class Main {
	
	private final static long ONE_MINUTE_IN_MILLIS = 60000;

	public static void main(String[] args) throws ParseException {
		Date date1 = BotProperties.DATE_FORMAT_GMT.parse("20180104");
		Date date2 = new Date(date1.getTime() + ONE_MINUTE_IN_MILLIS);
		Date date3 = new Date(date2.getTime() + ONE_MINUTE_IN_MILLIS);
		Date date4 = new Date(date3.getTime() + ONE_MINUTE_IN_MILLIS);
		System.out.println(Cex.getInstance().getHistoricalPrice(date1));
		System.out.println(Cex.getInstance().getHistoricalPrice(date2));
		System.out.println(Cex.getInstance().getHistoricalPrice(date3));
		System.out.println(Cex.getInstance().getHistoricalPrice(date4));


	}

}
