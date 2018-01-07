package bot.common;

import java.util.Date;

public class Position {

	private final long id;
	private final double openPrice;
	private final Date openDate;
	private final double fee;
	private final double amount;
	
	public Position(long id, double openPrice, Date openDate, double fee, double amount) {
		this.id = id;
		this.openPrice = openPrice;
		this.openDate = openDate;
		this.fee = fee;
		this.amount = amount;
	}
	
	public final long getId() {
		return id;
	}
	public final double getOpenPrice() {
		return openPrice;
	}
	public final Date getOpenDate() {
		return openDate;
	}
	public final double getFee() {
		return fee;
	}

	public final double getAmount() {
		return amount;
	}
	
	public String toString() {
		return "Position id: " + id + ", openPrice: " + openPrice + ", openDate: " + openDate + ", fee: " + fee + ", amount: " + amount;
	}
}
