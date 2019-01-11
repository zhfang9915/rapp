package org.rapp.pojo.echart;

public class NameValue {

	private String name;

	private long value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public NameValue(String name, long value) {
		super();
		this.name = name;
		this.value = value;
	}

}
