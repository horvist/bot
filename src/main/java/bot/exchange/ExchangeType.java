package bot.exchange;

public enum ExchangeType {

	SIMULATION,
	CEX;
	
	public static ExchangeType getFor(final String name) {
		for (ExchangeType val : values()) {
			if (val.name().equalsIgnoreCase(name)) {
				return val;
			}
		}
		
		throw new IllegalStateException("Enum value not found for: " + name);
	}
}
