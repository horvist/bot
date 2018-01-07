package bot.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import bot.exchange.ExchangeType;

public class BotProperties {

	private static final String PROP_EXCHANGE = "exchange";
	private static final String PROP_BOT_TYPE = "botType";
	private static final String PROP_CURRENCY1 = "currency1";
	private static final String PROP_CURRENCY2 = "currency2";
	private static final String PROP_COLLECT_DATA_FROM = "simulation.simulateFrom";
	private static final String PROP_COLLECT_DATA_TO = "simulation.simulateTo";
	private static final String PROP_RESULTFOLDER = "simulation.resultFolder";
	private static final String PROP_SIMULATION_EXCHANGE = "simulation.exchange";
	private static final String PROP_SIMULATION_STARTING_AMOUNT = "simulation.startingAmount";
	private static final String PROP_SIMULATION_MARGIN_STARTINGFEE = "simulation.margin.startingFee";
	private static final String PROP_SIMULATION_MARGIN_ROLLOVERFEE = "simulation.margin.rolloverFee";
	private static final String PROP_SIMULATION_MARGIN_TIME_PERIOD_OF_ROLLOVER_FEE = "simulation.margin.timePeriodOfRolloverFee";

	
	private static final String PROP_MARGIN_CHECKMINUTES = "margin.checkMinutes";
	private static final String PROP_MARGIN_OPEN_RAISE_PERCENTAGE = "margin.open.raisePercentage";
	private static final String PROP_MARGIN_OPEN_DECREASE_PERCENTAGE = "margin.open.decreasePercentage";
	private static final String PROP_MARGIN_CLOSE_RAISE_PERCENTAGE = "margin.close.raisePercentage";
	private static final String PROP_MARGIN_CLOSE_DESCREASE_PERCENTAGE = "margin.close.decreasePercentage";
	private static final String PROP_MARGIN_MANAGE_PERCENTAGE = "margin.managePercentage";
	
	private static final String PROP_MARGIN_SIMULATION_OPEN_RAISE_PERCENTAGE_START = "margin.simulation.open.raisePercentage.start";
	private static final String PROP_MARGIN_SIMULATION_OPEN_RAISE_PERCENTAGE_END = "margin.simulation.open.raisePercentage.end";
	private static final String PROP_MARGIN_SIMULATION_OPEN_DECREASE_PERCENTAGE_START = "margin.simulation.open.decreasePercentage.start";
	private static final String PROP_MARGIN_SIMULATION_OPEN_DECREASE_PERCENTAGE_END = "margin.simulation.open.decreasePercentage.end";
	private static final String PROP_MARGIN_SIMULATION_CLOSE_RAISE_PERCENTAGE_START = "margin.simulation.close.raisePercentage.start";
	private static final String PROP_MARGIN_SIMULATION_CLOSE_RAISE_PERCENTAGE_END = "margin.simulation.close.raisePercentage.end";
	private static final String PROP_MARGIN_SIMULATION_CLOSE_DECREASE_PERCENTAGE_START = "margin.simulation.close.decreasePercentage.start";
	private static final String PROP_MARGIN_SIMULATION_CLOSE_DECREASE_PERCENTAGE_END = "margin.simulation.close.decreasePercentage.end";
	
	public static final ExchangeType EXCHANGE;
	public static final BotType BOT_TYPE;
	public static final String CURRENCY1;
	public static final String CURRENCY2;
	public static final Date SIMULATION_FROM_DATE;
	public static final Date SIMULATION_TO_DATE;
	public static final String SIMULATION_RESULT_FOLDER;
	public static final ExchangeType SIMULATION_EXCHANGE;
	public static final double SIMULATION_STARTING_AMOUNT;
	public static final double SIMULATION_MARGIN_STARTINGFEE;
	public static final double SIMULATION_MARGIN_ROLLOVERFEE;
	public static final long SIMULATION_MARGIN_TIME_PERIOD_OF_ROLLOVER_FEE;

	
	public static final int MARGIN_CHECKMINUTES;
	public static final double MARGIN_OPEN_RAISE_PERCENTAGE;
	public static final double MARGIN_OPEN_DECREASE_PERCENTAGE;
	public static final double MARGIN_CLOSE_RAISE_PERCENTAGE;
	public static final double MARGIN_CLOSE_DESCREASE_PERCENTAGE;
	public static final double MARGIN_MANAGE_PERCENTAGE;
	
	public static final double MARGIN_SIMULATION_OPEN_RAISE_PERCENTAGE_START;
	public static final double MARGIN_SIMULATION_OPEN_RAISE_PERCENTAGE_END;
	public static final double MARGIN_SIMULATION_OPEN_DECREASE_PERCENTAGE_START;
	public static final double MARGIN_SIMULATION_OPEN_DECREASE_PERCENTAGE_END;
	public static final double MARGIN_SIMULATION_CLOSE_RAISE_PERCENTAGE_START;
	public static final double MARGIN_SIMULATION_CLOSE_RAISE_PERCENTAGE_END;
	public static final double MARGIN_SIMULATION_CLOSE_DECREASE_PERCENTAGE_START;
	public static final double MARGIN_SIMULATION_CLOSE_DECREASE_PERCENTAGE_END;
	
	public static final SimpleDateFormat DATE_FORMAT =  new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat DATE_FORMAT_GMT =  new SimpleDateFormat("yyyyMMdd");
	
