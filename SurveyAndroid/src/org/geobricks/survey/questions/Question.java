package org.geobricks.survey.questions;

import java.util.UUID;

import org.geobricks.survey.R;
import org.geobricks.survey.bean.QuestionBean;
import org.geobricks.survey.constants.QUESTIONTYPE;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

public class Question extends Fragment {
	
	QuestionBean qb;
	
	ScrollView scrollView;
	
	ScrollView s;
	
	LinearLayout panel;
	
 	int spinner_layout = android.R.layout.simple_spinner_item;

	
	public static Question newInstance(QuestionBean bean) {
		Question f = new Question();
		Log.i("newInstance", "newInstance");
		f.setQuestionBean(bean);
        return f;
	}
	
	public void setQuestionBean(QuestionBean bean) {
       this.qb = bean;
    }
	
	public QuestionBean getQuestionBean() {
		return qb;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.i("onResume", "onResume");
//		createUI();
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("onCreate", "ok");
//		createUI();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.i("onpause", "pause");
//		createUI();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		QuestionBean qb = getQuestionBean();
		String text = qb.getNumber() + ") " + qb.getText();

		Log.i("onCreateView", String.valueOf(getQuestionBean().getText()));

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

		scrollView = new ScrollView(getActivity());
		int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getActivity().getResources().getDisplayMetrics());
		scrollView.setPadding(padding, padding, padding, padding);
		LinearLayout panel = new LinearLayout(getActivity());
		scrollView.addView(panel);
		panel.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);
		llp.setMargins(5, 5, 5, 5);
		// scrollView.setLayoutParams(llp);

		panel.setBackgroundColor(Color.WHITE);
		scrollView.setBackgroundColor(Color.WHITE);

		TextView title = new TextView(getActivity());
		title.setPadding(padding, padding, padding, padding);
		panel.addView(title);
		title.setText(text);
		
		TextView uuid = new TextView(getActivity());
		uuid.setPadding(padding, padding, padding, padding);
		panel.addView(uuid);
		uuid.setText(String.valueOf(UUID.randomUUID()));


		panel.addView(createForm(qb));
		// container.removeAllViews();

		// return inflater.inflate(R.layout.question, container, false);
		return scrollView;
	}

	private LinearLayout createForm(QuestionBean qb) {
		Log.i("QUESTIONTYPE", String.valueOf(qb.getQuestionType()));

		LinearLayout panel = new LinearLayout(getActivity());
		panel.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);
		llp.setMargins(5, 5, 5, 5);
		switch (qb.getQuestionType()) {
		case SINGLE_VALUE_NUMBER:
			EditText editText = new EditText(getActivity());
			panel.addView(editText);
			break;
		case SINGLE_VALUE_TEXT:
			EditText editText2 = new EditText(getActivity());
			panel.addView(editText2);
			break;
		case SINGLE_CHOICE:
			Spinner spinner = new Spinner(getActivity());
			ArrayAdapter adapter = new ArrayAdapter(getActivity(), spinner_layout, qb.getQuestionChoices().getChoices());
			spinner.setAdapter(adapter);
			panel.addView(spinner);
			break;

		default:
			break;
		}
		return panel;
	}
	

	// public void build() {
	//
	// View text =
	// this.container.addView(child);
	// }
	// //
	// public LinearLayout getPanel() {
	// return panel;
	// }
	//
	// public void setPanel(LinearLayout panel) {
	// this.panel = panel;
	// }

}
