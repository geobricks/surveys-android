package org.geobricks.survey.surveyslist;

import java.util.List;

import org.geobricks.survey.R;
import org.geobricks.survey.bean.SurveyBean;
import org.geobricks.survey.bean.SurveyBeanSerializable;
import org.geobricks.survey.questions.Question;
import org.geobricks.survey.questions.QuestionsPager;
import org.geobricks.survey.utils.ParserUtils;
import org.geobricks.survey.utils.Utils;

import android.app.Activity;
import android.app.DownloadManager.Query;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SurveysListviewActivity extends Activity {

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        
        // TODO: should be read dinamically somewhere (DB or webservice)
		String json = Utils.readFile(this, R.raw.survey_list_large);
		List<SurveyBean> surveys = ParserUtils.parseJSONSurveysList(this, json);
        
        SurveyListModel data[] = new SurveyListModel[surveys.size()];
        
        for(int i=0; i < surveys.size(); i++) {
        	data[i] = new SurveyListModel(R.drawable.arrow_right, surveys.get(i));
        }
       
        SurveyListAdapter adapter = new SurveyListAdapter(this, R.layout.listview_list_item_row, data);
        
        listView = (ListView)findViewById(R.id.listView1);
        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
        listView.addHeaderView(header);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
				SurveyListModel model = (SurveyListModel) adapter.getItemAtPosition(pos);
				Intent i = new Intent().setClass(view.getContext(), QuestionsPager.class);
				// passing survey bean to questionPager
				i.putExtra("bean", model.surveyBean);
				view.getContext().startActivity(i);
			}
		});
    }
}