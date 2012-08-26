package org.geobricks.survey.bean;

import org.geobricks.survey.constants.QUESTIONTYPE;

public class QuestionBean {
	
	String title;
	
	String number;
	
	String info;
	
	String text;
	
	QUESTIONTYPE questionType;
	
	AnswerChoicesBean answerChoicesBean = new AnswerChoicesBean();
	
	String result = "";

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

	public QUESTIONTYPE getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QUESTIONTYPE questionType) {
		this.questionType = questionType;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public AnswerChoicesBean getAnswerChoicesBean() {
		return answerChoicesBean;
	}

	public void setAnswerChoicesBean(AnswerChoicesBean answerChoicesBean) {
		this.answerChoicesBean = answerChoicesBean;
	}
	
	
	
	
}
