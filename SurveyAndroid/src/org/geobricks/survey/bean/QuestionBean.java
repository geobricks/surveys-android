package org.geobricks.survey.bean;

import java.io.Serializable;

import org.geobricks.survey.constants.ANSWERTYPE;

public class QuestionBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String title;
	
	String number;
	
	String info;
	
	String text;
	
	ANSWERTYPE type;
	
	ChoicesBean choicesBean = new ChoicesBean();

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	

	
	public ANSWERTYPE getType() {
		return type;
	}

	public void setType(ANSWERTYPE type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public ChoicesBean getChoicesBean() {
		return choicesBean;
	}

	public void setChoicesBean(ChoicesBean choicesBean) {
		this.choicesBean = choicesBean;
	}
	
	
	
	
}
