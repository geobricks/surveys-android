package org.geobricks.survey.client;



import java.util.List;
import java.util.Map;

import android.content.Context;
import android.widget.LinearLayout;
import org.geobricks.survey.client.Question;

public class Survey {
	
	LinearLayout panel;
	
	Map<String, Question> questions;
	
	Context context;
	
	public Survey(Context context) {
		this.context = context;	
	}

}
