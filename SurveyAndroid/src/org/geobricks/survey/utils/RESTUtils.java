package org.geobricks.survey.utils;

import java.util.LinkedHashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.geobricks.survey.constants.CONSTANTS;
import org.json.JSONArray;

import android.content.Context;
import android.widget.Toast;

public class RESTUtils {
	
	public static String getJsonById(Context context, String q) {
//		String address = "http://" + CONSTANTS.SURVEY_WEBSERVICE_URL + "/" + q;
		String address = "http://" + q;
		
//		Log.i("PUT", address);

		try {
			HttpClient client = new DefaultHttpClient();
			HttpPut put= new HttpPut(address);
			HttpResponse response = client.execute(put);
			HttpEntity responseEntity = response.getEntity();
			// checks if the data
			if( responseEntity != null ) {
				return EntityUtils.toString(responseEntity);
			}
//			else 
//				Toast.makeText(context,messageFailed,Toast.LENGTH_SHORT).show();
		} catch (Exception e) { 
//			Toast.makeText(context,messageFailed,Toast.LENGTH_SHORT).show();
		}
		return null;
	}

}
