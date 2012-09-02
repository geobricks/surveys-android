package org.geobricks.survey.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.geobricks.survey.R;
import org.geobricks.survey.bean.AnswerBean;
import org.geobricks.survey.bean.QuestionBean;
import org.geobricks.survey.questions.types.QuestionBoolean;
import org.geobricks.survey.questions.types.QuestionDate;
import org.geobricks.survey.questions.types.QuestionEditText;
import org.geobricks.survey.questions.types.QuestionMultiselection;
import org.geobricks.survey.questions.types.QuestionSpinner;
import org.geobricks.survey.questions.types.QuestionValue;
import org.geobricks.survey.summary.SummaryListPanel;

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
		List<AnswerBean> answerBeans = new ArrayList<AnswerBean>();
		for(Question q : qp.getQuestionFragments() ) {
			try {
				answerBeans.add(getAnswerBean(q));
//				QuestionEditText qe = (QuestionEditText) q.getQuestionValue();
//				Log.i("SUMMARY", q.getQuestionBean().getNumber() + " | "+ q.getQuestionBean().getText() + " | " + qe.getValue().getText());
			}catch(Exception e) {
				
			}
		}
		
		for(AnswerBean ab : answerBeans ) {
			try {
				Log.i("SUMMARY", ab.getQuestionBean().getNumber() + " | "+ ab.getQuestionBean().getText() + " | " + ab.getResult());
			}catch(Exception e) {
			}
		}

		panel = new LinearLayout(getActivity());
		panel.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);
		llp.setMargins(5, 5, 5, 5);
		llp.weight = 1.0f;
		panel.setLayoutParams(llp);
		panel.setBackgroundColor(Color.WHITE);
		LinearLayout h = new LinearLayout(getActivity());	

		SummaryListPanel slp = new SummaryListPanel(getActivity());
		panel.addView(slp.build(answerBeans));
		
		return panel;
	}
	
	private AnswerBean getAnswerBean(Question q) {
		AnswerBean answerBean = new AnswerBean();
		QuestionBean qb = q.getQuestionBean();
		answerBean.setQuestionBean(qb);
		
		Log.i("ANSWERTYPE", String.valueOf(qb.getType()));

		LinearLayout panel = new LinearLayout(getActivity());
		panel.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);
		llp.setMargins(5, 5, 5, 5);
		
		
		QuestionEditText qe;	
		if ( qb.getType() != null) {
			switch (qb.getType()) {
				case SINGLE_VALUE_NUMBER:
					qe = (QuestionEditText) q.getQuestionValue();
					answerBean.setResult(qe.getValue().getText().toString());
					answerBean.setAnswered(true);
					break;
				case SINGLE_VALUE_TEXT:
					qe = (QuestionEditText) q.getQuestionValue();
					answerBean.setResult(qe.getValue().getText().toString());
					answerBean.setAnswered(true);
					break;
				case FREE_TEXT:
					qe = (QuestionEditText) q.getQuestionValue();
					answerBean.setResult(qe.getValue().getText().toString());
					answerBean.setAnswered(true);
					break;
				case SINGLE_VALUE_DATE:
					QuestionDate qd = (QuestionDate) q.getQuestionValue();
					answerBean.setResult(String.valueOf(qd.getValue().getYear()));
					answerBean.setAnswered(true);
//					questionValue = new QuestionDate(getActivity());
//					((QuestionDate) questionValue).build();
					break;
				case SINGLE_VALUE_BOOLEAN:
//					questionValue = new QuestionBoolean(getActivity());
//					((QuestionBoolean) questionValue).build();
					break;
				case SINGLE_CHOICE:
//					questionValue = new QuestionSpinner(getActivity());
//					((QuestionSpinner) questionValue).build(null, qb.getChoicesBean().getChoices());
					break;
				case MULTIPLE_CHOICE:
//					questionValue = new QuestionMultiselection(getActivity());
//					((QuestionMultiselection) questionValue).build(qb.getChoicesBean().getChoices());
					break;
		
				default: break;
			}
			try {
//				panel.addView(questionValue.getPanel(), llp);
			}catch(Exception e ){}
		}
		return answerBean;
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
