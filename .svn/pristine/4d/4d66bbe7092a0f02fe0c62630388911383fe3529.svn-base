package com.tc.cmcglobal.network;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public class VTechVolleyError extends VolleyError {
	public String getErrorMessage(){
		String str = "";
		try {
			JSONObject json = new JSONObject(getMessage());
			str = json.getString("message");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str; 
	}
}
