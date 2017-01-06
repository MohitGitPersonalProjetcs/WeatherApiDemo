package com.weather.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.weather.beans.DarkSkyBean;
import com.weather.beans.OpenWeatherMapBean;
import com.weather.clients.DarkSkyClient;
import com.weather.clients.OpenWeatherMapClient;
import com.weather.utils.ApplicationConstants;
import com.weather.utils.ApplicationProperties;

@Path("/weather")
public class WeatherResource {

	ApplicationProperties applicationProperties;

	@GET
	@Path("/city/{city}")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getMinMaxTemperatures(@PathParam("city") String city)
			throws JsonParseException, JsonMappingException, JSONException, IOException {
		applicationProperties = new ApplicationProperties();
		String openWeatherMapId = applicationProperties.getPropertyFileValue(ApplicationConstants.openMapId);
		OpenWeatherMapClient openWeatherMapClient = new OpenWeatherMapClient(openWeatherMapId);

		OpenWeatherMapBean openWeatherMapBean = openWeatherMapClient.getMinMaxTemperatureResults(city);
		ResponseBuilder responseBuilder;

		if (openWeatherMapBean == null) {
			responseBuilder = Response.status(404);
		} else {
			responseBuilder = Response.status(200);

		}

		return responseBuilder.entity(openWeatherMapBean).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}

	@GET
	@Path("/latlong/{latitude}/{longitude}")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getListOfTemperaturesWithTime(@PathParam("latitude") String latitude,
			@PathParam("longitude") String longitude)
			throws JsonParseException, JsonMappingException, JSONException, IOException {

		DarkSkyClient darkSkyClient = new DarkSkyClient();
		applicationProperties = new ApplicationProperties();

		String darkSkyId = applicationProperties.getPropertyFileValue(ApplicationConstants.darkSkyId);

		darkSkyClient.setAPPID(darkSkyId);
		DarkSkyBean darkSkyBean = darkSkyClient.getDarkSkyData(latitude, longitude);

		ResponseBuilder responseBuilder;

		if (darkSkyBean == null) {
			responseBuilder = Response.status(404);
		} else {
			responseBuilder = Response.status(200);

		}

		return responseBuilder.entity(darkSkyBean).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}
}
