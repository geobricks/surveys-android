package org.geobricks.survey.summary;

import java.util.ArrayList;
import java.util.List;

import org.geobricks.survey.R;
import org.geobricks.survey.bean.AnswerBean;
import org.geobricks.survey.bean.SurveyBean;
import org.geobricks.survey.questions.QuestionsPager;
import org.geobricks.survey.utils.AnswerUtils;
import org.geobricks.survey.utils.Data;
import org.w3c.dom.Text;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SummaryListPanel {
	
	Context context;
	
	ListView listView;
	
	LinearLayout panel;
	
	public SummaryListPanel(Context context) {
		this.context = context;
	}
	
	  public LinearLayout build(SurveyBean surveyBean, List<AnswerBean> answerBeans) {
	   panel = new LinearLayout(context);
	   
        
        SummaryListModel data[] = new SummaryListModel[answerBeans.size()];
        
        for(int i=0; i < answerBeans.size(); i++) {
        	data[i] = new SummaryListModel(R.drawable.arrow_right, answerBeans.get(i));
        }
       
        SummaryListAdapter adapter = new SummaryListAdapter(context, R.layout.listview_list_item_row, data);
        
        listView = new ListView(context);
        View header = (View) ((Activity) context).getLayoutInflater().inflate(R.layout.listview_header_row, null);
        listView.addHeaderView(header);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
				try {
				 QuestionsPager qp = (QuestionsPager) ((Activity) context);
				 SummaryListModel model = (SummaryListModel) adapter.getItemAtPosition(pos);
				 // TODO: maybe add a sequential number for the position of the question
				 qp.changePage(Integer.valueOf(model.answerBean.getQuestionBean().getNumber()) -1);
				
				} catch(Exception e) {
					
				}
			}
		});
        
        AnswerUtils.createJSONAnswer(surveyBean, answerBeans);
        
	    panel.addView(listView);
	    return panel;
	  }

}
