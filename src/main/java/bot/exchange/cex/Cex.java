package bot.exchange.cex;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import bot.common.BotProperties;
import bot.common.Position;
import bot.exchange.ExchangeI;

public class Cex implements ExchangeI {

	private static final Cex INSTANCE = new Cex();
	
	final Map<Long, Double> HIST_PRICES = new HashMap<>();
	
	final Gson gson = new Gson();
	
	JsonParser parser = new JsonParser();
	
	private Cex() { }

	public static Cex getInstance() {
		return INSTANCE;
	}

	@Override
	public double getHistoricalPrice(Date date) {
		final long dateInSec = date.getTime() / 1000;
		if (HIST_PRICES.containsKey(dateInSec)) {
			return HIST_PRICES.get(dateInSec);
		}
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String url = "https://cex.io/api/ohlcv/hd/" + BotProperties.DATE_FORMAT.format(date) + "/" + BotProperties.CURRENCY1 + "/" + BotProperties.CURRENCY2;
		HttpGet httpGet = new HttpGet(url);
		try {
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				HttpEntity entity = response.getEntity();
				String content = IOUtils.toString(entity.getContent());
								
				JsonObject json = parser.parse(content).getAsJsonObject();
				JsonArray data1m = parser.parse(json.get("data1m").getAsString()).getAsJsonArray();
				
				for (int i = 0; i < data1m.size(); i++) {
					JsonArray priceData = data1m.get(i).getAsJsonArray();
					final long timestamp = priceData.get(0).getAsLong();
					final double price = priceData.get(1).getAsDouble();
					HIST_PRICES.put(timestamp, price);
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			throw new IllegalStateException("Could not load historical prices for date: " + date, e);
		}

		return HIST_PRICES.get(dateInSec);
	}

	@Override
	public boolean isOpenPosition() {
		throw new UnsupportedOperationException("This method is not yet implemented");
	}

	@Override
	public Position getOpenPosition() {
		throw new UnsupportedOperationException("This method is not yet implemented");
	}

	@Override
	public void closePosition() {
		throw new UnsupportedOperationException("This method is not yet implemented");
	}

	@Override
	public void openPosition(final double percentage) {
		throw new UnsupportedOperationException("This method is not yet implemented");
	}

}
