package bot.exchange;

import java.util.Date;

import bot.common.Position;

public interface ExchangeI {

	public double getHistoricalPrice(final Date date);
	
	public boolean isOpenPosition();
	
	public Position getOpenPosition();
	
	public void closePosition();
	
	public void openPosition(final double percentage);
}
