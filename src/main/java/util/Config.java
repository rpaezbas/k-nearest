package util;

public enum Config {
	
	DATASETS_PATH("C:/users/rpaezbas/datasets/");
	
	private final String value;
	
	Config(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
