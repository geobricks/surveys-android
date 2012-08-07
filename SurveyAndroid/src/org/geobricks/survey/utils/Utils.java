package org.geobricks.survey.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.util.Log;

public class Utils {
	
	public static String readFile(Context context, int resource) {
		StringBuilder text = new StringBuilder();
		try {
			InputStream open = context.getResources().openRawResource(resource);
			InputStreamReader inputreader = new InputStreamReader(open);
			BufferedReader buffreader = new BufferedReader(inputreader);
			String line;
			while ((line = buffreader.readLine()) != null) {
				text.append(line);
			}
//		    Log.i("output", text.toString());
		    open.close();
		}catch(Exception e){}
	    return text.toString();
	}

}
