package org.geobricks.survey.questions.types;

import java.util.List;

import org.geobricks.survey.utils.Data;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class QuestionSpinner extends QuestionValue {
	
 	int spinner_layout = android.R.layout.simple_spinner_item;

 	Spinner spinner;
 	
    LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
 	
 	public QuestionSpinner(Context context) {
 		this.context = context;
 	}
 	
 	public LinearLayout build(Integer type, List<Data> values) {
 		panel = new LinearLayout(context);
		llp.setMargins(5, 15, 5, 5);
		panel.setLayoutParams(llp);
 	 	spinner = new Spinner(context);
 	 	if ( type == null )
 	 		type = spinner_layout;
 	    ArrayAdapter adapter = new ArrayAdapter(context, type, values);
 		spinner.setAdapter(adapter);
 		panel.addView(spinner, llp);
 		return panel;
 	}

	public Spinner getSpinner() {
		return spinner;
	}
 	
 	

}
