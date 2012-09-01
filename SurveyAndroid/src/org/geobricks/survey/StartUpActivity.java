package org.geobricks.survey;

import org.geobricks.survey.surveyslist.SurveysListviewActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class StartUpActivity extends Activity {
	 
		GridView gridView;
	 
		
	 
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			String[] gridvalues = new String[] { 
				this.getString(R.string.load_survey), 
				this.getString(R.string.summary),
				this.getString(R.string.search),
				this.getString(R.string.settings),
				};
	 
			setContentView(R.layout.startup);
			
			TextView title = (TextView) findViewById(R.id.startup_title);
			title.setText(Html.fromHtml("<b>Microdata Tool</b><br>copyright 2012"));
	 
			gridView = (GridView) findViewById(R.id.startup_grid);
	 
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gridvalues);
	 
			gridView.setAdapter(adapter);
	 
			gridView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
					
					Log.i("id", String.valueOf(id));
					Log.i("2 id", String.valueOf(v.getId()));
					
					
				switch (v.getId()) {
					case R.string.load_survey: break;
					case R.string.search:  break;
				}
					
				   //Toast.makeText(getApplicationContext(),
					//((TextView) v).getText(), Toast.LENGTH_SHORT).show();
//					Intent intent = new Intent().setClass(v.getContext(), QuestionsPager.class);
//			        v.getContext().startActivity(intent);
			        
					Intent intent = new Intent().setClass(v.getContext(), SurveysListviewActivity.class);
			        v.getContext().startActivity(intent);
				}
			});

		}
	 
	}