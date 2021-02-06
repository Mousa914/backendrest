package com.restaurants.restaurants.controller;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class GeocodeController 
{

	public String getGeocode(String l1,String l2) throws IOException 
	{
		OkHttpClient client = new OkHttpClient();
		
		Request request = new Request.Builder()
				.url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?latlng="+ l1 +"%2C"+ l2 +"&language=en")
				.get()
				.addHeader("x-rapidapi-key", "1c6de6d5ddmshe5479832d17a617p1ab186jsn8725ce74e2ec")
				.addHeader("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
				.build();
		
		ResponseBody responseBody = client.newCall(request).execute().body();
		ObjectMapper objectMapper = new ObjectMapper();
		GeocodeResult result = objectMapper.readValue(responseBody.string(), GeocodeResult.class);
	
		return result.getResults().get(0).getFormattedAddress();
	}

}
