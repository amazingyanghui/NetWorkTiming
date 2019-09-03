package com.temptation.pojo;

public class Parameter {
	private Long id;
	private String keyName;
	//模式
	private String pattern;
	private String parameterValue;
	private String defaultValue;
	//秒速
	private String describel;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getDescribel() {
		return describel;
	}
	public void setDescribel(String describel) {
		this.describel = describel;
	}
	@Override
	public String toString() {
		return "Parameter [id=" + id + ", keyName=" + keyName + ", pattern=" + pattern + ", parameterValue="
				+ parameterValue + ", defaultValue=" + defaultValue + ", describel=" + describel + "]";
	}
	
	
	
	
}
