package org.geobricks.survey.bean;

import java.io.Serializable;

import org.geobricks.survey.constants.ANSWERTYPE;

public class AnswerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	// TODO: Memory issue?
	QuestionBean qb;
	
	ChoicesBean answerChoicesBean = new ChoicesBean();
	
	// to show in the interface
	String textResult;
	
	// to pass to JSON
	String valueResult;
	
	boolean answered = false;




	public String getTextResult() {
		return textResult;
	}

	public void setTextResult(String textResult) {
		this.textResult = textResult;
	}

	public String getValueResult() {
		return valueResult;
	}

	public void setValueResult(String valueResult) {
		this.valueResult = valueResult;
	}

	public ChoicesBean getAnswerChoicesBean() {
		return answerChoicesBean;
	}

	public void setAnswerChoicesBean(ChoicesBean answerChoicesBean) {
		this.answerChoicesBean = answerChoicesBean;
	}

	public QuestionBean getQuestionBean() {
		return qb;
	}

	public void setQuestionBean(QuestionBean qb) {
		this.qb = qb;
	}

	public boolean isAnswered() {
		return answered;
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
	}


	
}
