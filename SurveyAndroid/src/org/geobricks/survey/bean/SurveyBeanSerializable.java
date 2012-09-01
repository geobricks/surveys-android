package org.geobricks.survey.bean;

import java.io.Serializable;


public class SurveyBeanSerializable implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	String id;
	String name;	
	String survey_abstract;
	
	String date_creation;
	
	String date_last_update;
	

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

}
