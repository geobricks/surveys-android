package org.geobricks.survey;


import org.geobricks.survey.bean.QuestionBean;
import org.geobricks.survey.bean.SurveyBean;
import org.geobricks.survey.questions.QuestionsPager;
import org.geobricks.survey.utils.ParserUtils;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;

public class EntryActivity extends TabActivity {
	
	static TabHost tabHost;

	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.entrypoint);
	    
	    Resources res = getResources(); // Resource object to get Drawables
	    tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab
	
	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, QuestionsPager.class);
	    spec = tabHost.newTabSpec("survey").setIndicator("test",res.getDrawable(R.drawable.arrow_left)).setContent(intent);
	    tabHost.addTab(spec);
	
//	    intent = new Intent().setClass(this, FormActivity.class);
//	    spec = tabHost.newTabSpec("Form").setIndicator("Form",res.getDrawable(R.drawable.ic_tab_form)).setContent(intent);
//	    tabHost.addTab(spec);
	
//	    // Do the same for the other tabs
//	    intent = new Intent().setClass(this, SummaryActivity.class);
//	    spec = tabHost.newTabSpec("summary").setIndicator("Summary",res.getDrawable(R.drawable.ic_tab_summary)).setContent(intent);
//	    tabHost.addTab(spec);
//	
//	    intent = new Intent().setClass(this, MarketsActivity.class);
//	    spec = tabHost.newTabSpec("markets").setIndicator(this.getString(R.string.addMarket),res.getDrawable(R.drawable.ic_tab_addmaket)).setContent(intent);
//	    tabHost.addTab(spec);

	
	    tabHost.setCurrentTab(0);
    }
    

}