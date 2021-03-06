package org.geobricks.survey.bean;

import java.io.Serializable;
import java.util.List;

import org.geobricks.survey.constants.SURVEYTYPE;

public class SurveyBean implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SURVEYTYPE surveyType;
	
	String id;
	String name;	
	String survey_abstract;
	
	String date_creation;
	
	String date_last_update;
	
	List<QuestionBean> questions;

	public SURVEYTYPE getSurveyType() {
		return surveyType;
	}

	public void setSurveyType(SURVEYTYPE surveyType) {
		this.surveyType = surveyType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurvey_abstract() {
		return survey_abstract;
	}

	public void setSurvey_abstract(String survey_abstract) {
		this.survey_abstract = survey_abstract;
	}

	public List<QuestionBean> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionBean> questions) {
		this.questions = questions;
	}

}
