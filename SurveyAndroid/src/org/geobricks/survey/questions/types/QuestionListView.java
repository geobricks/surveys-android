package org.geobricks.survey.questions.types;

import java.text.ChoiceFormat;
import java.util.List;

import org.geobricks.survey.utils.Data;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class QuestionListView extends QuestionValue {
	
	int listview_layout = ListView.CHOICE_MODE_MULTIPLE;

 	ListView value;
 	
    LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
 	
 	public QuestionListView(Context context) {
 		this.context = context;
 	}
 	
 	public LinearLayout build(Integer type, List<Data> values) {
 		panel = new LinearLayout(context);
		llp.setMargins(5, 5, 5, 5);
		panel.setLayoutParams(llp);
 	 	value = new ListView(context);
// 	 	value.setBackgroundResource(R.drawable.white);
// 	 	value.setDivider( new ColorDrawable(this.getResources().getColor(R.drawable.dividercolor)) );
 	 	value.setDividerHeight(1);
 	 	value.setCacheColorHint(0);
// 	 	value.setChoiceMode(ChoiceFormat.)
 	 	
 	 	ArrayAdapter<Data> adapter = new ArrayAdapter<Data>(context, android.R.layout.select_dialog_multichoice, values);

 	 	
 	 	LinearLayout.LayoutParams llp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

 	 		value.setLayoutParams(llp2);
 	 		// Assign adapter to ListView
 	 		value.setAdapter(adapter); 
// 	    ArrayAdapter adapter = new ArrayAdapter(context, type, values);
// 	   value.setAdapter(adapter);
 		panel.addView(value, llp2);
 		return panel;
 	}

	public ListView getValue() {
		return value;
	}
 	
 	

}
