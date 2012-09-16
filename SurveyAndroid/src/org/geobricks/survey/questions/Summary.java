package org.geobricks.survey.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.geobricks.survey.R;
import org.geobricks.survey.bean.AnswerBean;
import org.geobricks.survey.bean.QuestionBean;
import org.geobricks.survey.bean.SurveyBean;
import org.geobricks.survey.questions.types.QuestionBoolean;
import org.geobricks.survey.questions.types.QuestionDate;
import org.geobricks.survey.questions.types.QuestionEditText;
import org.geobricks.survey.questions.types.QuestionMultiselection;
import org.geobricks.survey.questions.types.QuestionSpinner;
import org.geobricks.survey.questions.types.QuestionValue;
import org.geobricks.survey.summary.SummaryListPanel;
import org.geobricks.survey.utils.AnswerUtils;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Summary extends Question {
	
	
	public static Summary newInstance(QuestionBean bean) {
		Summary f = new Summary();
		Log.i("newInstance", "newInstance");
		f.setQuestionBean(bean);
        return f;
	}

	
	@Override
	public void onResume() {
		super.onResume();
		Log.i("SUMMARY", "onResume");

	}
	
	@Override
	public void onPause() {
		super.onResume();
		Log.i("SUMMARY", "onPause");
//		QuestionsPager qp = (QuestionsPager) getActivity();
//		qp.getmPagerAdapter().destroySummary(qp.getQuestionFragments().size() - 1, this);
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		detach = true;

		if (container == null) {
			// We have different layouts, and in one of them this
			// fragment's containing frame doesn't exist. The fragment
			// may still be created from its saved state, but there is
			// no reason to try to create its view hierarchy because it
			// won't be displayed. Note this is not needed -- we could
			// just run the code below, where we would create and return
			// the view hierarchy; it would just never be used.
			return null;
		}
	
		QuestionsPager qp = (QuestionsPager) getActivity();
		SurveyBean surveyBean = qp.getSurveyBean();
		List<AnswerBean> answerBeans = new ArrayList<AnswerBean>();
		
		// -2 because the latest two are the summeries fragments
//		for(int i = 0; i < qp.getQuestionFragments().size() -2; i++) {
//			try {
//				answerBeans.add(AnswerUtils.getAnswerBean(qp.getApplicationContext(), qp.getQuestionFragments().get(i)));
//			}catch(Exception e) {}
//		}

		for(int i = 0; i < surveyBean.getQuestions().size(); i++) {
			try {
				AnswerBean answerBean = new AnswerBean();
				answerBean.setQuestionBean(surveyBean.getQuestions().get(i));
				answerBeans.add(answerBean);
				AnswerUtils.getAnswerBean(qp.getApplicationContext(), answerBean, qp.getQuestionFragments().get(i));
			}catch(Exception e) {}
		}

		panel = new LinearLayout(getActivity());
		panel.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);
		llp.setMargins(5, 5, 5, 5);
		llp.weight = 1.0f;
		panel.setLayoutParams(llp);
		panel.setBackgroundColor(Color.WHITE);

		SummaryListPanel slp = new SummaryListPanel(getActivity());
		panel.addView(slp.build(surveyBean, answerBeans));
		
		return panel;
	}
	
	
	// WORKAROUND: google bux fix
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (outState.isEmpty()) {
			outState.putBoolean("bug:fix", true);
		}
	}
}
