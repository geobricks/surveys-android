package org.geobricks.survey.questions;

import java.util.List;
import java.util.Vector;

import org.geobricks.survey.R;
import org.geobricks.survey.bean.QuestionBean;
import org.geobricks.survey.bean.SurveyBean;
import org.geobricks.survey.constants.CONSTANTS;
import org.geobricks.survey.utils.ParserUtils;
import org.geobricks.survey.utils.RESTUtils;
import org.geobricks.survey.utils.Utils;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;




public class QuestionsPager extends FragmentActivity {
	
		ViewPager pager;
		
		Button nextButton;
		
		Button backButton;


		
		EditText url;
		

		/** maintains the pager adapter*/
		private QuestionsPagerAdapter mPagerAdapter;
		/* (non-Javadoc)
		 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
		 */
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			// working
//			setContentView(R.layout.viewpager2);
			setContentView(R.layout.viewpager_withedittext);

			
//			setContentView(R.layout.viewpager3);
//			setContentView(R.layout.viewpager);
			//initialsie the pager
//			this.initialisePaging(json);
			
		    Button ok = (Button) findViewById(R.id.getjson);
		    ok.setOnClickListener(new GetJson());
//		    ok.setId(id)
		    
		    url = (EditText) findViewById(R.id.url);		    
		    url.setText(CONSTANTS.SURVEY_WEBSERVICE_URL + CONSTANTS.SURVEY_WEBSERVICE_SELECT_MODEL + "/");
		    
//			String json = Utils.readFile(this, R.raw.survey_new);
//			 ParserUtils.parseJSON(this, json, CONSTANTS.LOCALE);
			
		}
		
		   public class GetJson implements View.OnClickListener {
				public void onClick( View view ) {

					Log.i("GetJson", "getting the json");
//					Log.i("ChangePageHandler", view.getId());
					Log.i("GetJson", "json read");
//					((QuestionsPager) view.getContext()).initialisePaging(json);
					
					Log.i("GetJson", url.getText().toString());
//					String result = RESTUtils.getJsonById(view.getContext(), url.getText().toString());
//					((QuestionsPager) view.getContext()).initialisePaging(result);

					
					LinearLayout urlpanel = (LinearLayout) findViewById(R.id.urlpanel);
					urlpanel.removeAllViews();
					
					
					String json = Utils.readFile(view.getContext(), R.raw.survey_new);
					((QuestionsPager) view.getContext()).initialisePaging(json);
				}
			}

		
		/**
		 * Initialise the fragments to be paged
		 */
		private void initialisePaging(String json) {

			List<Question> fragments = new Vector<Question>();
			SurveyBean surveyBean = ParserUtils.parseJSON(this, json);
			
			int i = 1;
			for(QuestionBean question : surveyBean.getQuestions()) {
				question.setNumber(String.valueOf(i)); // TODO; it was a test
				fragments.add((Question) Question.newInstance(question));
				i++;
			}

			this.mPagerAdapter  = new QuestionsPagerAdapter(super.getSupportFragmentManager(), fragments);
			pager = (ViewPager) super.findViewById(R.id.viewpager);
			pager.setAdapter(this.mPagerAdapter);
			
			DisplayMetrics displaymetrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
			int height = displaymetrics.heightPixels;
			int wwidth = displaymetrics.widthPixels;
			
			Log.i("SIZE", String.valueOf(height));
			
			
			Toast.makeText(this, String.valueOf(height), Toast.LENGTH_LONG);

		    nextButton = (Button) findViewById(R.id.next);
		    nextButton.setId(R.string.next);
		    nextButton.setOnClickListener(new ChangePageHandler());
		    backButton = (Button) findViewById(R.id.back);
		    backButton.setOnClickListener(new ChangePageHandler());
		    backButton.setId(R.string.back);
		    
		    
		}
		
	    public class ChangePageHandler implements View.OnClickListener {
			public void onClick( View view ) {
				Log.i("ChangePageHandler", String.valueOf(view.getId()));

				int pos = pager.getCurrentItem();
				switch (view.getId()) {
					// TODO: test on current position, and hide back/next buttons
					case R.string.back: pos--; break;
					case R.string.next: pos++; break;
				}
				pager.setCurrentItem(pos, true);
			}
		}

	}


