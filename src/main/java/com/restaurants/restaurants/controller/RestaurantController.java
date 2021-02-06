package com.restaurants.restaurants.controller;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.restaurants.restaurants.domain.Restaurant;

@RestController
public class RestaurantController {
	
	@Autowired
	private RestaurantControllerRepo repo;

	public List<Restaurant> loadCSV() throws IOException
	{
		ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
		strat.setType(Restaurant.class);
		String[] columns = new String[] {"Name", "Type", "Phone", "Location"};
		strat.setColumnMapping(columns);

		String csvFilename = "./restaurants.csv";
		CSVReader csvReader = new CSVReader(new FileReader(csvFilename));

		CsvToBean<Restaurant> csv = new CsvToBean();
		List<Restaurant> list = csv.parse(strat, csvReader);
		GeocodeController geocodeController = new GeocodeController();
		for (Restaurant restaurant : list) 
		{
			System.out.println(restaurant.toString());
			String[] arrSplit = restaurant.getLocation().split("/");
			
			String location = geocodeController.getGeocode(arrSplit[0],arrSplit[1]);
			System.out.println("location :" +  location);
			
			restaurant.setLocation(location);				
		}
		
		return list;
	}
	
	@RequestMapping("/restaurants")
	@CrossOrigin(origins = "*")
	public List<Restaurant> getRestaurant()
	{
		Iterable<Restaurant> it = repo.findAll();//Select * from DB
		
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		it.forEach(e -> restaurants.add(e));

	    return restaurants;
	}
	
}
