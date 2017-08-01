package com.tc.cmcglobal.network;



import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class KeepSession {
	
	private static final String SET_COOKIE_KEY = "Set-Cookie";
	private static final String COOKIE_KEY = "Cookie";
	private static KeepSession _instance;
	public static HashMap<String, String> cookiesList = new HashMap<String, String>();
	
	public KeepSession() {
		// TODO Auto-generated constructor stub
	}
	
	public static KeepSession get() {
		if(_instance == null) {
			synchronized (KeepSession.class) {
				if (_instance == null) {
					_instance = new KeepSession();
				}
			}
		}
		return _instance;
	}
	/**
	 * Adds session cookie to headers if exists.
	 * 
	 * @param headers
	 */
	public void addSessionCookie(Map<String, String> headers) {
		if (cookiesList.size() > 0) {
			StringBuilder builder = new StringBuilder();
			Set<String> keySet = cookiesList.keySet();
			for (String key : keySet) {
				builder.append(key);
				builder.append("=");
				builder.append(cookiesList.get(key));
				builder.append(";");
			}
			if (headers.containsKey(COOKIE_KEY)) {
				builder.append(headers.get(COOKIE_KEY));
			}
			headers.put(COOKIE_KEY, builder.toString());
		}
	}
	
	
	/**
	 * Checks the response headers for session cookie and saves it if it finds
	 * it.
	 * 
	 * @param headers
	 *            Response Headers.
	 */
	public void checkSessionCookie(Map<String, String> headers) {
		if (headers.get(SET_COOKIE_KEY) != null) {
				String cookie = headers.get(SET_COOKIE_KEY);
				if (cookie.length() > 0) {
					String[] splitCookie = cookie.split(";");
					for (int i = 0, j = splitCookie.length; i < j; i++) {
						String keyValue = splitCookie[i];
						if (keyValue.split("=").length == 2) {
							cookiesList.put(keyValue.split("=")[0], keyValue.split("=")[1]);
						}
					}
				}
		}
	}

	public void clearSession(){
		if (cookiesList != null) {
			cookiesList.clear();
		}
	}
}
