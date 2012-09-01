package org.geobricks.survey.utils;

import java.io.Serializable;

public class Data  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String code;

	String label;

	boolean checked = false;
	
	public Data() {
	}

	public Data(String code, String label) {
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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public void toggleChecked() {
		checked = !checked;
	}

	@Override
	public String toString() {
		return this.label;
	}

}
