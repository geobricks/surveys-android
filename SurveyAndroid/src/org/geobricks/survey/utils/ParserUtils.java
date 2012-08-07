package org.geobricks.survey.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.geobricks.survey.bean.QuestionBean;
import org.geobricks.survey.bean.SurveyBean;
import org.geobricks.survey.constants.QUESTIONINFO;
import org.geobricks.survey.constants.QUESTIONTYPE;
import org.geobricks.survey.constants.SURVEYINFO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.content.Context;
import android.util.Log;

public class ParserUtils {
	
	public static SurveyBean parseJSON(Context context, int resource) {
		 String json = Utils.readFile(context, resource);
		 SurveyBean surveyBean = new SurveyBean();		 
		 try {
			JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
			Map<String, String> map = new HashMap<String, String>();
			Iterator iter = object.keys();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				String value = object.getString(key);
				map.put(key, value);
			}		 
			surveyBean = parseSurvey(map);
		
		 } catch (JSONException e) {Log.e("JSON", e.getMessage());}
		 return surveyBean;
	}

	private static SurveyBean parseSurvey(Map<String, String> map) {
		SurveyBean surveyBean = new SurveyBean();
		for(String key : map.keySet()) {
			SURVEYINFO c = SURVEYINFO.valueOf(key.toUpperCase());
			switch (c) {
				case SURVEY_ID: surveyBean.setId(map.get(key)); break;
				case SURVEY_NAME: surveyBean.setName(map.get(key)); break;
				case SURVEY_ABSTRACT: surveyBean.setSurvey_abstract(map.get(key)); break;
				case SURVEY_QUESTIONS: surveyBean.setQuestions(parseQuestions(map.get(key))); break;
				default: break;
			}
		}
		return surveyBean;
	}
	
	private static List<QuestionBean> parseQuestions(String survey_questions) {
		List<QuestionBean> questions = new ArrayList<QuestionBean>();
		Log.i("JSON",  survey_questions);
		 try {
			 	JSONArray array = (JSONArray) new JSONTokener(survey_questions).nextValue();
				for(int i=0; i <  array.length(); i++) {
					JSONObject object = (JSONObject) array.get(i);
					Log.i("JSON", "!" + i + " | " + array.get(i));
					Log.i("JSON", "!!" + i + " | " + object);
					Iterator iter = object.keys();
					Map<String, String> map = new HashMap<String, String>();
					while (iter.hasNext()) {
						String key = (String) iter.next();
						String value = object.getString(key);
						map.put(key, value);
					}
					Log.i("JSON", "---->" + i + " | " + map);
					questions.add(parseQuestion(map));
				}
			 } catch (JSONException e) {Log.e("JSON", e.getMessage());}
		return questions;
	}
	
	private static QuestionBean parseQuestion(Map<String, String> map) throws JSONException {
		QuestionBean question = new QuestionBean();
		for(String key : map.keySet()) {
			QUESTIONINFO c = QUESTIONINFO.valueOf(key.toUpperCase());
			Log.i("JSON", c.toString() + " | " + key.toUpperCase());
			switch (c) {
				case QUESTION_NUMBER: question.setNumber(map.get(key)); break;
				case QUESTION_TEXT: question.setText(map.get(key)); break;
				case QUESTION_TYPE: Log.i("JSON", map.get(key).toUpperCase()); question.setQuestionType(QUESTIONTYPE.valueOf(map.get(key).toUpperCase())); break;
				case QUESTION_CHOICES:  break;
				default: break;
			}
		}

		return question;
	}
	
	
	
}
