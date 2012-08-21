package org.geobricks.survey.questions;



import java.util.Map;

import android.content.Context;
import android.widget.LinearLayout;

import org.geobricks.survey.questions.Question;

public class Survey {
	
	LinearLayout panel;
	
	Map<String, Question> questions;
	
	Context context;
	
	public Survey(Context context) {
		this.context = context;	
	}

}