	static {
		Properties prop = new Properties();
		InputStream input = null;
		
		DATE_FORMAT_GMT.setTimeZone(TimeZone.getTimeZone("GMT"));

		try {

			input = new FileInputStream("src/main/resources/config.properties");

			// load a properties file
			prop.load(input);
			EXCHANGE = ExchangeType.getFor(prop.getProperty(PROP_EXCHANGE));
			SIMULATION_EXCHANGE = ExchangeType.getFor(prop.getProperty(PROP_SIMULATION_EXCHANGE));
			BOT_TYPE = BotType.getFor(prop.getProperty(PROP_BOT_TYPE));
			CURRENCY1 = prop.getProperty(PROP_CURRENCY1);
			CURRENCY2 = prop.getProperty(PROP_CURRENCY2);
			SIMULATION_FROM_DATE = DATE_FORMAT.parse(prop.getProperty(PROP_COLLECT_DATA_FROM));
			SIMULATION_TO_DATE = DATE_FORMAT.parse(prop.getProperty(PROP_COLLECT_DATA_TO));
			SIMULATION_RESULT_FOLDER = prop.getProperty(PROP_RESULTFOLDER);
			SIMULATION_STARTING_AMOUNT = Double.parseDouble(prop.getProperty(PROP_SIMULATION_STARTING_AMOUNT));
			SIMULATION_MARGIN_STARTINGFEE = Double.parseDouble(prop.getProperty(PROP_SIMULATION_MARGIN_STARTINGFEE)) / 100.0;
			SIMULATION_MARGIN_ROLLOVERFEE = Double.parseDouble(prop.getProperty(PROP_SIMULATION_MARGIN_ROLLOVERFEE)) / 100.0;
			SIMULATION_MARGIN_TIME_PERIOD_OF_ROLLOVER_FEE = Long.parseLong(prop.getProperty(PROP_SIMULATION_MARGIN_TIME_PERIOD_OF_ROLLOVER_FEE));
			
			MARGIN_CHECKMINUTES = Integer.parseInt(prop.getProperty(PROP_MARGIN_CHECKMINUTES));
			MARGIN_OPEN_RAISE_PERCENTAGE = Integer.parseInt(prop.getProperty(PROP_MARGIN_OPEN_RAISE_PERCENTAGE)) / 100.0;
			MARGIN_OPEN_DECREASE_PERCENTAGE = Integer.parseInt(prop.getProperty(PROP_MARGIN_OPEN_DECREASE_PERCENTAGE)) / 100.0;
			MARGIN_CLOSE_RAISE_PERCENTAGE = Integer.parseInt(prop.getProperty(PROP_MARGIN_CLOSE_RAISE_PERCENTAGE)) / 100.0;
			MARGIN_CLOSE_DESCREASE_PERCENTAGE = Integer.parseInt(prop.getProperty(PROP_MARGIN_CLOSE_DESCREASE_PERCENTAGE)) / 100.0;
			MARGIN_MANAGE_PERCENTAGE = Integer.parseInt(prop.getProperty(PROP_MARGIN_MANAGE_PERCENTAGE)) / 100.0;
			
			MARGIN_SIMULATION_OPEN_RAISE_PERCENTAGE_START = Integer.parseInt(prop.getProperty(PROP_MARGIN_SIMULATION_OPEN_RAISE_PERCENTAGE_START)) / 100.0;
			MARGIN_SIMULATION_OPEN_RAISE_PERCENTAGE_END = Integer.parseInt(prop.getProperty(PROP_MARGIN_SIMULATION_OPEN_RAISE_PERCENTAGE_END)) / 100.0;
			MARGIN_SIMULATION_OPEN_DECREASE_PERCENTAGE_START = Integer.parseInt(prop.getProperty(PROP_MARGIN_SIMULATION_OPEN_DECREASE_PERCENTAGE_START)) / 100.0;
			MARGIN_SIMULATION_OPEN_DECREASE_PERCENTAGE_END = Integer.parseInt(prop.getProperty(PROP_MARGIN_SIMULATION_OPEN_DECREASE_PERCENTAGE_END)) / 100.0;
			MARGIN_SIMULATION_CLOSE_RAISE_PERCENTAGE_START = Integer.parseInt(prop.getProperty(PROP_MARGIN_SIMULATION_CLOSE_RAISE_PERCENTAGE_START)) / 100.0;
			MARGIN_SIMULATION_CLOSE_RAISE_PERCENTAGE_END = Integer.parseInt(prop.getProperty(PROP_MARGIN_SIMULATION_CLOSE_RAISE_PERCENTAGE_END)) / 100.0;
			MARGIN_SIMULATION_CLOSE_DECREASE_PERCENTAGE_START = Integer.parseInt(prop.getProperty(PROP_MARGIN_SIMULATION_CLOSE_DECREASE_PERCENTAGE_START)) / 100.0;
			MARGIN_SIMULATION_CLOSE_DECREASE_PERCENTAGE_END = Integer.parseInt(prop.getProperty(PROP_MARGIN_SIMULATION_CLOSE_DECREASE_PERCENTAGE_END)) / 100.0;

		} catch (Exception e) {
			throw new IllegalStateException("Could not load properties", e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					throw new IllegalStateException("Could not close properties file", e);
				}
			}
		}
	}
}
