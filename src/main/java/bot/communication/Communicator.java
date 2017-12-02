package bot.communication;

import java.util.Date;
import java.util.List;

import bot.common.PriceData;

public interface Communicator {

	public List<PriceData> getHistoricalPrices(final Date date);
	
	// https://www.javacodegeeks.com/2012/09/simple-rest-client-in-java.html
}
