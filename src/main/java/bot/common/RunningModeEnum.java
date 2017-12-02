package bot.common;

public enum RunningModeEnum {

	COLLECT_DATA("1"),
	RUN_BOT("2");
	
	private final String codeKey;
	
	private RunningModeEnum(final String codeKey) {
		this.codeKey = codeKey;
	}

	public String getCodeKey() {
		return codeKey;
	}
	
	public static RunningModeEnum getFor(final String codeKey) {
		for (RunningModeEnum e : values()) {
			if (e.getCodeKey().equals(codeKey)) {
				return e;
			}
		}
		
		throw new IllegalArgumentException("No value found for codeKey: " + codeKey);
	}
}
