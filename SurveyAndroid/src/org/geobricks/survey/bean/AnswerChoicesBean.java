package org.geobricks.survey.bean;

import java.io.Serializable;
import java.util.List;

import org.geobricks.survey.utils.Data;

public class AnswerChoicesBean  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Data> choices;

	public List<Data> getChoices() {
		return choices;
	}

	public void setChoices(List<Data> choices) {
		this.choices = choices;
	}

	
}
