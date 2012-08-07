package org.geobricks.survey.structure;

import org.geobricks.survey.constants.QUESTIONTYPE;

public class QuestionBean {
	
	String number;
	
	String text;
	
	QUESTIONTYPE questionType;
	
	QuestionChoicesBean questionChoices;

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

	public QuestionChoicesBean getQuestionChoices() {
		return questionChoices;
	}

	public void setQuestionChoices(QuestionChoicesBean questionChoices) {
		this.questionChoices = questionChoices;
	}
	
	

}
