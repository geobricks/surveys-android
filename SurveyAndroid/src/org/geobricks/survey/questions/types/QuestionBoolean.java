package org.geobricks.survey.questions.types;


import org.geobricks.survey.R;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class QuestionBoolean extends QuestionValue {
	
	RadioGroup value;
	
    LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
 	
 	public QuestionBoolean(Context context) {
 		this.context = context;
 	}
 	
 	public LinearLayout build() {
 		panel = new LinearLayout(context);
 		panel = new LinearLayout(context);
		llp.setMargins(5, 15, 5, 5);
		panel.setLayoutParams(llp);
 	 	value = new RadioGroup(context);
// 	 	value.setOrientation(RadioGroup.VERTICAL);
 	 	
 	 	
 	 	RadioButton yes = new RadioButton(context);
 	 	yes.setText("YES");
 	 	
 	 	
 	 	RadioButton no = new RadioButton(context);
 	 	no.setText(R.string.no);
 	 	
 	 	value.addView(yes);
 	 	value.addView(no);

 	 	

 		panel.addView(value, llp);
 		return panel;
 	}

	public RadioGroup getValue() {
		return value;
	}
}
