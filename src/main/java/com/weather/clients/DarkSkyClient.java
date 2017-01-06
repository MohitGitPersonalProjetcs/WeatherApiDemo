package com.weather.clients;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.beans.DarkSkyBean;

public class DarkSkyClient {

	static private final String APPID_HEADER = "x-api-key";
	private HttpClient httpClient;
	private String owmDarkSkyID = null;

	private String baseDarkSkyUrl = "https://api.darksky.net/forecast/";

	public DarkSkyClient() {
		this.httpClient = new DefaultHttpClient();
	}

	public DarkSkyClient(HttpClient httpClient) {
		if (httpClient == null)
			throw new IllegalArgumentException("Can't construct a OwmClient with a null HttpClient");
		this.httpClient = httpClient;
	}

	/**
	 * @param appid
	 *            The APP ID provided by OpenWeatherMap
	 */
	public void setAPPID(String appid) {
		this.owmDarkSkyID = appid;
	}

	public DarkSkyBean getDarkSkyData(String latitude, String longitude)
			throws IOException, JsonParseException, JsonMappingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		String latLongData = latitude + "," + longitude;
		String appId = owmDarkSkyID;
		String subUrl = appId + "/" + latLongData;

		DarkSkyClient darkSkyClient = new DarkSkyClient();
		JSONObject response = darkSkyClient.doQuery(subUrl);

		DarkSkyBean darkSkyBean = objectMapper.readValue(response.toString(), DarkSkyBean.class);
		return darkSkyBean;
	}

	private JSONObject doQuery(String subUrl) throws JSONException, IOException {
		String responseBody = null;
		HttpGet httpget = new HttpGet(this.baseDarkSkyUrl + subUrl);
		if (this.owmDarkSkyID != null) {
			httpget.addHeader(DarkSkyClient.APPID_HEADER, this.owmDarkSkyID);
		}

		HttpResponse response = this.httpClient.execute(httpget);
		InputStream contentStream = null;
		try {
			StatusLine statusLine = response.getStatusLine();
			if (statusLine == null) {
				throw new IOException(String.format("Unable to get a response from OWM server"));
			}
			int statusCode = statusLine.getStatusCode();
			if (statusCode < 200 && statusCode >= 300) {
				throw new IOException(
						String.format("OWM server responded with status code %d: %s", statusCode, statusLine));
			}
			/* Read the response content */
			HttpEntity responseEntity = response.getEntity();
			contentStream = responseEntity.getContent();
			Reader isReader = new InputStreamReader(contentStream);
			int contentSize = (int) responseEntity.getContentLength();
			if (contentSize < 0)
				contentSize = 8 * 1024;
			StringWriter strWriter = new StringWriter(contentSize);
			char[] buffer = new char[8 * 1024];
			int n = 0;
			while ((n = isReader.read(buffer)) != -1) {
				strWriter.write(buffer, 0, n);
			}
			responseBody = strWriter.toString();
			contentStream.close();
		} catch (IOException e) {
			throw e;
		} catch (RuntimeException re) {
			httpget.abort();
			throw re;
		} finally {
			if (contentStream != null)
				contentStream.close();
		}
		return new JSONObject(responseBody);
	}

}
