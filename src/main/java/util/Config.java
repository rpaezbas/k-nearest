package util;

public enum Config {
	
	DATASETS_PATH("C:/users/rpaezbas/datasets/");
	
	String value;
	
	Config(String value) {
		value = this.value;
	}
	
	public String getValue() {
		return this.value;
	}
}
