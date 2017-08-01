package com.tc.cmcglobal.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyJson extends JSONObject {
	public static final int SUCCESS = 0;
	public static final int ERROR_PARSER = 500;
	public static final int ERROR_BAD_REQUEST = 400;
	public static final int ERROR_AUTHENTICATED_USER = 401;

	public static final String FIELD_ERROR = "status";
	// public static final String FIELD_MESSAGE = "message";
	public static final String FIELD_DATA = "data";
	public static final String FIELD_METADATA = "_meta";
	// public static final String FIELD_TOTAL = "total";
	public int error = -1;
	// public String message;
	public String data;
	public String json;
	public String metaData;
	private int mStatusCode = 200;

	// public String total;

	public MyJson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyJson(JSONObject copyFrom, String[] names) throws JSONException {
		super(copyFrom, names);
		// TODO Auto-generated constructor stub
	}

	public MyJson(JSONTokener readFrom) throws JSONException {
		super(readFrom);
		// TODO Auto-generated constructor stub
	}

	public MyJson(Map copyFrom) {
		super(copyFrom);
		// TODO Auto-generated constructor stub
	}

	public MyJson(String json) throws JSONException {
		super(json);
		this.json = json;
		if (has(FIELD_ERROR)) {
			error = getInt(FIELD_ERROR);
		}
		// if (has(FIELD_MESSAGE)) {
		// message = getString(FIELD_MESSAGE);
		// }
		if (has(FIELD_DATA)) {
			data = getString(FIELD_DATA);
		}
		// if (has(FIELD_TOTAL)) {
		// total = getString(FIELD_TOTAL);
		// }
		if (has(FIELD_METADATA)) {
			metaData = getString(FIELD_METADATA);
		}
		// TODO Auto-generated constructor stub
	}

	public Boolean isSuccess() {
		// return error == SUCCESS;
		if (mStatusCode == 200 || mStatusCode == 201 || mStatusCode == 204) {
			return true;
		}
		return false;
	}

	// public String getErrorMessage() {
	// return message;
	// }

	// public int getTotal() {
	// if (total == null) {
	// return 0;
	// }
	// return Integer.parseInt(total);
	// }

	public <T> T get(Type typeOfT, String field) throws JSONException {
		data = getString(field);
		Gson gson = new Gson();
		return gson.fromJson(data, typeOfT);
	}

	public <T> T to(Type typeOfT) {
		Gson gson = new Gson();
		return gson.fromJson(data, typeOfT);
	}

	public <T> T to(Class<T> _class) {
		Gson gson = new Gson();
		return gson.fromJson(data, _class);
	}

	public <T> T get() {
		Type type = new TypeToken<T>() {
		}.getType();
		return new Gson().fromJson(data, type);
	}

	public <T> List<T> toList(Class<T> _class) {
		List<T> listData = new ArrayList<T>();
		JSONArray jsonArray;
		try {
			jsonArray = new JSONArray(data);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			error = ERROR_PARSER;
			// message = e1.getMessage();
			return listData;
		}
		Gson g = new Gson();
		try {

			for (int i = 0; i < jsonArray.length(); i++) {
				// showLog(ja.getString(i), myClass);
				@SuppressWarnings("unused")
				String str = jsonArray.getString(i);
				listData.add(g.fromJson(jsonArray.getString(i), _class));
				// listener.loading(i,ja.length());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			error = ERROR_PARSER;
			// message = e.getMessage();
		}
		return listData;
	}

	public <T> List<T> appendList(Class<T> _class, List<T> listData) {
		JSONArray jsonArray;
		try {
			jsonArray = new JSONArray(data);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			error = ERROR_PARSER;
			// message = e1.getMessage();
			return listData;
		}
		Gson g = new Gson();
		try {

			for (int i = 0; i < jsonArray.length(); i++) {
				// showLog(ja.getString(i), myClass);
				listData.add(g.fromJson(jsonArray.getString(i), _class));
				// listener.loading(i,ja.length());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			error = ERROR_PARSER;
			// message = e.getMessage();
		}
		return listData;
	}

	public String getJson() {
		return json;
	}

	public int getTotal() {
		if (metaData == null) {
			return Integer.MAX_VALUE;
		}

		try {
			JSONObject jsonObject = new JSONObject(metaData);
			return jsonObject.getInt("totalCount");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	public int getMetaDataInfo(String field , int defautValue){
		if (metaData == null) {
			return defautValue;
		}
		try {
			JSONObject jsonObject = new JSONObject(metaData);
			return jsonObject.getInt(field);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return defautValue;
	}
	public <T> T getValueFromJson(Class<T> _class, String field){
		try {
			//JSONObject jsonObject = new JSONObject(metaData);
			String j = getString(field);
			Gson gson = new Gson();
			return gson.fromJson(j, _class);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setStatusCode(int statusCode) {
		this.mStatusCode = statusCode;
	}
}
