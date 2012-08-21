package org.geobricks.survey.questions.types;

import java.util.List;

import org.geobricks.survey.utils.Data;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class QuestionEditText extends QuestionValue {
	
	EditText value;
	
    LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
 	
 	public QuestionEditText(Context context) {
 		this.context = context;
 	}
 	
 	public LinearLayout build(Integer type) {
 		panel = new LinearLayout(context);
 		panel = new LinearLayout(context);
		llp.setMargins(5, 15, 5, 5);
		panel.setLayoutParams(llp);
 	 	value = new EditText(context);
 	 	if ( type != null )
 	 		value.setInputType(type);
 		panel.addView(value, llp);
 		return panel;
 	}

	public EditText getValue() {
		return value;
	}
}
