package bot.exchange.simulation;

import java.util.Date;

import bot.common.BotFactory;
import bot.common.BotProperties;
import bot.common.LogUtil;
import bot.common.Position;
import bot.exchange.ExchangeI;

public class ExchangeSimulation implements ExchangeI {
	
	private static final ExchangeSimulation INSTANCE = new ExchangeSimulation();
	
	private ExchangeSimulation() { }
	
	public static ExchangeSimulation getInstance() {
		return INSTANCE;
	}
	
	private static final double INCREASE_POSITION_STARTING_AMOUNT_BY = 1.006;
	
	private static double cryptoAmount = BotProperties.SIMULATION_STARTING_AMOUNT;
	private static double rolloverFee = BotProperties.SIMULATION_MARGIN_ROLLOVERFEE;
	
	private Position currentPos = null;
	
	private double currentPrice;
	private Date currentDate;
	private Date lastFeeRemoved;
	
	@Override
	public double getHistoricalPrice(Date date) {
		currentDate = date;
		currentPrice = BotFactory.getInstance().getExchange(BotProperties.SIMULATION_EXCHANGE).getHistoricalPrice(date);
		
		if (isOpenPosition() && (currentDate.getTime() - lastFeeRemoved.getTime()) >= BotProperties.SIMULATION_MARGIN_TIME_PERIOD_OF_ROLLOVER_FEE) {
			double fee = currentPos.getAmount() * rolloverFee;
			if (fee > cryptoAmount) {
				LogUtil.log("WARNING: Position has been closed because of insufficient founds: " + currentPos);
				closePosition();
			} else {
				cryptoAmount -= fee;
				lastFeeRemoved = currentDate;
			}
		}
		
		return currentPrice;
	}

	@Override
	public boolean isOpenPosition() {
		return currentPos != null;
	}

	@Override
	public Position getOpenPosition() {
		return currentPos;
	}

	@Override
	public void closePosition() {
		if (!isOpenPosition()) {
			throw new IllegalStateException("There is no open position!");
		}
		
		double amount = currentPos.getAmount() * (currentPrice / currentPos.getOpenPrice()) - currentPos.getFee(); 
		
		cryptoAmount += amount;
		
		currentPos = null;
	}

	@Override
	public void openPosition(final double percentage) {
		if (isOpenPosition()) {
			throw new IllegalStateException("There is already an open position!");
		}
		
		final double posAmount = cryptoAmount * percentage;
		if (posAmount > cryptoAmount) {
			throw new IllegalStateException("Pos amount: " + posAmount + " is greater than all amount: " + cryptoAmount);
		}
		cryptoAmount -= posAmount;
		
		final double openPrice = currentPrice * INCREASE_POSITION_STARTING_AMOUNT_BY;
		
		final double fee = openPrice * BotProperties.SIMULATION_MARGIN_STARTINGFEE;
		
		currentPos = new Position(0, openPrice, currentDate, fee, posAmount);
		lastFeeRemoved = currentDate;
		
		LogUtil.log("Position opened: " + currentPos);
	}

}
