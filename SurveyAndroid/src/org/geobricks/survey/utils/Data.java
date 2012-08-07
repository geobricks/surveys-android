package org.geobricks.survey.utils;

public class Data {
	
	String code;
	
	String label;
	
	public Data(String code, String label){
		this.code = code;
		this.label = label;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
    public String toString() {
        return this.label;
    }

}
