package org.geobricks.survey.utils;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.geobricks.survey.bean.QuestionBean;
import org.geobricks.survey.bean.SurveyBean;
import org.geobricks.survey.constants.CONSTANTS;
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
	
	public static SurveyBean parseJSON(Context context, String json, String language) {
		 SurveyBean surveyBean = new SurveyBean();		 
		 try {
			JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			Iterator iter = object.keys();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				String value = object.getString(key);
				map.put(key, value);
			}		 
			surveyBean = parseSurvey(map, language);
		
		 } catch (JSONException e) {Log.e("JSON", e.getMessage());}
		 return surveyBean;
	}

	private static String cutKey(String key) {
		String k = key;
		k = key.substring(key.indexOf("_") + 1);
		return k;
	}
	
	private static SurveyBean parseSurvey(Map<String, String> map, String language) {
		SurveyBean surveyBean = new SurveyBean();
		
		//theere are the no language based fields
		for(String key : map.keySet()) {
			try {
				SURVEYINFO c = SURVEYINFO.valueOf(key.toUpperCase());
				switch (c) {
					case ID: surveyBean.setId(map.get(key)); break;
					case MODEL_QUESTIONS: surveyBean.setQuestions(parseQuestions(map.get(key), language)); break;
					default: break;
				}
			}catch(Exception e) {}
		}
		
		//these are the language based fields
		for(String key : map.keySet()) {
			Log.i("KEY: ", key);
			String cutkey = cutKey(key);
			Log.i("cutkey: ", cutkey);
			try {
				SURVEYINFO c = SURVEYINFO.valueOf(cutkey.toUpperCase());
	
				String keylanguage = language + "_"+ cutkey;
				Log.i("k: ", keylanguage);
				switch (c) {
					case NAME: surveyBean.setName(map.get(keylanguage)); break;
					case ABSTRACT: surveyBean.setSurvey_abstract(map.get(keylanguage)); break;
					default: break;
				}
			}catch(Exception e) {}
		}
		return surveyBean;
	}
	
	private static List<QuestionBean> parseQuestions(String survey_questions, String language) {
		List<QuestionBean> questions = new ArrayList<QuestionBean>();
		Log.i("JSON",  survey_questions);
		 try {
			 	JSONArray array = (JSONArray) new JSONTokener(survey_questions).nextValue();
				for(int i=0; i <  array.length(); i++) {
					JSONObject object = (JSONObject) array.get(i);
					Log.i("JSON", "!" + i + " | " + array.get(i));
					Log.i("JSON", "!!" + i + " | " + object);
					Iterator iter = object.keys();
					LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
					while (iter.hasNext()) {
						String key = (String) iter.next();
						String value = object.getString(key);
						map.put(key, value);
					}
					Log.i("JSON", "---->" + i + " | " + map);
					questions.add(parseQuestion(map, language));
				}
			 } catch (JSONException e) {Log.e("JSON", e.getMessage());}
		return questions;
	}
	
	private static QuestionBean parseQuestion(Map<String, String> map, String language) throws JSONException {
		QuestionBean question = new QuestionBean();
		for(String key : map.keySet()) {
			try {
				QUESTIONINFO c = QUESTIONINFO.valueOf(key.toUpperCase());
				Log.i("JSON", c.toString() + " | " + key.toUpperCase());
				switch (c) {
					case QUESTION_NUMBER: question.setNumber(map.get(key)); break;
					case QUESTION_TYPE: Log.i("JSON", map.get(key).toUpperCase()); question.setQuestionType(QUESTIONTYPE.valueOf(map.get(key).toUpperCase())); break;
					case QUESTION_CHOICES: question.getQuestionChoices().setChoices(getQuestionChoices(map.get(key),language)); break;
					default: break;
			}
			}catch(Exception e ) {}
		}
		
		for(String key : map.keySet()) {
			String cutkey = cutKey(key);
			String keylanguage = language + "_"+ cutkey;
			try {
				QUESTIONINFO c = QUESTIONINFO.valueOf(cutkey.toUpperCase());
				Log.i("JSON", c.toString() + " | " + key.toUpperCase());
				switch (c) {
					case INFO: question.setInfo(map.get(keylanguage)); break;
					case TEXT: question.setText(map.get(keylanguage)); break;
					default: break;
			}
			}catch(Exception e ) {}
		}

		return question;
	}
	
	private static String getText(String json, String language) {
		String s = "The language is not supported";
		Log.i("JSON", json);
		 try {
			 JSONArray array = (JSONArray) new JSONTokener(json).nextValue();
			 for(int i=0; i <  array.length(); i++) {
					JSONObject object = (JSONObject) array.get(i);
					Log.i("JSON", "!" + i + " | " + array.get(i));
					Iterator iter = object.keys();
					LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
					while (iter.hasNext()) {
						String key = (String) iter.next();
						if ( key.toLowerCase().equals(language)) {
							return object.getString(key);
						}
					}
				}
		 } catch (JSONException e) {Log.e("JSON", e.getMessage());}

		return s;
	}
	
	private static List<Data> getQuestionChoices(String json, String language) {
		List<Data> data = new ArrayList<Data>();
		Log.i("QUESTION CHOICES ", json);
		String getJson = getText(json, language);
		Log.i("QUESTION CHOICES s ", getJson);
		 try {
			 JSONArray array = (JSONArray) new JSONTokener(getJson).nextValue();
			 for(int i=0; i <  array.length(); i++) {
				JSONObject object = (JSONObject) array.get(i);
				Log.i("JSON", "!" + i + " | " + array.get(i));
				Iterator iter = object.keys();
				while (iter.hasNext()) {
					String key = (String) iter.next();
					String value = object.getString(key);
					data.add(new Data(key, value));
				}
			}
		} catch (JSONException e) {Log.e("JSON", e.getMessage());}
		return data;
	}
}
