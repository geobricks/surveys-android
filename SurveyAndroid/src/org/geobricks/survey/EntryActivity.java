package org.geobricks.survey;

import org.geobricks.survey.structure.QuestionBean;
import org.geobricks.survey.structure.SurveyBean;
import org.geobricks.survey.utils.ParserUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class EntryActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
		SurveyBean surveyBean = ParserUtils.parseJSON(this, R.raw.survey);

		ScrollView scrollView = new ScrollView(this);
		LinearLayout panel = new LinearLayout(this);
		panel.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
		llp.setMargins(5, 5, 5, 5);

		TextView t = new TextView(this);
		t.setText(surveyBean.getId());
		panel.addView(t, llp);

		t = new TextView(this);
		t.setText(surveyBean.getName());
		panel.addView(t, llp);

		t = new TextView(this);
		t.setText(surveyBean.getSurvey_abstract());
		panel.addView(t, llp);
		
		 Log.i("JSON", String.valueOf(surveyBean.getQuestions().size()));

		for(QuestionBean question : surveyBean.getQuestions()) {
			TextView q = new TextView(this);
			q.setText(question.getNumber() + " | " + question.getText() + " | " + question.getQuestionType());
			panel.addView(q, llp);
		}

		scrollView.addView(panel);
		setContentView(scrollView);

    }
}