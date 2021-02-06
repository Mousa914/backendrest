package com.restaurants.restaurants;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.restaurants.restaurants.controller.RestaurantController;
import com.restaurants.restaurants.controller.RestaurantControllerRepo;
import com.restaurants.restaurants.domain.Restaurant;

@SpringBootApplication
public class RestaurantsApplication {
	
	@Autowired
	private RestaurantControllerRepo repo;

	public static void main(String[] args) {
		SpringApplication.run(RestaurantsApplication.class, args);
		
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
			RestaurantController rest = new RestaurantController();
			try 
			{
				repo.deleteAll();
				List<Restaurant> resturant = rest.loadCSV();
				for(int i = 0 ; i < resturant.size() ; i++)
				{
					repo.save(resturant.get(i));
				}
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		}

}
