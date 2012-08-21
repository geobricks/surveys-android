package org.geobricks.survey.questions.types;

import java.util.List;

import org.geobricks.survey.utils.Data;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class QuestionDate extends QuestionValue {
	
	DatePicker value;
	
    LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
 	
 	public QuestionDate(Context context) {
 		this.context = context;
 	}
 	
 	public LinearLayout build() {
 		panel = new LinearLayout(context);
		llp.setMargins(5, 15, 5, 5);

 	 	value = new DatePicker(context);
 		panel.addView(value, llp);
 		return panel;
 	}

	public DatePicker getValue() {
		return value;
	}
}
