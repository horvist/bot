package bot.common;

public enum BotType {

	MARGIN;
	
	public static BotType getFor(final String name) {
		for (BotType val : values()) {
			if (val.name().equalsIgnoreCase(name)) {
				return val;
			}
		}
		
		throw new IllegalStateException("Enum value not found for: " + name);
	}
}
