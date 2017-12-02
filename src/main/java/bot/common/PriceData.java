package bot.common;

import java.util.Date;

public class PriceData {

	private final Date date;
	
	private final double price;

	public PriceData(Date date, double price) {
		this.date = date;
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public double getPrice() {
		return price;
	}
}
