package org.geobricks.survey.questions;

import java.util.List;
import java.util.Vector;

import org.geobricks.survey.R;
import org.geobricks.survey.bean.QuestionBean;
import org.geobricks.survey.bean.SurveyBean;
import org.geobricks.survey.constants.LOG;
import org.geobricks.survey.utils.Utils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;




public class QuestionsPager extends FragmentActivity {
	
		ViewPager pager;
		
		List<Question> questionFragments;
		
		Button nextButton;
		
		Button backButton;
		
//		ImageView backButton;
		
		Button summaryButton;

		SurveyBean surveyBean;
		
		EditText url;
		
		/** maintains the pager adapter*/
		private QuestionsPagerAdapter mPagerAdapter;
		
		public static QuestionsPager newInstance(SurveyBean bean) {
			QuestionsPager f = new QuestionsPager();
			f.setSurveyBean(bean);
	        return f;
		}

		public SurveyBean getSurveyBean() {
			return surveyBean;
		}



		public void setSurveyBean(SurveyBean surveyBean) {
			this.surveyBean = surveyBean;
		}

		@Override
		public void onResume() {
			super.onResume();
			Log.i(LOG.PAGER.toString(), "onResume");
		}
		
		@Override
		public void onPause() {
			super.onResume();
			Log.i(LOG.PAGER.toString(), "onPause");
		}

		/* (non-Javadoc)
		 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
		 */
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
//			SurveyBeanSerializable t = (SurveyBeanSerializable) getIntent().getExtras().getSerializable("bean");
			surveyBean = (SurveyBean) getIntent().getExtras().getSerializable("bean");
			Log.i("t", surveyBean.getName());
			
			// working
			setContentView(R.layout.viewpager2);
			
		    initialisePaging();

//			setContentView(R.layout.viewpager_withedittext);

			
//			setContentView(R.layout.viewpager3);
//			setContentView(R.layout.viewpager);
			//initialsie the pager
//			this.initialisePaging(json);
			
//		    Button ok = (Button) findViewById(R.id.getjson);
//		    ok.setOnClickListener(new GetJson());
//		    ok.setId(id)
		    
//		    url = (EditText) findViewById(R.id.url);		    
//		    url.setText(CONSTANTS.SURVEY_WEBSERVICE_URL + CONSTANTS.SURVEY_WEBSERVICE_SELECT_MODEL + "/");
		    
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
					((QuestionsPager) view.getContext()).initialisePaging();
				}
			}

		
		/**
		 * Initialise the fragments to be paged
		 */
		private void initialisePaging() {

			questionFragments = new Vector<Question>();
//			SurveyBean surveyBean = ParserUtils.parseJSON(this, json);
			
			Log.i("initialisePaging", "here");

			SurveyBean sb = getSurveyBean();
			
			Log.i("initialisePaging", sb.getName());

			
			int i = 1;
			for(QuestionBean question : sb.getQuestions()) {
				question.setNumber(String.valueOf(i)); // TODO; it was a test
				questionFragments.add((Question) Question.newInstance(question));
				i++;
			}
			// Adding the Summary Fragment (FAKE)
			questionFragments.add((Summary) Summary.newInstance(new QuestionBean()));
			questionFragments.add((Summary) Summary.newInstance(new QuestionBean()));

			pager = (ViewPager) super.findViewById(R.id.viewpager);
			this.mPagerAdapter  = new QuestionsPagerAdapter(pager, super.getSupportFragmentManager(), questionFragments);
			pager.setAdapter(this.mPagerAdapter);
			
			DisplayMetrics displaymetrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
			int height = displaymetrics.heightPixels;
			int wwidth = displaymetrics.widthPixels;
			
			Log.i("SIZE", String.valueOf(height));
			
			// WORKAROUND to load all the pages
//			for(int j=0; j < questionFragments.size(); j++) {
//				Log.i("QUESTION ", String.valueOf(j));
//				changePage(j);
//			}
//			changePage(0);
			
			
			Toast.makeText(this, String.valueOf(height), Toast.LENGTH_LONG);

		    nextButton = (Button) findViewById(R.id.next);
		    nextButton.setId(R.string.next);
		    nextButton.setOnClickListener(new ChangePageHandler());
		    backButton = (Button) findViewById(R.id.back);
		    backButton.setOnClickListener(new ChangePageHandler());
		    backButton.setId(R.string.back);
		    summaryButton = (Button) findViewById(R.id.summary);
		    summaryButton.setOnClickListener(new SummaryHandler());
		    summaryButton.setId(R.string.summary);
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
				changePage(pos);
			}
		}
	    
	    public void changePage(int pos) {
	    	pager.setCurrentItem(pos, true);
	    }
	    
	    
	    public class SummaryHandler implements View.OnClickListener {
			public void onClick( View view ) {
				Log.i("SummaryHandler", "");
				
//				Intent intent = new Intent().setClass(getBaseContext(), StartUpActivity.class);
//				startActivity(intent);
				
				pager.setCurrentItem(questionFragments.size()-1, false);
			}
		}
	    
	    @Override
	public void onBackPressed() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		// set title
		alertDialogBuilder.setTitle(R.string.exitSurvey);

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton(R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// if this button is clicked, close
								// current activity
						        finish();				      
							}
						})
				.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}



		public List<Question> getQuestionFragments() {
			return questionFragments;
		}

		public QuestionsPagerAdapter getmPagerAdapter() {
			return mPagerAdapter;
		}
	}


