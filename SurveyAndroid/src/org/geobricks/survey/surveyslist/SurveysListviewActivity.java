package org.geobricks.survey.surveyslist;

import java.util.ArrayList;
import java.util.List;

import org.geobricks.survey.R;
import org.geobricks.survey.bean.SurveyBean;
import org.geobricks.survey.utils.ParserUtils;
import org.geobricks.survey.utils.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class SurveysListviewActivity extends Activity {

    private ListView listView1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        
		String json = Utils.readFile(this, R.raw.survey_list);
		List<SurveyBean> surveys = ParserUtils.parseJSONSurveysList(this, json);
        
        SurveyListModel data[] = new SurveyListModel[surveys.size()];
        
        for(int i=0; i < surveys.size(); i++) {
        	data[i] = new SurveyListModel(R.drawable.arrow_right, surveys.get(i));
        }
//        {
//            new SurveyListModel(R.drawable.arrow_right, "Cloudy"),
//            new SurveyListModel(R.drawable.arrow_right, "Showers"),
//            new SurveyListModel(R.drawable.arrow_right, "Snow"),
//            new SurveyListModel(R.drawable.arrow_right, "Storm"),
//            new SurveyListModel(R.drawable.arrow_right, "Sunny")
//        };
        
        SurveyListAdapter adapter = new SurveyListAdapter(this, R.layout.listview_list_item_row, data);
        
        
        listView1 = (ListView)findViewById(R.id.listView1);
         
        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
        listView1.addHeaderView(header);
        
        listView1.setAdapter(adapter);
    }
}