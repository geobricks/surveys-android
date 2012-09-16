package org.geobricks.survey.utils;

import java.util.List;

import org.geobricks.survey.R;
import org.geobricks.survey.bean.AnswerBean;
import org.geobricks.survey.bean.ChoicesBean;
import org.geobricks.survey.bean.QuestionBean;
import org.geobricks.survey.bean.SurveyBean;
import org.geobricks.survey.questions.Question;
import org.geobricks.survey.questions.types.QuestionBoolean;
import org.geobricks.survey.questions.types.QuestionDate;
import org.geobricks.survey.questions.types.QuestionEditText;
import org.geobricks.survey.questions.types.QuestionMultiselection;
import org.geobricks.survey.questions.types.QuestionSpinner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.DownloadManager.Query;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class AnswerUtils {
	
	public static AnswerBean getAnswerBean(Question q) {
		AnswerBean answerBean = new AnswerBean();
		QuestionBean qb = q.getQuestionBean();
		answerBean.setQuestionBean(qb);
		
		Log.i("ANSWERTYPE", String.valueOf(qb.getType()));

		
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
					answerBean.setResult(String.valueOf(qd.getValue().getDayOfMonth() +"/" + (qd.getValue().getMonth() + 1)+"/"+ qd.getValue().getYear()));
					answerBean.setAnswered(true);
					break;
				case SINGLE_VALUE_BOOLEAN:
					QuestionBoolean qboolean = (QuestionBoolean) q.getQuestionValue();
					int btn = qboolean.getValue().getCheckedRadioButtonId();
				    switch (btn) {
					    case R.string.yes:
							answerBean.setResult("0");
							answerBean.setAnswered(true);
					    break;
					    case R.string.no:
							answerBean.setResult("1");
							answerBean.setAnswered(true);				    
							break;
				    }
					break;
				case SINGLE_CHOICE:
					QuestionSpinner qs = (QuestionSpinner) q.getQuestionValue();
					Data d = (Data) qs.getSpinner().getSelectedItem();
					answerBean.setResult(d.getLabel());
					answerBean.getAnswerChoicesBean().addData(d);
					answerBean.setAnswered(true);	
					break;
				case MULTIPLE_CHOICE:
					QuestionMultiselection qm = (QuestionMultiselection) q.getQuestionValue();
					ArrayAdapter<Data> adapter = qm.getListAdapter();
					Log.i("ANSWER", String.valueOf(adapter.getCount()));
					String v = "";
					for(int i = 0 ; i < adapter.getCount(); i++) {
						Data data = adapter.getItem(i);
						Log.i("ANSWER", data.getLabel());
						Log.i("ANSWER", String.valueOf(data.isChecked()));
						if ( data.isChecked() ) {
							v += " " + data.getLabel();	
							answerBean.getAnswerChoicesBean().addData(data);
							answerBean.setAnswered(true);
						}
						
					}
					answerBean.setResult(v);
					break;
		
				default: break;
			}
			try {
			}catch(Exception e ){
				Log.i("ANSWER ERROR", e.getMessage());
			}
		}
		return answerBean;
	}
	
	public static void test() {
		createJSONAnswer(null, null);
	}
	
	public static String createJSONAnswer(SurveyBean surveyBean, List<AnswerBean> answerBeans) {
		
		JSONObject obj = new JSONObject();
		
		try {
			obj.put("meta", createObjMetaData(surveyBean));
			obj.put("data", createObjData(answerBeans));
		} catch (JSONException e) {e.printStackTrace(); }

		Log.i("ANSWER", obj.toString());
		return obj.toString();
	}
	
	private static JSONObject createObjMetaData(SurveyBean surveyBean) {
		String result = new String();
		
//		  "model_id": "504e1efed6f33bb421000001",
//	      "date":"2012-09-10",
//	      "user":"Kalimaha",
//	      "location":[
//	         23.706898506522872,
//	         90.4019021987915
//	      ]
		
		JSONObject data = new JSONObject();
		
		try {
			data.put("model_id", surveyBean.getId());
//			data.put("date", "2012-09-10");
//			data.put("user", "Kalimaha");
		} catch (JSONException e) {e.printStackTrace(); }
		
		Log.i("ANSWER", data.toString());
		return data;
	}
	
	private static  JSONArray createObjData(List<AnswerBean> answerBeans) {
		JSONArray data = new JSONArray();
		
//		   "data":[
//		           {
//		              "question_id":1,
//		              "answer":0
//		           },
//		           {
//		              "question_id":2,
//		              "answer":3
//		           }
//		        ]
		
		for ( AnswerBean ab : answerBeans ) {
			JSONObject obj = createObjAnswer(ab);
			if ( obj != null )
				data.put(obj);
		}
		
		Log.i("ANSWER", data.toString());
		return data;
	}
	
	private static JSONObject createObjAnswer(AnswerBean ab) {
		try {
			if ( ab.getResult() != null) {
				JSONObject data = new JSONObject();		
				data.put("question_id", ab.getQuestionBean().getNumber());
				data.put("answer", ab.getResult());
				return data;
			}
			
		} catch (JSONException e) {e.printStackTrace(); }
		return null;
	}
}
