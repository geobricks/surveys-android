package org.geobricks.survey.bean;

import java.io.Serializable;

import org.geobricks.survey.constants.ANSWERTYPE;

public class AnswerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	// TODO: Memory issue?
	QuestionBean qb;
	
	ChoicesBean answerChoicesBean = new ChoicesBean();
	
	// free text date
	String result;
	
	boolean answered = false;


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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
