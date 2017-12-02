package bot.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BotProperties {

	private static final String PROP_RUNNING_MODE = "runningMode";
	private static final String PROP_COLLECT_DATA_FROM = "collectDataFrom";
	private static final String PROP_COLLECT_DATA_TO = "collectDataTo";
	private static final String PROP_RESULTFILE = "resultFile";
	
	public static final RunningModeEnum RUNNING_MODE;
	public static final Date COLLECT_DATA_FROM;
	public static final Date COLLECT_DATA_TO;
	public static final String RESULTFILE;
	
	private static final SimpleDateFormat DATE_FORMAT =  new SimpleDateFormat("yyyy.MM.dd");
	
	static {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);
			RUNNING_MODE = RunningModeEnum.getFor(prop.getProperty(PROP_RUNNING_MODE));
			COLLECT_DATA_FROM = DATE_FORMAT.parse(prop.getProperty(PROP_COLLECT_DATA_FROM));
			COLLECT_DATA_TO = DATE_FORMAT.parse(prop.getProperty(PROP_COLLECT_DATA_TO));
			RESULTFILE = prop.getProperty(PROP_RESULTFILE);

		} catch (IOException | ParseException e) {
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
